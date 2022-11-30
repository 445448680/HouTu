<#include "/src/main/resources/template/java_copyright.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.biz.service;

import ${basePackage}.biz.entity.${table.className}DO;
import ${basePackage}.biz.dto.${table.className}DTO;
import ${basePackage}.biz.query.${table.className}Query;
import ${basePackage}.framework.result.Result;
import ${basePackage}.framework.result.ResultPage;
import java.util.List;

<#include "/src/main/resources/template/annotation.include"/>
public interface ${table.className}Service{

        /**
         * 分页列表
         * @param query
         * @return
         */
        ResultPage list(${table.className}Query query);

        /**
         * 新建
         * @return
         */
        Result insert(${table.className}DO ${classNameLower}DO);


        /**
         * 删除
         * @param id
         * @return
         */
        Result delete(Integer id);

        /**
         * 详情
         * @param id
         * @return
         */
        ${table.className}DTO view(Integer id);

        /**
         * 批量保存
         */
        int batchSave${table.className}List(List<${table.className}DO> ${classNameLower}List);
}

