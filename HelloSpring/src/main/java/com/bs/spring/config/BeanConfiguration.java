package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.model.vo.Animal;
import com.bs.spring.model.vo.Person;

//beanconfiguration클래스로 구현하기
@Configuration
public class BeanConfiguration {
	/* 제공하는 springbean은 메소드로 제공
	 * 객체를 반환하는 메소드를 구현 
	 */
	@Bean
	public Person getDongmin() {
		Person p=new Person();
		p.setName("이동민");
		p.setAge(28);
		p.setMyAnimal(cow());
		return p;
	}
	
	@Bean 
	@Qualifier("song")
	public Animal cow() {
		return new Animal("얼룩송아지",5,"여",100.5);
	}
	@Bean
	@Autowired
	public Person ujun(@Qualifier("song") Animal a) {
		Person p=new Person();
		p.setName("김유준");
		p.setAge(31);
		p.setMyAnimal(a);
		return p;
	}
}
