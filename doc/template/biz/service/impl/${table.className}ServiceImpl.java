<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basePackage}.service.impl;

import ${basePackage}.service.${table.className}Service;
import ${basePackage}.model.page.${table.className}Page;
import ${beansPackage}.${table.className};
import ${mapperPackage}.${table.className}Mapper;
import com.fujieid.core.common.exception.JaiException;
import com.fujieid.core.common.exception.model.RestStatus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import me.zhyd.oauth.utils.UuidUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

<#include "/src/main/resources/template/annotation.include"/>
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CacheConfig(cacheNames = "${table.className}")
public class ${table.className}ServiceImpl implements ${table.className}Service {

    private final ${table.className}Mapper ${classNameLower}Mapper;

    @Override
    public PageInfo<${table.className}> listPage(${table.className}Page param) {
        PageHelper.startPage(param.getPageNumber(), param.getPageSize());
        Example example = new Example(${table.className}.class);

        example.orderBy("createTime").desc();

        List<${table.className}> list = ${classNameLower}Mapper.selectByExample(example);
        return new PageInfo<${table.className}>(list);
    }

    @CachePut(key = "#entity.id")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.className} insert(${table.className} entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        if (entity.getId() == null) {
            entity.setId(UuidUtils.getUUID());
        }
        ${classNameLower}Mapper.insertSelective(entity);
        return entity;
    }

    @CacheEvict(key = "#id")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(${primaryKeyType} id) {
        Assert.notNull(id, "Invalid parameter");
        return ${classNameLower}Mapper.deleteByPrimaryKey(id) > 0;
    }

    @CacheEvict(key = "#primaryKeys")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPkList(String[] primaryKeys) {
        Assert.noNullElements(primaryKeys, "ID 不可为空");
        Example example = new Example(${table.className}.class);
        example.createCriteria().andIn("id", Arrays.asList(primaryKeys));
        int removedCount = ${classNameLower}Mapper.deleteByExample(example);
        return removedCount > 0;
    }

    @CachePut(key = "#entity.id")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.className} updateSelective(${table.className} entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setUpdateTime(new Date());
        boolean isSuccess = ${classNameLower}Mapper.updateByPrimaryKeySelective(entity) > 0;
        if (isSuccess) {
        return entity;
        }
        throw new JaiException(RestStatus.ERROR);
    }

    @Cacheable(key = "#id")
    @Override
    public ${table.className} getByPrimaryKey(${primaryKeyType} id) {
        Assert.notNull(id, "Invalid parameter");
        return ${classNameLower}Mapper.selectByPrimaryKey(id);
    }
}
