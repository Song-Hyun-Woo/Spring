package com.bs.spring.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
	private String name;
	private int age;
	private String gender;
	private double weight;
	
	public Animal(String name, int age) {
		this.name=name;
		this.age=age;
	}
	
	
	public void initTest() {
		System.out.println("생성 후 실행하는 메소드");
	}
	
	public void destroyTest() {
		System.out.println("객체 소멸 후 실행하는 메소드");
	}
}

