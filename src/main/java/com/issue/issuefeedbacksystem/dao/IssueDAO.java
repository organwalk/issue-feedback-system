package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.bo.IssueBO;
import com.issue.issuefeedbacksystem.bo.IssueDetailsBO;
import com.issue.issuefeedbacksystem.entity.Issue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IssueDAO {
    @Insert("insert into issue (user_id,type_id,title,`desc`,status_id) " +
            "values(#{issue.userId},#{issue.typeId},#{issue.title},#{issue.desc},#{issue.statusId})")
    int insertIssue(@Param("issue") Issue issue);

    @Select("select status_id from issue where issue_id = #{issueId}")
    Integer selectStatusById(Integer issueId);

    @Update("update issue set status_id = #{status} where issue_id = #{issueId}")
    int updateIssueStatusById(@Param("issueId") Integer issueId, @Param("status") Integer status);

    @Select("select issue.*,ic.type_name,i.status_name from issue left join issue_category ic on ic.type_id = issue.type_id left join issue_status i on i.status_id = issue.status_id where issue_id = #{issueId}")
    IssueDetailsBO selectById(Integer issueId);

    @Select("select count(issue_id) from issue where status_id = #{issueStatus}")
    Integer countNumsByStatus(Integer issueStatus);

    @Select("select i.issue_id,i.title,i.desc,i.type_id,ic.type_name " +
            "from issue i " +
            "left join issue_category ic on ic.type_id = i.type_id " +
            "where i.status_id = #{issueStatus} " +
            "order by i.issue_id limit #{size} offset #{offset}")
    List<IssueBO> selectIssueListByStatus(@Param("issueStatus") Integer issueStatus, @Param("size") Integer size, @Param("offset") Integer offset);

    @Update({
            "<script>",
            "UPDATE issue",
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
