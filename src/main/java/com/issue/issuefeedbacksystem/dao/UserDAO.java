package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.DeptBO;
import com.issue.issuefeedbacksystem.bo.PendingUserBO;
import com.issue.issuefeedbacksystem.bo.RoleBO;
import com.issue.issuefeedbacksystem.bo.UserBO;
import com.issue.issuefeedbacksystem.dto.*;
import com.issue.issuefeedbacksystem.entity.Dept;
import com.issue.issuefeedbacksystem.entity.Role;
import com.issue.issuefeedbacksystem.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {
    @Insert("insert into user(username, password_hash, phone) values (#{user.realName}, #{user.password}, #{user.phone})")
    int insertUser(@Param("user") UserRegistrationDTO userRegistrationDTO);

    @Select("SELECT u.user_id, u.username, u.password_hash, " +
            "CASE WHEN r.is_delete = 1 THEN NULL ELSE u.role_id END as role_id, " +
            "u.dept_id, u.phone " +
            "FROM user u " +
            "LEFT JOIN role r ON u.role_id = r.role_id " +
            "WHERE u.phone = #{phone} AND u.is_delete = 0")

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
            "WHERE u.is_delete = 0 and (u.role_id IS NULL or r.is_delete = 1) limit #{size} offset #{offset}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "phone", column = "phone")
    })
    List<PendingUserBO> selectPendingUserList(@Param("size") Integer size,
                                              @Param("offset") Integer offset);
    @Update({
            "<script>",
            "update user set role_id = #{data.roleId} where user_id in ",
            "<foreach collection='data.userIdList' item='uid' open='(' close=')' separator=','>",
            "#{uid}",
            "</foreach>",
            "</script>"
    })
    int batchUpdateUserRole(@Param("data") UserBatchUpdateRoleDTO userBatchUpdateRoleDTO);


    @Select("select count(user_id) from user")
    Integer countUserSum();

    @Select("select user_id, username, role_id, dept_id, phone from user order by user_id desc limit #{size} offset #{offset} ")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "role", column = "role_id", javaType = RoleBO.class,
                    one = @One(select = "com.issue.issuefeedbacksystem.dao.RoleDAO.selectRoleById")),
            @Result(property = "dept", column = "dept_id", javaType = DeptBO.class,
                    one = @One(select = "com.issue.issuefeedbacksystem.dao.DeptDAO.selectDeptById")),
            @Result(property = "phone", column = "phone")
    })
    List<UserBO> selectUserList(@Param("size") Integer size, @Param("offset") Integer offset);

    @Update({
            "<script>",
            "update user <set>",
            "<if test = 'user.username != null' >",
            "username = #{user.username},",
            "</if>",
            "<if test = 'user.passwordHash != null' >",
            "password_hash = #{user.passwordHash},",
            "</if>",
            "<if test = 'user.roleId != null' >",
            "role_id = #{user.roleId},",
            "</if>",
            "<if test = 'user.deptId != null' >",
            "dept_id = #{user.deptId},",
            "</if>",
            "<if test = 'user.phone != null' >",
            "phone = #{user.phone} ",
            "</if>",
            "</set> where user_id = #{user.userId}",
            "</script>"
    })
    int updateUser(@Param("user") UserUpdateDTO userUpdateDTO);

    @Update({
            "<script>",
            "update user  set dept_id = #{data.deptId} ",
            "where is_delete = 0 and (role_id = 2 or role_id = 3) and user_id in ",
            "<foreach collection='data.userIdList' item='uid' open='(' close=')' separator=','>",
            "#{uid}",
            "</foreach>",
            "</script>"
    })
    int batchUpdateUserDept(@Param("data")UserBatchUpdateDeptDTO userBatchUpdateDeptDTO);
}
