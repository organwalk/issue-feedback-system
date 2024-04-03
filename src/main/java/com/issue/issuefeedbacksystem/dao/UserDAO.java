package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDAO {
    @Insert("insert into user(username, password_hash, phone) values (#{user.realName}, #{user.password}, #{user.phone})")
    int insertUser(@Param("user") UserRegistrationDTO userRegistrationDTO);

    @Select("select user_id, username, password_hash, role_id, dept_id, phone from user where phone = #{phone}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "passwordHash", column = "password_hash"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "phone", column = "phone")
    })
    User selectUserByPhone(String phone);
}
