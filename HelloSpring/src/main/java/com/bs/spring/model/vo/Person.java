package com.bs.spring.model.vo;

import lombok.Data;

@Data
public class Person {
	private String name;
	private int age;
	private Animal myAnimal;
}
