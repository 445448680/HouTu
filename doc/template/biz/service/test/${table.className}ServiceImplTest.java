<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.impl;

import com.fujieid.jai.biz.BaseTest;
import ${basePackage}.service.${table.className}Service;
import ${basePackage}.model.page.${table.className}Page;
import ${beansPackage}.${table.className};
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

<#include "/src/main/resources/template/annotation.include"/>
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ${table.className}ServiceImplTest extends BaseTest {

    private final ${table.className}Service ${classNameLower}Service;

    @Test
    public void findPageBreakByCondition() {
        PageInfo<${table.className}> pageInfo = ${classNameLower}Service.listPage(new ${table.className}Page());
        System.out.println(JSONObject.toJSONString(pageInfo));
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        ${table.className} entity = new ${table.className}();
        <#list table.columns as column>
        <#if column.possibleShortJavaType == 'Integer'>
        entity.set${column.columnNameFirstUpper}(0);
        <#elseif column.possibleShortJavaType == 'String'>
        entity.set${column.columnNameFirstUpper}("Generated by Houtu");
        <#elseif column.possibleShortJavaType == 'Long'>
        entity.set${column.columnNameFirstUpper}(0L);
        <#elseif column.possibleShortJavaType == 'Date'>
        entity.set${column.columnNameFirstUpper}(new Date());
        <#elseif column.possibleShortJavaType == 'Boolean'>
        entity.set${column.columnNameFirstUpper}(false);
        </#if>
        </#list>

        ${table.className} newEntity = ${classNameLower}Service.insert(entity);
        System.out.println(JSONObject.toJSONString(newEntity));
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void removeByPrimaryKey() {
        boolean rs = ${classNameLower}Service.removeByPrimaryKey("1");
        System.out.println(rs);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void removeByPkList() {
        boolean rs = ${classNameLower}Service.removeByPkList(new String[]{"1"});
        System.out.println(rs);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void updateSelective() {
        ${table.className} entity = new ${table.className}();
        entity.setId("1");
        <#list table.columns as column>
        <#if column.possibleShortJavaType == 'Integer' && column.columnNameFirstLower != 'id'>
        entity.set${column.columnNameFirstUpper}(0);
        <#elseif column.possibleShortJavaType == 'String'>
        entity.set${column.columnNameFirstUpper}("Updated by Houtu");
        <#elseif column.possibleShortJavaType == 'Long'>
        entity.set${column.columnNameFirstUpper}(0L);
        <#elseif column.possibleShortJavaType == 'Date'>
        entity.set${column.columnNameFirstUpper}(new Date());
        <#elseif column.possibleShortJavaType == 'Boolean'>
        entity.set${column.columnNameFirstUpper}(false);
        </#if>
        </#list>

        ${table.className} newEntity = ${classNameLower}Service.insert(entity);
        System.out.println(JSONObject.toJSONString(newEntity));
    }

    @Test
    public void getByPrimaryKey() {
        ${table.className} entity = ${classNameLower}Service.getByPrimaryKey("1");
        System.out.println(JSONObject.toJSONString(entity));
    }
}