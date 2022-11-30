<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.biz.query;
import ${basePackage}.framework.result.ResultPage;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Data
@ApiModel( "${table.remark}query")
public class ${table.className}Query extends ResultPage  implements Serializable  {
    @ApiModelProperty(notes = "搜索关键字")
    private String name;
}

