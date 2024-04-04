package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.RoleBO;
import com.issue.issuefeedbacksystem.entity.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleDAO {
    @Select("select role_id, role_name from role where is_delete != 1")
    @Results(id = "role", value = {
            @Result(property = "roleId", column = "role_id"),
            @Result(property = "roleName", column = "role_name")
    })
    List<RoleBO> selectRoleListNotDelete();

    @Insert("insert into role(role_name) values (#{roleName})")
    int insertRoleName(String roleName);

    @Update("update role set role_name = #{roleName} where role_id = #{roleId}")
    int updateRoleName(@Param("roleName") String roleName, @Param("roleId") Integer roleId);

    @Update({"<script> UPDATE role SET is_delete = 1, role_name = '已删除'"
            + "WHERE role_id IN "
            + "<foreach  collection = 'ids' item = 'id' index = 'index' open = '(' separator= ',' close = ')' >"
            + "	#{id} "
            + "</foreach>"
            + "</script>"})
    int batchDeleteRole(@Param("ids") List<Integer> ids);

    @Select("select role_id, role_name from role where role_id = #{roleId}")
    @ResultMap(value = "role")
    Role selectRoleById(Integer roleId);
}
