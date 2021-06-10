package com.blogger.io.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.blogger.io.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
	//in spring boot framework, we need to properly name method
	//if we want to select the method, then we need to use find (example: findByEmail || findByUserId)
	//Cannot find another keywords , this is associated with database (findBy = select ,Email || UserId = attribute in db)
	//so spring data gpa will query the sql for us
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
}
