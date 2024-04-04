package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDAO {
    @Insert("insert into reply (issue_id, user_id, content) values (#{issueId},#{userId},#{content}) ")
    int insertReply(Reply reply);
}
