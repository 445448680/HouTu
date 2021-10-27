<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
package ${basePackage}.biz.vo;

import com.fujieid.core.common.core.object.SearchForm;
import lombok.Getter;
import lombok.Setter;

<#assign po = table.classNameFirstLower>

<#include "/src/main/resources/template/annotation.include"/>
@Getter
@Setter
public class ${table.className}Page extends SearchForm {

}

