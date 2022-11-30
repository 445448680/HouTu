<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service.mapper;

import ${basePackage}.biz.dto.${table.className}DTO;
import ${basePackage}.biz.entity.${table.className}DO;
import ${basePackage}.biz.query.${table.className}Query;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

<#include "/src/main/resources/template/annotation.include"/>

@Service
@Mapper
public interface ${table.className}Mapper {
    
    @SelectProvider(type= ${table.className}MapperDynaSqlProvider.class,method="selectAll")
    List<${table.className}DTO> selectAll(${table.className}Query query);


    @Insert(" INSERT INTO `${table.tableName}`(<#list table.columns as _column><#if _column.columnName != 'id' && _column.columnName != 'is_delete' && _column.columnName != 'update_time' >`${_column.columnName}`<#if _column_has_next>,</#if></#if></#list>) " +
            " VALUES (<#list table.columns as _column><#if _column.columnName != 'id' && _column.columnName != 'is_delete' && _column.columnName != 'update_time' >${r'#{'}${_column.columnNameFirstLower}${r'}'}<#if _column_has_next>,</#if></#if></#list>)")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(${table.className}DO ${classNameLower}DO);


    @UpdateProvider(type = ${table.className}MapperDynaSqlProvider.class,method = "update")
    int update(${table.className}DO ${classNameLower}DO);

    @Update("update ${table.tableName} set is_delete=1 where id= ${r'#{'} id ${r'}'}")
    int delete(Integer id);

    @Select("select <#list table.columns as _column>a.`${_column.columnName}`<#if _column_has_next>,</#if></#list>" +
        " from ${table.tableName} a  " +
        " where a.id= ${r'#{'} id ${r'}'}")
    ${table.className}DTO view(Integer id);

    @Select("select <#list table.columns as _column>a.`${_column.columnName}`<#if _column_has_next>,</#if></#list>" +
        " from ${table.tableName} a  " +
        " where a.name= ${r'#{'} name ${r'}'}  and is_delete =0 ")
    List<${table.className}DO> getExistList(String name);

    @Insert({"<script> ",
        "INSERT INTO ${table.tableName} (<#list table.columns as _column><#if _column.columnName != 'id' && _column.columnName != 'is_delete' && _column.columnName != 'update_time' >`${_column.columnName}`<#if _column_has_next>,</#if></#if></#list> )  ",
        "VALUES ",
        " <foreach collection='list' separator=',' item='item'>" ,
        "  ( <#list table.columns as _column><#if _column.columnName != 'id' && _column.columnName != 'is_delete' && _column.columnName != 'update_time' >${r'#{'}item.${_column.columnNameFirstLower}${r'}'}<#if _column_has_next>,</#if></#if></#list>)",
        "</foreach> ",
        "</script>"})
    int batchSave${table.className}List(List<${table.className}DO> ${classNameLower}List);
}

