<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service.impl;

import ${basePackage}.biz.dto.${table.className}DTO;
import ${basePackage}.biz.entity.${table.className}DO;
import ${basePackage}.biz.query.${table.className}Query;
import ${basePackage}.biz.service.${table.className}Service;
import ${basePackage}.service.mapper.${table.className}Mapper;
import ${basePackage}.common.annotation.RepeatableCommit;
import ${basePackage}.common.redis.RedisUtils;
import ${basePackage}.framework.result.CodeEnum;
import ${basePackage}.framework.result.Result;
import ${basePackage}.framework.result.ResultPage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

<#include "/src/main/resources/template/annotation.include"/>
@EnableTransactionManagement
@Slf4j
@Service
public class ${table.className}ServiceImpl implements ${table.className}Service,Serializable {


    @Autowired
    private ${table.className}Mapper ${classNameLower}Mapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ResultPage list(${table.className}Query query) {
        query.setPageNo(query.getPageNo() == null ? 0 : query.getPageNo());
        query.setPageSize(query.getPageSize() == null ? 20 : query.getPageSize());
        if(StringUtils.isNotBlank(query.getName())){
            query.setName("%"+query.getName()+"%");
        }
        //使用分页插件,核心代码就这一行 #分页配置#
        PageHelper.startPage(query.getPageNo(), query.getPageSize());
        List<${table.className}DTO> list = ${classNameLower}Mapper.selectAll(query);
        PageInfo pageInfo = new PageInfo(list);
        if (CollectionUtils.isEmpty(list)) {
           return ResultPage.ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
        }
        return ResultPage.ok(pageInfo.getList(), pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
    }

    @RepeatableCommit
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result insert(${table.className}DO ${classNameLower}DO) {
        ${table.className}DTO old = null;
        try {
            if(${classNameLower}DO.getId()!=null){
                old = view(${classNameLower}DO.getId());
             }
            Result result = verifyUniqueness(${classNameLower}DO,old);
            if(CodeEnum.FAILED.getValue().equals(result.getCode())){
                 return result;
            }
            if(old == null){
                ${classNameLower}Mapper.insert(${classNameLower}DO);
            }else {
                ${classNameLower}Mapper.update(${classNameLower}DO);
            }
        }catch (Exception e){
            log.error("save error >>>>>>{}",ExceptionUtils.getStackTrace(e));
        }
        return Result.ok(${classNameLower}DO.getId());
    }

    private Result verifyUniqueness(${table.className}DO obj, ${table.className}DTO old) {
        if(StringUtils.isBlank(obj.getName())){
            return Result.fail("请检查名称是否存在空值");
        }
        List<${table.className}DO> existList = ${classNameLower}Mapper.getExistList(obj.getName());
        if(old !=null && CollectionUtils.isNotEmpty(existList)){
            //编辑排除自身
            existList = existList.stream().filter(ex -> !ex.getId().equals(old.getId())).collect(Collectors.toList());
        }
        if(CollectionUtils.isNotEmpty(existList)) {
            String error = "";
            for (${table.className}DO ex : existList) {
                if (obj.getName().equals(ex.getName())) {
                    error = "已存在";
                    break;
                }
            }
            return Result.fail(error);
        }
        return Result.ok(0);
    }

    @Override
    public Result delete(Integer id) {
        try {
            ${classNameLower}Mapper.delete(id);
        }catch (Exception e){
            log.error("delete error >>>>>>");
        }
        return Result.ok(id);
    }

    @Override
    public ${table.className}DTO view(Integer id) {
        ${table.className}DTO dto = ${classNameLower}Mapper.view(id);
        return  dto;

    }

    @Override
    public int batchSave${table.className}List(List<${table.className}DO> ${classNameLower}List) {
        ${classNameLower}Mapper.batchSave${table.className}List(${classNameLower}List);
        return 0;
    }
}
