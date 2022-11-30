<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.controller;

import ${basePackage}.biz.dto.${table.className}DTO;
import ${basePackage}.biz.entity.${table.className}DO;
import ${basePackage}.biz.query.${table.className}Query;
import ${basePackage}.biz.service.${table.className}Service;
import ${basePackage}.framework.annotation.LoginRequired;
import ${basePackage}.framework.enums.PlatformMarkEnum;
import ${basePackage}.framework.result.Result;
import ${basePackage}.framework.result.ResultPage;
import ${basePackage}.framework.utils.UserThreadLocal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

<#include "/src/main/resources/template/annotation.include"/>

@Api(tags = {"1000-${table.remark}"})
@RefreshScope
@RestController
@Component
@Slf4j
@LoginRequired(platform= PlatformMarkEnum.MECHANISM)
@RequestMapping("/api/mech/${table.classNameFirstLower}")
public class Rest${table.className}Controller {
    @Autowired
    private ${table.className}Service ${table.classNameFirstLower}Service; 

    @ApiOperation(value = "${table.remark}-列表", notes = "列表")
    @PostMapping(value = "/list")
    public ResultPage<${table.className}DTO> list(@RequestBody ${table.className}Query query) {
        //Integer mechanismId = UserThreadLocal.get() .getMechanismId();
        ResultPage result = ${table.classNameFirstLower}Service.list(query);
        return result;
    }

    @ApiOperation(value = "${table.remark}-新建/编辑", notes = "新建/编辑")
    @PostMapping(value = "/add")
    public Result add(@RequestBody ${table.className}DO ${table.classNameFirstLower}DO) {
        Result result =  ${table.classNameFirstLower}Service.insert(${table.classNameFirstLower}DO);
        return result;
    }

    @ApiOperation(value = "${table.remark}-详情", notes = "详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "${table.remark}ID", required = true)})
    @PostMapping(value = "/view/{id}")
    public Result<${table.className}DTO> view(@PathVariable int id) {
        return Result.ok(${table.classNameFirstLower}Service.view(id));
    }

    @ApiOperation(value = "${table.remark}-删除", notes = "删除")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "${table.remark}ID", required = true)})
    @PostMapping(value = "/delete/{id}")
    public Result delete(@PathVariable int id) {
        return ${table.classNameFirstLower}Service.delete(id);
    }

}
