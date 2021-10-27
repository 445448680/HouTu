<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${mapperPackage}.mapper;

import ${beansPackage}.${table.className};
import com.fujieid.core.common.datasource.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

<#include "/src/main/resources/template/annotation.include"/>
@Mapper
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {
}
