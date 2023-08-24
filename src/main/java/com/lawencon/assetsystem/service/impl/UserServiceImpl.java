package com.lawencon.assetsystem.service.impl;

import static com.lawencon.assetsystem.util.GeneratorID.generateID;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lawencon.assetsystem.dao.FileDao;
import com.lawencon.assetsystem.dao.UserDao;
import com.lawencon.assetsystem.dao.UserProfileDao;
import com.lawencon.assetsystem.dao.UserRoleDao;
import com.lawencon.assetsystem.dto.InsertResDto;
import com.lawencon.assetsystem.dto.UpdateResDto;
import com.lawencon.assetsystem.dto.login.LoginReqDto;
import com.lawencon.assetsystem.dto.login.LoginResDto;
import com.lawencon.assetsystem.dto.user.UserInsertReqDto;
import com.lawencon.assetsystem.dto.user.UserListResDto;
import com.lawencon.assetsystem.dto.user.UserPasswordChangeReqDto;
import com.lawencon.assetsystem.dto.user.UserProfileUpdateReqDto;
import com.lawencon.assetsystem.dto.user.UserResDto;
import com.lawencon.assetsystem.dto.user.UserUpdateIsActiveReqDto;
import com.lawencon.assetsystem.dto.userrole.UserRoleResDto;
import com.lawencon.assetsystem.model.File;
import com.lawencon.assetsystem.model.User;
import com.lawencon.assetsystem.model.UserProfile;
import com.lawencon.assetsystem.model.UserRole;
import com.lawencon.assetsystem.service.MailService;
import com.lawencon.assetsystem.service.PrincipalService;
import com.lawencon.assetsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;
	private final UserProfileDao userProfileDao;
	private final UserRoleDao userRoleDao;
	private final FileDao fileDao;
	private final PasswordEncoder passwordEncoder;
	private final MailService mailService;
	private final PrincipalService principalService;

	@PersistenceContext
	private EntityManager em;

	public UserServiceImpl(UserDao userDao, UserProfileDao userProfileDao, UserRoleDao userRoleDao, FileDao fileDao,
			PasswordEncoder passwordEncoder, MailService mailService, PrincipalService principalService) {
		this.userDao = userDao;
		this.userProfileDao = userProfileDao;
		this.userRoleDao = userRoleDao;
		this.fileDao = fileDao;
		this.passwordEncoder = passwordEncoder;
		this.mailService = mailService;
		this.principalService = principalService;
	}

	@Override
	public List<UserResDto> getAll() {
		final List<UserResDto> users = new ArrayList<>();

		userDao.getAll().forEach(u -> {
			final UserResDto user = new UserResDto();
			user.setUserFullName(u.getProfile().getUserFullname());
			user.setRoleName(u.getRole().getRoleName());
			user.setRoleCode(u.getRole().getRoleCode());
			users.add(user);
		});

		return users;
	}

	@Override
	public List<UserListResDto> getAllUser() {
		final List<UserListResDto> users = new ArrayList<>();

		userDao.getAll().forEach(u -> {
			final UserListResDto user = new UserListResDto();
			user.setId(u.getId());
			user.setFileId(u.getProfile().getFile().getId());
			user.setFile(u.getProfile().getFile().getFile());
			user.setFileExtension(u.getProfile().getFile().getFileExtension());
			user.setUserIdNumber(u.getProfile().getUserIdNumber());
			user.setUserFullname(u.getProfile().getUserFullname());
			user.setUserGender(u.getProfile().getUserGender());
			user.setUserEmail(u.getUserEmail());
			user.setRoleCode(u.getRole().getRoleCode());
			user.setRoleName(u.getRole().getRoleName());
			user.setIsActive(u.getIsActive());
			users.add(user);
		});

		return users;
	}

	@Override
	public UserListResDto getByID(long id) {
		final UserListResDto profile = new UserListResDto();
		final User user = userDao.getUserById(id);
		profile.setId(user.getId());
		profile.setFileId(user.getProfile().getFile().getId());
		profile.setFile(user.getProfile().getFile().getFile());
		profile.setFileExtension(user.getProfile().getFile().getFileExtension());
		profile.setUserIdNumber(user.getProfile().getUserIdNumber());
		profile.setUserFullname(user.getProfile().getUserFullname());
		profile.setUserGender(user.getProfile().getUserGender());
		profile.setUserAddress(user.getProfile().getUserAddress());
		profile.setUserEmail(user.getUserEmail());
		profile.setRoleId(user.getRole().getId());
		profile.setRoleCode(user.getRole().getRoleCode());
		profile.setRoleName(user.getRole().getRoleName());
		profile.setIsActive(user.getIsActive());

		return profile;
	}

	@Transactional
	@Override
	public InsertResDto insert(UserInsertReqDto userInsertReqDto) {

		InsertResDto result = new InsertResDto();

		final File file = new File();
		file.setFile(userInsertReqDto.getFile());
		file.setFileExtension(userInsertReqDto.getFileExtension());
		file.setCreatedBy(principalService.getPrincipal());
		final File photo = fileDao.insert(file);

		final UserProfile userProfile = new UserProfile();
		final String randomCode = generateID(5);
		userProfile.setUserIdNumber(randomCode);
		userProfile.setUserFullname(userInsertReqDto.getUserFullName());
		userProfile.setUserGender(userInsertReqDto.getUserGender());
		userProfile.setUserAddress(userInsertReqDto.getUserAddress());
		userProfile.setCreatedBy(principalService.getPrincipal());
		userProfile.setFile(photo);
		final UserProfile profile = userProfileDao.insert(userProfile);

		final User user = new User();
		final String randomPassword = generateID(5);
		user.setUserEmail(userInsertReqDto.getUserEmail());
		final String passwordEncoded = passwordEncoder.encode(randomPassword);
		user.setUserPassword(passwordEncoded);

		final UserRole role = userRoleDao.getRoleById(userInsertReqDto.getRoleId());
		user.setRole(role);
		user.setProfile(profile);
		user.setCreatedBy(principalService.getPrincipal());
		userDao.insert(user);

		final String subject = "New Account Registered on AMS";
		final String body = "Here is your Credentials\n" + "Email: " + userInsertReqDto.getUserEmail() + "\nPassword: "
				+ randomPassword;
		mailService.sendMail(userInsertReqDto.getUserEmail(), subject, body);
		result.setId(user.getId());
		result.setMessage("Insert User Success!");
		return result;
	}

	@Override
	public UserProfile updatePicture(Long profileId, File file) {
		UserProfile profile = null;

		try {
			this.em.getTransaction().begin();
			final File newPicture = fileDao.insert(file);
			profile = userProfileDao.getByID(profileId);

			final Long fileId = profile.getFile().getId();

			profile.setFile(newPicture);

			fileDao.deleteByID(fileId);

			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}

		return profile;
	}

	@Override
	public List<UserResDto> getByRoleCode(String roleCode) {
		final List<UserResDto> users = new ArrayList<>();

		userDao.getByRoleCode(roleCode).forEach(u -> {
			final UserResDto user = new UserResDto();
			user.setUserFullName(u.getProfile().getUserFullname());
			user.setRoleName(u.getRole().getRoleName());
			user.setRoleCode(u.getRole().getRoleCode());
			users.add(user);
		});

		return users;
	}

	@Override
	public List<UserRoleResDto> getAllRoles() {
		final List<UserRoleResDto> roles = new ArrayList<>();

		userRoleDao.getAll().forEach(r -> {
			final UserRoleResDto role = new UserRoleResDto();
			role.setId(r.getId());
			role.setRoleCode(r.getRoleCode());
			role.setRoleName(r.getRoleName());
			roles.add(role);
		});

		return roles;
	}

	@Override
	public LoginResDto login(LoginReqDto credentials) {
		final User userlog = userDao.getByUsername(credentials.getUserEmail());

		final LoginResDto result = new LoginResDto();
		result.setId(userlog.getId());
		result.setUserFullname(userlog.getProfile().getUserFullname());
		result.setRoleCode(userlog.getRole().getRoleCode());
		result.setRoleName(userlog.getRole().getRoleName());
		result.setPhotoId(userlog.getProfile().getFile().getId());

		return result;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User user = userDao.getByUsername(username);

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(username, user.getUserPassword(),
					new ArrayList<>());
		}

		throw new UsernameNotFoundException(username);
	}

	@Transactional
	@Override
	public UpdateResDto isActive(UserUpdateIsActiveReqDto isActive) {
		final User user = userDao.getUserById(isActive.getUserId());
		user.setIsActive(isActive.getIsActive());
		user.setUpdatedBy(principalService.getPrincipal());

		em.flush();

		final UpdateResDto result = new UpdateResDto();
		result.setVersion(user.getVersion());
		if(user.getIsActive()) {			
			result.setMessage("User isActive changed to ACTIVE");
		} else {
			result.setMessage("User isActive changed to INACTIVE");
		}

		return result;
	}

	@SuppressWarnings("serial")
	public class PasswordMismatchException extends RuntimeException {
		public PasswordMismatchException(String message) {
			super(message);
		}
	}

	@Transactional
	@Override
	public UpdateResDto changePassword(UserPasswordChangeReqDto data) throws PasswordMismatchException {
		final User user = userDao.getUserById(data.getId());
		UpdateResDto result = new UpdateResDto();

		if (!passwordEncoder.matches(data.getCurrentPassword(), user.getUserPassword())) {
			throw new PasswordMismatchException("Old Password Incorrect");
		}

		final String newPasswordEncoded = passwordEncoder.encode(data.getNewPassword());
		user.setUserPassword(newPasswordEncoded);
		user.setUpdatedBy(principalService.getPrincipal());
		em.flush();

		result.setVersion(user.getVersion());
		result.setMessage("Password Changed");
		return result;

	}

	@Transactional
	@Override
	public UpdateResDto updateProfile(UserProfileUpdateReqDto data) {
		final User user = userDao.getUserById(data.getId());
		user.setId(data.getId());
		user.setUserEmail(data.getUserEmail());
		
		final UserRole role = userRoleDao.getRoleById(data.getId());
		user.setRole(role);
		
		final UserProfile profile = new UserProfile();
		profile.setUserFullname(data.getUserFullName());
		profile.setUserGender(data.getUserGender());
		profile.setUserAddress(data.getUserAddress());
		
		if (data.getFile() != null) {
			final long fileID = data.getFileId();
			final File file = new File();
			file.setFile(data.getFile());
			file.setFileExtension(data.getFileExtension());
			file.setCreatedBy(principalService.getPrincipal());
			fileDao.insert(file);
			profile.setFile(file);
			user.setProfile(profile);
			
			fileDao.deleteByID(fileID);
		}
		
		user.setUpdatedBy(principalService.getPrincipal());
		em.flush();
		
		
		final UpdateResDto result = new UpdateResDto();
		result.setVersion(user.getVersion());
		result.setMessage("Profile has been updated!");
		
		return result;
	}

}
