package com.karina.webflux;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Repository
public interface StudentRepository extends ReactiveCrudRepository<Student,Integer>{

//	Mono<Student> findByS_id(int s_id);

	Flux<Student> findByName(String name);
	
	@Query("select * from student where city=:city")
	Flux<Student> findByCity(String city);
}
