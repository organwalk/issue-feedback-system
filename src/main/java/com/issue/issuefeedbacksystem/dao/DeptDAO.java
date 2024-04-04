package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DeptDAO {
    @Select("select dept_id, dept_name from dept where dept_id = #{deptId}")
    @Results(id = "dept", value = {
            @Result(property = "deptId", column = "dept_id"),
            @Result(property = "deptName", column = "dept_name")
    })
    Dept selectDeptById(Integer deptId);
}
