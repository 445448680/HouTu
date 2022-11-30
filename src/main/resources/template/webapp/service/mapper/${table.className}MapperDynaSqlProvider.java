<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service.mapper;

import ${basePackage}.biz.entity.${table.className}DO;
import ${basePackage}.biz.query.${table.className}Query;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
<#include "/src/main/resources/template/annotation.include"/>

public class ${table.className}MapperDynaSqlProvider {

    public String selectAll(final ${table.className}Query query) {
        SQL sql = new SQL() {
            {
                SELECT( " <#list table.columns as _column>a.`${_column.columnName}`<#if _column_has_next>,</#if></#list> " );
                FROM(" ${table.tableName} a ");
                if (StringUtils.isNotBlank(query.getName())) {
                    WHERE(" a.name like ${r'#{'} name ${r'}'} ");
                }
                WHERE(" a.is_delete=0");
                ORDER_BY(" a.create_time DESC");
            }
        };
        return sql.toString();
    }



    public String update(final ${table.className}DO ${classNameLower}DO) {
        return new SQL() {
            {
                 UPDATE(" ${table.tableName} ");
                 <#list table.columns as _column><#if _column.columnName != 'id'  && _column.columnName != 'is_delete' && _column.columnName != 'update_time'  && _column.columnName != 'create_time'  >                ${r'SET("'}`${_column.columnName}`${r'=#{'}${_column.columnNameFirstLower}${r'}");'}</#if>
</#list>
                WHERE("id= ${r'#{'} id ${r'}'}");
             }
        }.toString();
    }
}
