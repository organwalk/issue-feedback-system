package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Issue;
import com.issue.issuefeedbacksystem.entity.IssueStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface IssueDAO
{

    @Select("select * from issue_status left join issue i on issue_status.status_id = i.status_id where i.issue_id=#{IssueId}")
    IssueStatus getIssueStatus(Integer IssueId);

    @Update({
            "<script>",
            "UPDATE issues",
            "<set>",
            "<if test='userId != null'>",
            "userId = #{userId},",
            "</if>",
            "<if test='typeId != null'>",
            "typeId = #{typeId},",
            "</if>",
            "<if test='title != null'>",
            "title = #{title},",
            "</if>",
            "<if test='desc != null'>",
            "`desc` = #{desc},",
            "</if>",
            "<if test='statusId != null'>",
            "statusId = #{statusId},",
            "</if>",
            "<if test='createDatetime != null'>",
            "createDatetime = #{createDatetime}",
            "</if>",
            "</set> WHERE issueId = #{issueId}",
            "</script>"
    })
    int updateIssue(Issue issue);
}
