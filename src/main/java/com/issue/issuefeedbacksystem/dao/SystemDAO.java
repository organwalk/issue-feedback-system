package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SystemDAO {
    @Select("select role_id, role_name from role")
    @Results({
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    List<Role> selectRoleList();
}
