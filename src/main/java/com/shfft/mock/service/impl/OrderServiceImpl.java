package com.shfft.mock.service.impl;

import org.springframework.stereotype.Service;

import com.shfft.mock.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Override
	public String print(String arg1) {
		System.out.println(arg1);
		return null;
	}

}
