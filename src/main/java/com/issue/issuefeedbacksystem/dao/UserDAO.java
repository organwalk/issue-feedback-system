package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
    @Insert("insert into user(username, password_hash, phone) values (#{user.realName}, #{user.password}, #{user.phone})")
    int insertUser(@Param("user") UserRegistrationDTO userRegistrationDTO);

    @Select("select user_id, username, password_hash, role_id, dept_id, phone from user where phone = #{phone}")
    @Results(id = "UserAllInfo", value = {
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "passwordHash", column = "password_hash"),
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "phone", column = "phone")
    })
    User selectUserByPhone(String phone);

    @Select("SELECT count(u.user_id) " +
            "FROM user u " +
            "LEFT JOIN role r ON u.role_id = r.role_id " +
            "WHERE u.role_id IS NULL")
    Integer countPendingUserSum();
    @Select("SELECT u.user_id, u.username, r.role_name AS role_name, u.phone  " +
            "FROM user u " +
            "LEFT JOIN role r ON u.role_id = r.role_id " +
            "WHERE u.role_id IS NULL limit #{size} offset #{offset}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "phone", column = "phone")
    })
    List<PendingUserBO> selectPendingUserList(@Param("size") Integer size,
                                              @Param("offset") Integer offset);
}
