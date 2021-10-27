<#include "/src/main/resources/template/java_copyright.include"/>
<#include "/src/main/resources/template/macro.include"/>
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${beansPackage};

import com.fujieid.core.common.datasource.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.Date;

<#include "/src/main/resources/template/annotation.include"/>
@Getter
@Setter
@Entity
@Table(name = "${table.tableName}")
@org.hibernate.annotations.Table(appliesTo = "${table.tableName}", comment = "${table.remark}")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ${table.className} extends BaseEntity {

	<#list table.columns as column>
	<#if column.columnNameFirstLower != 'id' && column.columnNameFirstLower != 'insertTime' && column.columnNameFirstLower != 'createTime' && column.columnNameFirstLower != 'updateTime'>
	/**
	 * ${column.remark}
	 */
	<#if column.possibleShortJavaType == 'String'>
	@Column(name = "${column.columnName}", columnDefinition = "varchar(${column.length?c}) comment '${column.remark}'")
	<#elseif column.possibleShortJavaType == 'Integer'>
	@Column(name = "${column.columnName}", columnDefinition = "int(11) comment '${column.remark}'")
	<#elseif column.possibleShortJavaType == 'Long'>
	@Column(name = "${column.columnName}", columnDefinition = "bigint(${column.length?c}) comment '${column.remark}'")
	<#elseif column.possibleShortJavaType == 'Date'>
	@Column(name = "${column.columnName}", columnDefinition = "datetime comment '${column.remark}'")
	<#elseif column.possibleShortJavaType == 'Boolean'>
	@Column(name = "${column.columnName}", columnDefinition = "bit(1) comment '${column.remark}'")
	</#if>
	private ${column.possibleShortJavaType} ${column.columnNameFirstLower};

	</#if>
	</#list>
}
