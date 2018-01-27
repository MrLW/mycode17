package com.lw.jibx;

import lombok.Data;

@Data
public class Account {
	private int id;
	private String name;
	private String email;
	private String address;
	private Birthday birthday;
}
