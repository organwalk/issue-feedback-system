package com.issue.issuefeedbacksystem.dao;

import com.issue.issuefeedbacksystem.dto.EvaluationDTO;
import com.issue.issuefeedbacksystem.entity.Evaluation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EvaluationDAO {
    @Insert("insert into evaluation (issue_id, rating, comment) " +
            "values (#{evaluation.issueId},#{evaluation.rating},#{evaluation.comment})")
    int insertEvaluation(@Param("evaluation") EvaluationDTO evaluationDTO);

    @Select("select * from evaluation where issue_id = #{issueId}")
    Evaluation selectByIssueId(Integer issueId);
}
