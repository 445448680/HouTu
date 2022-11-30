<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.biz.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Data
@ApiModel( "${table.remark}DO")
public class ${table.className}DO implements Serializable  {
    <#list table.columns as column>
    <#if column.columnNameFirstLower != 'isDelete' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
    <#if column.possibleShortJavaType == 'String'>
        @ApiModelProperty(value = "varchar(${column.length?c}) ${((column.remark)!'')?js_string}")
    <#else>
        @ApiModelProperty(value = "${((column.remark)!'')?js_string}")
    </#if>
        private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
    <#else>
        @ApiModelProperty(value = "${column.remark}",hidden = true)
        private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
    </#if>
    </#list>
}