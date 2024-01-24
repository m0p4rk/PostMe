package com.spring.postme.mapper;

import com.spring.postme.model.User;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    // 모든 사용자 조회
    List<User> findAll();

    // ID로 사용자 조회
    User findById(Integer id);

    // 사용자 추가
    void insertUser(User user);

    // 사용자 정보 업데이트
    void updateUser(User user);

    // ID로 사용자 삭제
    void deleteById(Integer id);

	User getUserbyUsernameAndPassword(@Param("username")String username,@Param("password") String password);

	int insertUsers(User newUser);
	

}
