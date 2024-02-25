package com.karina.webflux;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Netty server is embeded in eclipse bydefault
//add r2dbc,mariadb,reactive web and  webflux dependencies in pom.xml then project is created

@RestController
public class StudentController {
	@Autowired
	StudentRepository srepo;
	
	@RequestMapping("/test")
	public String test()
	{
		return "data tested";
	}
	@RequestMapping("/save")
	public String save()
	{
		Student s=new Student();
		s.setName("Ashok");
		s.setCity("Simdega");
		
		srepo.save(s).subscribe();//add subscribe otherwise it will not work
		return "data saved";
	}
	
	@RequestMapping("/all")
	public Flux<Student> all()
	{
		return srepo.findAll();
		
	}
	
/*	@RequestMapping(value="/{s_id}")
	Mono<Student> byid(@PathVariable int s_id)
	{
		return srepo.findByS_id(s_id);
	}*/
	
	@RequestMapping(value="/by/{name}")
	public Flux<Student> byname(@PathVariable String name)
	{
		return srepo.findByName(name);
	}
	
	@RequestMapping(value="/find/{city}")
	public Flux<Student> byonly(@PathVariable String city)
	{
		return srepo.findByCity(city);
	}
}
