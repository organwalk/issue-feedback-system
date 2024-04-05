package com.issue.issuefeedbacksystem.service;

import com.issue.issuefeedbacksystem.dto.IssueCategoryBatchUpdateDeptDTO;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;

import java.util.List;

public interface IssueCategoryService {
    PagedResult<?> getIssueCategoryList(Integer size, Integer offset);
    PagedResult<?> getIssueCategoryListByDept(Integer size, Integer offset, Integer deptId);
    PagedResult<?> getIssueCategoryListByName(Integer size, Integer offset, String typeName);
    MsgResult addIssueCategory(String typeName, Integer deptId);
    MsgResult updateIssueCategory(String typeName, Integer deptId, Integer typeId);
    MsgResult batchUpdateIssueCategoryDept(IssueCategoryBatchUpdateDeptDTO issueCategoryBatchUpdateDeptDTO);
    MsgResult deleteIssueCategory(List<Integer> typeIdList);
}
