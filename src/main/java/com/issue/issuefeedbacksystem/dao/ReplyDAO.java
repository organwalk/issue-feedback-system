package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReplyDAO {
    @Insert("insert into reply (issue_id, user_id, content) values (#{issueId},#{userId},#{content}) ")
    int insertReply(Reply reply);
    @Select("select * from reply where issue_id = #{issueId}")
    List<Reply> selectReplyByIssueId(Integer issueId);
}
