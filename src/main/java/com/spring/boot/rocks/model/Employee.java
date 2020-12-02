package com.spring.boot.rocks.model;

import java.util.List;

import lombok.Data;

@Data
public class Employee {

	private String name;
	private String dateOfBirth;
	private String gender;
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String state;
	private String city;
	private String zipCode;
	private String mobile;
	private String email;
	private List<String> skills;
	private String biography;
	private String website;
}
