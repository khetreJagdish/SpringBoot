package com.springbootexample.rest.webservices.restfulwebservices.versioning;

public class PersonV2 {
	
	public Name name;

	public PersonV2() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}


	
	
}
