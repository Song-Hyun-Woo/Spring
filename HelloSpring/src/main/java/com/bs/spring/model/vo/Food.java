package com.bs.spring.model.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
//어노테이션을 이용해서 SpringBean으로 등록하기
@Component
public class Food {
	private String name;
	private int price;
	private String type;
	private Person p;
	
	@Autowired
	@Qualifier(value="yeonji")
	public void setPerson(Person p) {
		this.p=p;
	}
}





