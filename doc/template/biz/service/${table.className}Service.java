<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.biz.service;


import com.fujieid.core.common.core.object.AbstractService;
import ${beansPackage}.${table.className};
import ${basePackage}.model.page.${table.className}Page;
import com.github.pagehelper.PageInfo;

<#include "/src/main/resources/template/annotation.include"/>
public interface ${table.className}Service extends AbstractService<${table.className}, ${primaryKeyType}> {

    PageInfo<${table.className}> listPage(${table.className}Page param);
}
