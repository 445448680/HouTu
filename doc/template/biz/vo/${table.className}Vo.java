<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.vo;

import ${basePackage}.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Getter
@Setter
public class ${table.className}Vo extends BaseModel {
    <#list table.columns as column>
    <#if column.columnNameFirstLower != 'id' && column.columnNameFirstLower != 'insertTime' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
    private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
    </#if>
    </#list>
}

