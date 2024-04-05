package com.issue.issuefeedbacksystem.service.impl;

import com.issue.issuefeedbacksystem.bo.IssueCategoryBO;
import com.issue.issuefeedbacksystem.dao.IssueCategoryDAO;
import com.issue.issuefeedbacksystem.dto.IssueCategoryBatchUpdateDeptDTO;
import com.issue.issuefeedbacksystem.service.IssueCategoryService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class IssueCategoryServiceImpl implements IssueCategoryService {
    private final IssueCategoryDAO issueCategoryDAO;
    @Override
    public PagedResult<?> getIssueCategoryList(Integer size, Integer offset) {
        Integer total = issueCategoryDAO.countIssueCategorySum();
        if (total == 0) return PagedResult.fail("问题分类列表为空");
        List<IssueCategoryBO> list = issueCategoryDAO.selectIssueCategoryList(size, offset);
        return !list.isEmpty()
                ? PagedResult.success("成功获取问题分类列表", total, list)
                : PagedResult.success("当前记录后数据为空", 0, null);
    }

    @Override
    public PagedResult<?> getIssueCategoryListByDept(Integer size, Integer offset, Integer deptId) {
        Integer total = issueCategoryDAO.countIssueCategorySumByDept(deptId);
        if (total == 0) return PagedResult.fail("问题分类列表为空");
        List<IssueCategoryBO> list = issueCategoryDAO.selectIssueCategoryListByDept(size, offset, deptId);
        return !list.isEmpty()
                ? PagedResult.success("成功获取问题分类列表", total, list)
                : PagedResult.success("当前记录后数据为空", 0, null);
    }

    @Override
    public PagedResult<?> getIssueCategoryListByName(Integer size, Integer offset, String typeName) {
        Integer total = issueCategoryDAO.countIssueCategorySumByName(typeName);
        if (total == 0) return PagedResult.fail("问题分类列表为空");
        List<IssueCategoryBO> list = issueCategoryDAO.selectIssueCategoryListByName(size, offset, typeName);
        return !list.isEmpty()
                ? PagedResult.success("成功获取问题分类列表", total, list)
                : PagedResult.success("当前记录后数据为空", 0, null);
    }

    @Override
    public MsgResult addIssueCategory(String typeName, Integer deptId) {
        Integer typeId = issueCategoryDAO.selectIssueCategoryIdByDeptIdAndName(deptId, typeName);
        if (typeId != null) return MsgResult.fail("添加失败，当前部门下该问题分类已存在");
        int row = issueCategoryDAO.addIssueCategory(typeName, deptId);
        return row > 0 ? MsgResult.success("添加成功") : MsgResult.fail("添加失败");
    }

    @Override
    public MsgResult updateIssueCategory(String typeName, Integer deptId, Integer typeId) {
        Integer typeIdMark = issueCategoryDAO.selectIssueCategoryIdByDeptIdAndName(deptId, typeName);
        if (typeIdMark != null) return MsgResult.fail("修改失败，当前部门下该问题分类已存在");
        int row = issueCategoryDAO.updateIssueCategoryByTypeId(typeName, deptId, typeId);
        return row > 0 ? MsgResult.success("修改成功") : MsgResult.fail("修改失败");
    }

    @Override
    public MsgResult batchUpdateIssueCategoryDept(IssueCategoryBatchUpdateDeptDTO issueCategoryBatchUpdateDeptDTO) {
        int row = issueCategoryDAO.batchUpdateIssueCategoryDept(issueCategoryBatchUpdateDeptDTO);
        int invalid = issueCategoryBatchUpdateDeptDTO.getTypeIdList().size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "修改成功" : "修改成功, 无效修改" + invalid +"个")
                : MsgResult.fail("修改失败, 无效修改" + invalid +"个");
    }

    @Override
    public MsgResult deleteIssueCategory(List<Integer> typeIdList) {
        int row = issueCategoryDAO.deleteIssueCategoryByIdList(typeIdList);
        int invalid = typeIdList.size() - row;
        return row > 0 ? MsgResult.success(invalid == 0 ? "删除成功" : "删除成功, 无效删除" + invalid +"个")
                : MsgResult.fail("删除失败, 无效删除" + invalid +"个");
    }
}
