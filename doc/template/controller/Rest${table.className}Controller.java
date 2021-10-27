<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package com.fujieid.jai.api.controller;

import ${basePackage}.aspect.OperationLog;
import ${basePackage}.model.enums.LogType;
import com.fujieid.jai.security.bac.BacControl;
import com.fujieid.jai.security.bac.filter.${table.className}BacFilter;
import ${basePackage}.model.dto.${table.className}Dto;
import ${basePackage}.model.page.${table.className}Page;
import ${basePackage}.persistence.beans.${table.className};
import ${basePackage}.service.${table.className}Service;
import com.fujieid.core.common.exception.model.R;
import com.fujieid.core.common.utils.BeanConvertUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

<#include "/src/main/resources/template/annotation.include"/>
@RestController
@RequestMapping("/${table.classNameFirstLower}")
public class Rest${table.className}Controller {
    @Autowired
    private ${table.className}Service ${table.classNameFirstLower}Service;

    @PostMapping("/page")
    public R<PageInfo> page(@RequestBody ${table.className}Page param) {
        PageInfo pageInfo = ${table.classNameFirstLower}Service.listPage(param);
        return R.success(pageInfo);
    }

    @PostMapping
    @OperationLog(type = LogType.APPLICATION, contentTemplate = "添加${table.remark} [%s] ", param = {"#p0.appName"})
    public R<Object> add(@Valid @RequestBody ${table.className}Dto dto) {
        ${table.classNameFirstLower}Service.insert(BeanConvertUtil.doConvert(dto, ${table.className}.class));
        return R.success();
    }

    @BacControl(spel = "#id", filter = ${table.className}BacFilter.class)
    @DeleteMapping("{id}")
    @OperationLog(type = LogType.xx, contentTemplate = "删除${table.remark} [%s]", param = {"#p0"})
    public R<Object> delete(@PathVariable("id") String id) {
        return R.success(${table.classNameFirstLower}Service.removeByPrimaryKey(id));
    }

    @BacControl(spel = "#dto.id", filter = ${table.className}BacFilter.class)
    @PutMapping
    @OperationLog(type = LogType.xx, contentTemplate = "更新${table.remark} [%s](ID=[%s])", param = {"#p0.appName", "#p0.id"})
    public R<Object> update(@RequestBody ${table.className}Dto dto) {
        ${table.classNameFirstLower}Service.updateSelective(BeanConvertUtil.doConvert(dto, ${table.className}.class));
        return R.success();
    }

    @BacControl(spel = "#id", filter = ${table.className}BacFilter.class)
    @GetMapping("{id}")
    public R<Object> get(@PathVariable("id") String id) {
        ${table.className} ${table.classNameFirstLower} = ${table.classNameFirstLower}Service.getByPrimaryKey(id);
        return R.success(${table.classNameFirstLower});
    }

}
