package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.bo.UserBO;
import com.issue.issuefeedbacksystem.dto.PendingUserRoleDTO;
import com.issue.issuefeedbacksystem.dto.UserRegistrationDTO;
import com.issue.issuefeedbacksystem.entity.Dept;
import com.issue.issuefeedbacksystem.entity.Role;
import com.issue.issuefeedbacksystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("SELECT count(u.user_id) " +
            "FROM user u " +
            "LEFT JOIN role r ON u.role_id = r.role_id " +
            "WHERE u.role_id IS NULL or r.is_delete = 1")
    Integer countPendingUserSum();
    @Select("SELECT u.user_id, u.username, r.role_name AS role_name, u.phone  " +
            "FROM user u " +
            "LEFT JOIN role r ON u.role_id = r.role_id " +
            "WHERE u.role_id IS NULL or r.is_delete = 1 limit #{size} offset #{offset}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "phone", column = "phone")
    })
    List<PendingUserBO> selectPendingUserList(@Param("size") Integer size,
                                              @Param("offset") Integer offset);

    @Update("update user set role_id = #{role_id} where user_id = #{user_id}")
    void updateUserRoles(@Param("user_id") Integer userId, @Param("role_id") Integer roleId);


    @Select("select count(user_id) from user")
    Integer countUserSum();

    @Select("select user_id, username, role_id, dept_id, phone from user limit #{size} offset #{offset}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "role", column = "role_id", javaType = Role.class,
                    one = @One(select = "com.issue.issuefeedbacksystem.dao.RoleDAO.selectRoleById")),
            @Result(property = "dept", column = "dept_id", javaType = Dept.class,
                    one = @One(select = "com.issue.issuefeedbacksystem.dao.DeptDAO.selectDeptById")),
            @Result(property = "phone", column = "phone")
    })
    List<UserBO> selectUserList(@Param("size") Integer size, @Param("offset") Integer offset);

}
