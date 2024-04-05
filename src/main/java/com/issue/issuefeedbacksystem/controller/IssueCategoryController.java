package com.issue.issuefeedbacksystem.controller;

import com.issue.issuefeedbacksystem.dto.IssueCategoryBatchUpdateDeptDTO;
import com.issue.issuefeedbacksystem.service.IssueCategoryService;
import com.issue.issuefeedbacksystem.vo.MsgResult;
import com.issue.issuefeedbacksystem.vo.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class IssueCategoryController {
    private final IssueCategoryService issueCategoryService;

    @GetMapping("/issue-category")
    public PagedResult<?> getIssueCategoryList(@Valid
                                               @Min(value = 1, message = "size不能小于1")
                                               @RequestParam(value = "size") Integer size,
                                               @Min(value = 0, message = "offset不能小于0")
                                               @RequestParam(value = "offset") Integer offset) {
        return issueCategoryService.getIssueCategoryList(size, offset);
    }

    @GetMapping("/issue-category/dept")
    public PagedResult<?> getIssueCategoryListByDept(@Valid
                                                     @Min(value = 1, message = "size不能小于1")
                                                     @RequestParam(value = "size") Integer size,
                                                     @Min(value = 0, message = "offset不能小于0")
                                                     @RequestParam(value = "offset") Integer offset,
                                                     @Min(value = 1, message = "deptId不能小于0")
                                                     @RequestParam(value = "deptId") Integer deptId) {
        return issueCategoryService.getIssueCategoryListByDept(size, offset, deptId);
    }

    @GetMapping("/issue-category/name")
    public PagedResult<?> getIssueCategoryListByName(@Valid
                                                     @Min(value = 1, message = "size不能小于1")
                                                     @RequestParam(value = "size") Integer size,
                                                     @Min(value = 0, message = "offset不能小于0")
                                                     @RequestParam(value = "offset") Integer offset,
                                                     @NotBlank(message = "typeName不能为空")
                                                     @RequestParam(value = "typeName") String typeName) {
        return issueCategoryService.getIssueCategoryListByName(size, offset, typeName);
    }

    @PostMapping("/system/issue-category")
    public MsgResult addIssueCategory(@Valid @NotBlank(message = "typeName不能为空")
                                      @RequestParam(value = "typeName") String typeName,
                                      @Min(value = 1, message = "deptId不能小于1")
                                      @RequestParam(value = "deptId") Integer deptId) {
        return issueCategoryService.addIssueCategory(typeName, deptId);
    }

    @PutMapping("/system/issue-category")
    public MsgResult updateIssueCategoryByTypeId(@Valid @NotBlank(message = "typeName不能为空")
                                                 @RequestParam(value = "typeName", required = false) String typeName,
                                                 @Min(value = 1, message = "deptId不能小于1")
                                                 @RequestParam(value = "deptId", required = false) Integer deptId,
                                                 @Min(value = 1, message = "typeId不能小于1")
                                                 @RequestParam Integer typeId) {
        return issueCategoryService.updateIssueCategory(typeName, deptId, typeId);
    }

    @PutMapping("/system/issue-categories")
    public MsgResult batchUpdateIssueCategoryDept(@Validated @RequestBody IssueCategoryBatchUpdateDeptDTO dto){
        return issueCategoryService.batchUpdateIssueCategoryDept(dto);
    }

    @DeleteMapping("/system/issue-categories")
    public MsgResult deleteIssueCategoryByIdList(@RequestParam List<Integer> typeIdList){
        return issueCategoryService.deleteIssueCategory(typeIdList);
    }
}
