<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package com.fujieid.jai.security.bac.filter;

import ${beansPackage}.${table.className};
import ${basePackage}.service.${table.className}Service;
import com.fujieid.core.common.exception.BacControlException;
import com.fujieid.core.common.exception.model.RestStatus;
import com.fujieid.core.common.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

<#include "/src/main/resources/template/annotation.include"/>
@Slf4j
@Component
public class ${table.className}BacFilter extends BaseBacFilter {

    @Autowired
    private ${table.className}Service ${classNameLower}Service;

    @Override
    public void filter(String loginUserName, String loginUserId, String loginUserType, String loginUserCompanyId, String targetId) {
        if (null == targetId) {
            return;
        }
        // 被操作的企业登录门户
        ${table.className} target = ${classNameLower}Service.getByPrimaryKey(targetId);
        if (ObjectUtil.orNull(loginUserType, loginUserCompanyId, target)) {
            this.recordBacLog(loginUserName, loginUserId, loginUserType, loginUserCompanyId, "${table.remark}", targetId, null);
            throw new BacControlException(RestStatus.UNAUTHORIZED);
        }

        if (target.getCompanyId().equals(loginUserCompanyId) && this.isCompanyAdmin(loginUserType)) {
            return;
        }

        this.recordBacLog(loginUserName, loginUserId, loginUserType, loginUserCompanyId, "${table.remark}", targetId, target.getCompanyId());
        throw new BacControlException(RestStatus.UNAUTHORIZED);
    }
}
