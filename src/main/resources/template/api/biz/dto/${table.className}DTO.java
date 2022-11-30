<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.biz.dto;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Data
@ApiModel( "${table.remark}DTO")
public class ${table.className}DTO implements Serializable  {
    <#list table.columns as column>
    <#if column.columnNameFirstLower != 'isDelete' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
        @ApiModelProperty(value = "${((column.remark)!'')?js_string}")
        private ${column.possibleShortJavaType} ${column.columnNameFirstLower};
    </#if>
    </#list>
}

