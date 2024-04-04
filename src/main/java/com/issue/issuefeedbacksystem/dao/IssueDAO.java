package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Issue;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IssueDAO {
    @Insert("insert into issue (user_id,type_id,title,desc,status_id) " +
            "values(#{userId},#{typeId},#{title},#{desc},#{statusId})")
    int insertIssue(Issue issue);

    @Select("select status_id from issue where issue_id = #{issueId}")
    Integer selectStatusById(Integer issueId);

    @Update("update issue set status_id = #{status} where issue_id = #{issueId}")
    int updateIssueStatusById(@Param("issueId") Integer issueId, @Param("status") Integer status);
}
