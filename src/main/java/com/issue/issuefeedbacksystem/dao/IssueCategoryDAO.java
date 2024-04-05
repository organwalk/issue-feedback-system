package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.DeptBO;
import com.issue.issuefeedbacksystem.bo.IssueCategoryBO;
import com.issue.issuefeedbacksystem.dto.IssueCategoryBatchUpdateDeptDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueCategoryDAO {
    @Select("select count(type_id) from issue_category where is_delete = 0")
    Integer countIssueCategorySum();
    @Select("select type_id, type_name, dept_id from issue_category " +
            "where is_delete = 0 order by type_id desc limit #{size} offset #{offset}")
    @Results(id = "icList", value = {
            @Result(property = "typeId", column = "type_id"),
            @Result(property = "typeName", column = "type_name"),
            @Result(property = "dept", column = "dept_id", javaType = DeptBO.class,
                    one = @One(select = "com.issue.issuefeedbacksystem.dao.DeptDAO.selectDeptById"))
    })
    List<IssueCategoryBO> selectIssueCategoryList(@Param("size") Integer size, @Param("offset") Integer offset);

    @Select("select count(type_id) from issue_category where is_delete = 0 and dept_id = #{deptId}")
    Integer countIssueCategorySumByDept(Integer deptId);
    @Select("select type_id, type_name, dept_id from issue_category " +
            "where is_delete = 0 and dept_id = #{deptId} order by type_id desc limit #{size} offset #{offset}")
    @ResultMap(value = "icList")
    List<IssueCategoryBO> selectIssueCategoryListByDept(@Param("size") Integer size,
                                                        @Param("offset") Integer offset,
                                                        @Param("deptId") Integer deptId);


    @Select("select count(type_id) from issue_category where is_delete = 0 " +
            "and type_name like concat('%', #{typeName}, '%')")
    Integer countIssueCategorySumByName(String typeName);
    @Select("select type_id, type_name, dept_id from issue_category " +
            "where is_delete = 0 and type_name like concat('%', #{typeName}, '%') order by type_id desc limit #{size} offset #{offset}")
    @ResultMap(value = "icList")
    List<IssueCategoryBO> selectIssueCategoryListByName(@Param("size") Integer size,
                                                        @Param("offset") Integer offset,
                                                        @Param("typeName") String typeName);

    @Select("select type_id from issue_category where dept_id = #{deptId} and type_name = #{typeName}")
    Integer selectIssueCategoryIdByDeptIdAndName(@Param("deptId") Integer deptId,
                                                 @Param("typeName")String typeName);
    @Insert("insert into issue_category(type_name, dept_id) values (#{typeName}, #{deptId})")
    int addIssueCategory(@Param("typeName") String typeName, @Param("deptId") Integer deptId);

    @Update({
            "<script>",
            "update issue_category set ",
            "<if test = 'typeName != null' >",
            "type_name = #{typeName}",
            "</if>",
            "<if test = 'deptId != null' >",
            ",dept_id = #{deptId} ",
            "</if>",
            "where type_id = #{typeId} and is_delete = 0",
            "</script>"
    })
    int updateIssueCategoryByTypeId(@Param("typeName") String typeName,
                                    @Param("deptId") Integer deptId,
                                    @Param("typeId") Integer typeId);

    @Update({
            "<script>",
            "update issue_category set dept_id = #{data.deptId} where is_delete = 0 and type_id in ",
            "<foreach collection='data.typeIdList' item='typeId' open='(' close=')' separator=','>",
            "#{typeId}",
            "</foreach>",
            "</script>"
    })
    int batchUpdateIssueCategoryDept(@Param("data")IssueCategoryBatchUpdateDeptDTO dto);

    @Update({
            "<script>",
            "update issue_category set type_name = '已删除', is_delete = 1 where type_id in ",
            "<foreach collection='data' item='typeId' open='(' close=')' separator=','>",
            "#{typeId}",
            "</foreach>",
            "</script>"
    })
    int deleteIssueCategoryByIdList(@Param("data")List<Integer> idList);
}
