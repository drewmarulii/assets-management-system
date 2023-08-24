CREATE TABLE file (
	id serial,
	file TEXT NOT NULL,
	file_extension VARCHAR(5) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE file ADD CONSTRAINT file_pk
	PRIMARY KEY(id);

CREATE TABLE user_role (
	id serial,
	role_code VARCHAR(5) NOT NULL,
	role_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_role ADD CONSTRAINT user_role_pk
	PRIMARY KEY(id);
ALTER TABLE user_role ADD CONSTRAINT user_role_bk
	UNIQUE(role_code);
	
CREATE TABLE user_profile (
	id serial,
	user_id_number VARCHAR(20) NOT NULL,
	user_fullname VARCHAR(50) NOT NULL,
	user_gender VARCHAR(6) NOT NULL,
	user_address VARCHAR(80) NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_profile ADD CONSTRAINT user_profile_pk
	PRIMARY KEY(id);
ALTER TABLE user_profile ADD CONSTRAINT user_picture_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE user_profile ADD CONSTRAINT user_profile_bk
	UNIQUE(user_id_number);
	
CREATE TABLE user_account (
	id serial,
	user_email VARCHAR(50) NOT NULL,
	user_password TEXT NOT NULL,
	profile_id int NOT NULL,
	role_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE user_account ADD CONSTRAINT user_account_pk
	PRIMARY KEY(id);
ALTER TABLE user_account ADD CONSTRAINT user_account_email_bk
	UNIQUE(user_email);
ALTER TABLE user_account ADD CONSTRAINT user_account_profile_fk
	FOREIGN KEY(profile_id)
	REFERENCES user_profile(id);
ALTER TABLE user_account ADD CONSTRAINT user_account_role_fk
	FOREIGN KEY(role_id)
	REFERENCES user_role(id);

CREATE TABLE company (
	id serial,
	company_name VARCHAR(50) NOT NULL,
	company_address VARCHAR(80) NOT NULL,
	company_phone VARCHAR(13) NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE company ADD CONSTRAINT company_pk
	PRIMARY KEY(id);
ALTER TABLE company ADD CONSTRAINT company_phone_bk
	UNIQUE(company_phone);
ALTER TABLE company ADD CONSTRAINT company_file_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE asset_type ( 
	id serial,
	type_code VARCHAR(10) NOT NULL,
	type_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE asset_type ADD CONSTRAINT asset_type_pk
	PRIMARY KEY(id);
ALTER TABLE asset_type ADD CONSTRAINT asset_type_bk
	UNIQUE(type_code);

CREATE TABLE asset_status ( 
	id serial, 
	status_code VARCHAR(10) NOT NULL,
	status_name VARCHAR(20) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE asset_status ADD CONSTRAINT asset_status_pk
	PRIMARY KEY(id);
ALTER TABLE asset_status ADD CONSTRAINT asset_status_bk
	UNIQUE(status_code);

CREATE TABLE supplier ( 
	id serial,
	supplier_code VARCHAR(10) NOT NULL,
	supplier_name VARCHAR(50) NOT NULL,
	supplier_phone VARCHAR(15) NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE supplier ADD CONSTRAINT supplier_pk
	PRIMARY KEY(id);
ALTER TABLE supplier ADD CONSTRAINT supplier_code_bk
	UNIQUE(supplier_code);
ALTER TABLE supplier ADD CONSTRAINT supplier_phone_bk
	UNIQUE(supplier_phone);
ALTER TABLE supplier ADD CONSTRAINT supplier_file_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE invoices ( 
	id serial,
	invoice_code VARCHAR(10) NOT NULL,
	invoice_date DATE NOT NULL,
	file_id int NOT NULL,
	supplier_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE invoices ADD CONSTRAINT invoices_pk
	PRIMARY KEY(id);
ALTER TABLE invoices ADD CONSTRAINT invoices_code_bk
	UNIQUE(invoice_code);
ALTER TABLE invoices ADD CONSTRAINT inv_detail_supplier_fk
	FOREIGN KEY(supplier_id)
	REFERENCES supplier(id);
ALTER TABLE invoices ADD CONSTRAINT invoices_file_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE provider ( 
	id serial,
	provider_code VARCHAR(10) NOT NULL,
	provider_name VARCHAR(50) NOT NULL,
	file_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE provider ADD CONSTRAINT provider_pk
	PRIMARY KEY(id);
ALTER TABLE provider ADD CONSTRAINT provider_code_bk
	UNIQUE(provider_code);
ALTER TABLE provider ADD CONSTRAINT provider_file_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);

CREATE TABLE invoice_detail ( 
	id serial,
	detail_code VARCHAR(10) NOT NULL,
	item_name VARCHAR(50) NOT NULL,
	provider_id int NOT NULL,
	invoice_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE invoice_detail ADD CONSTRAINT inv_detail_pk
	PRIMARY KEY(id);
ALTER TABLE invoice_detail ADD CONSTRAINT inv_detail_code_bk
	UNIQUE(detail_code);
ALTER TABLE invoice_detail ADD CONSTRAINT inv_detail_provider_fk
	FOREIGN KEY(provider_id)
	REFERENCES provider(id);
ALTER TABLE invoice_detail ADD CONSTRAINT inv_detail_invoice_fk
	FOREIGN KEY(invoice_id)
	REFERENCES invoices(id);

CREATE TABLE assets (
	id serial,
	asset_code VARCHAR(10) NOT NULL,
	asset_name VARCHAR(50) NOT NULL,
	type_id int NOT NULL,
	status_id int NOT NULL,
	company_id int NOT NULL,
	file_id int NOT NULL,
	inv_detail_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE assets ADD CONSTRAINT assets_pk
	PRIMARY KEY(id);
ALTER TABLE assets ADD CONSTRAINT assets_type_fk
	FOREIGN KEY(type_id)
	REFERENCES asset_type(id);
ALTER TABLE assets ADD CONSTRAINT assets_status_fk
	FOREIGN KEY(status_id)
	REFERENCES asset_status(id);
ALTER TABLE assets ADD CONSTRAINT assets_file_fk
	FOREIGN KEY(file_id)
	REFERENCES file(id);
ALTER TABLE assets ADD CONSTRAINT assets_company_fk
	FOREIGN KEY(company_id)
	REFERENCES company(id);
ALTER TABLE assets ADD CONSTRAINT assets_invoice_fk
	FOREIGN KEY(inv_detail_id)
	REFERENCES invoice_detail(id);

CREATE TABLE employee ( 
	id serial,
	employee_code VARCHAR(10) NOT NULL,
	employee_name VARCHAR(50) NOT NULL,
	company_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE employee ADD CONSTRAINT employee_pk
	PRIMARY KEY(id);
ALTER TABLE employee ADD CONSTRAINT employee_company_fk
	FOREIGN KEY(company_id)
	REFERENCES company(id);
ALTER TABLE employee ADD CONSTRAINT employee_bk
	UNIQUE(employee_code);

CREATE TABLE checkout_type ( 
	id serial,
	c_type_code VARCHAR(10) NOT NULL,
	c_type_name VARCHAR(30) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE checkout_type ADD CONSTRAINT c_type_pk
	PRIMARY KEY(id);
ALTER TABLE checkout_type ADD CONSTRAINT c_type_code_bk
	UNIQUE(c_type_code);

CREATE TABLE location (
	id serial,
	location_number VARCHAR(10) NOT NULL,
	location_detail VARCHAR(50) NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE location ADD CONSTRAINT c_location_pk
	PRIMARY KEY(id);
ALTER TABLE location ADD CONSTRAINT c_location_bk
	UNIQUE(location_number);

CREATE TABLE checkout ( 
	id serial,
	checkout_code VARCHAR(10) NOT NULL,
	employee_id int,
	location_id int,
	asset_id int,
	type_id int NOT NULL,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE checkout ADD CONSTRAINT checkout_pk
	PRIMARY KEY(id);
ALTER TABLE checkout ADD CONSTRAINT checkout_code_bk
	UNIQUE(checkout_code);
ALTER TABLE checkout ADD CONSTRAINT checkout_employee_fk
	FOREIGN KEY(employee_id)
	REFERENCES employee(id);
ALTER TABLE checkout ADD CONSTRAINT checkout_type_fk
	FOREIGN KEY(type_id)
	REFERENCES checkout_type(id);
ALTER TABLE checkout ADD CONSTRAINT checkout_location_fk
	FOREIGN KEY(location_id)
	REFERENCES location(id);
ALTER TABLE checkout ADD CONSTRAINT checkout_asset_fk
	FOREIGN KEY(asset_id)
	REFERENCES assets(id);

CREATE TABLE checkout_details ( 
	id serial, 
	asset_id int NOT NULL,
	checkout_id int NOT NULL,
	checkout_date timestamp NOT NULL,
	due_date timestamp,
	checkin_date timestamp,
	created_by int NOT NULL,
	created_at timestamp NOT NULL,
	updated_by int,
	updated_at timestamp,
	is_active boolean NOT NULL,
	version int NOT NULL
);

ALTER TABLE checkout_details ADD CONSTRAINT c_details_pk
	PRIMARY KEY(id);
ALTER TABLE checkout_details ADD CONSTRAINT c_details_asset_fk
	FOREIGN KEY(asset_id)
	REFERENCES assets(id);
ALTER TABLE checkout_details ADD CONSTRAINT c_details_checkout_fk
	FOREIGN KEY(checkout_id)
	REFERENCES checkout(id);