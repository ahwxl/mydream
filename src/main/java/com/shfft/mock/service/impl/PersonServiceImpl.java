package com.shfft.mock.service.impl;

import com.shfft.mock.service.PersonService;

public class PersonServiceImpl implements PersonService {

	@Override
	public String sayName(String string) {
		return "hi " + string + "!";
	}
	
}
