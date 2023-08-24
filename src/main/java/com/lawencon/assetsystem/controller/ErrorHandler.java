package com.lawencon.assetsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.assetsystem.dto.ErrorResDto;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorResDto<String>> handleNullPointerException(NullPointerException npe) {
		System.out.println(npe.getMessage());
		npe.printStackTrace();
		
		final ErrorResDto<String> resDto = new ErrorResDto<String>();
		resDto.setMessage(npe.getMessage());
		
		return new ResponseEntity<>(resDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<ErrorResDto<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException data) {
		final List<String> errors = data.getBindingResult().getFieldErrors()
			.stream()
			.map(v -> v.getDefaultMessage())
			.collect(Collectors.toList());
		
		final ErrorResDto<List<String>> resDto = new ErrorResDto<List<String>>();
		resDto.setMessage(errors);
		return new ResponseEntity<>(resDto, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class) 
	public ResponseEntity<ErrorResDto<String>> handleBadCredentialsException(BadCredentialsException data) {	
		final ErrorResDto<String> resDto = new ErrorResDto<String>();
		resDto.setMessage("Email & Password Wrong!");
		return new ResponseEntity<>(resDto, HttpStatus.BAD_REQUEST);
	}
		
}
