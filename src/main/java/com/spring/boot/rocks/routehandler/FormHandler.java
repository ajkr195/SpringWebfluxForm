package com.spring.boot.rocks.routehandler;

import static com.spring.boot.rocks.constant.AppConstant.*;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.spring.boot.rocks.model.Employee;

import reactor.core.publisher.Mono;

@Component
public class FormHandler {

	public Mono<ServerResponse> sampleForm(ServerRequest request) {

		return ServerResponse.ok().render(FORM);
	}

	public Mono<ServerResponse> displayFormData(ServerRequest request) {
		
		Mono<MultiValueMap<String, String>> formData = request.formData();
		
		// BodyExtractor based. It didn't result any value for our program
		// It looks any earlier piece of code (Filter ?) already accessed the body
		// making it empty.
		
		// Mono<MultiValueMap<String, String>> formData = request.body(BodyExtractors.toFormData());		

		return ServerResponse.ok().render(DISPLAY_FORM_DATA, formDataToEmployee(formData));
	}

	private Employee formDataToEmployee(Mono<MultiValueMap<String, String>> formData) {

		Employee employee = new Employee();

		formData.subscribe(formDatamap -> {
			employee.setName(formDatamap.get(NAME).get(0));
			employee.setDateOfBirth(formDatamap.getFirst(DATE_OF_BIRTH));
			employee.setGender(formDatamap.getFirst(GENDER));
			employee.setAddressLine1(formDatamap.getFirst(ADDRESS_LINE_1));
			employee.setAddressLine2(formDatamap.getFirst(ADDRESS_LINE_2));
			employee.setCountry(formDatamap.getFirst(COUNTRY));
			employee.setState(formDatamap.getFirst(STATE));
			employee.setCity(formDatamap.getFirst(CITY));
			employee.setZipCode(formDatamap.getFirst(ZIP_CODE));
			employee.setMobile(formDatamap.getFirst(MOBILE));
			employee.setEmail(formDatamap.getFirst(EMAIL));
			employee.setSkills(formDatamap.get(SKILLS));
			employee.setWebsite(formDatamap.getFirst(WEBSITE));
			employee.setBiography(formDatamap.getFirst(BIOGRAPHY));
		});

		return employee;
	}
}
