package com.renting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.renting.model.User;

public interface UserRepository extends AbstractRepository<User> {

	User findFirstByLogin(String login);
	
	User findFirstByName(String name);
	
	List<User> findByName(String name);
	
	@Query("SELECT u FROM User u WHERE u.name = :name")
	User getDzikName(@Param("name") String name);
	
	@Query("SELECT u FROM User u, House h WHERE u.id = h.owner.id AND h.id = :houseID")
	User findByHouse(@Param("houseID") int houseID);
	
	@Query("SELECT CASE WHEN COUNT(re) > 0 THEN TRUE ELSE FALSE END"
			+ " FROM User u, Role re"
			+ " WHERE u.id = :userID"
			+ " AND UPPER(re.roleName) = UPPER('Administrator')"
			+ " AND re.id = u.role.id")
	boolean isAdministrator(@Param("userID") int userID);
}
