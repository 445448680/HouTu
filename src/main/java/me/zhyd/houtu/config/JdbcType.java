package me.zhyd.houtu.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Types;

/**
 * @author GHS
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum JdbcType {

    /**
     * 数据库数据类型
     */
    BIT(Types.BIT),
    TINYINT(Types.TINYINT),
    SMALLINT(Types.SMALLINT),
    INTEGER(Types.INTEGER),
    BIGINT(Types.BIGINT),
    FLOAT(Types.FLOAT),
    REAL(Types.REAL),
    DOUBLE(Types.DOUBLE),
    NUMERIC(Types.NUMERIC),
    DECIMAL(Types.DECIMAL),
    CHAR(Types.CHAR),
    VARCHAR(Types.VARCHAR),
    LONGVARCHAR(Types.LONGVARCHAR),
    DATE(Types.DATE),
    DATETIME(Types.DATE),
    TIME(Types.TIME),
    TIMESTAMP(Types.TIMESTAMP),
    BINARY(Types.BINARY),
    VARBINARY(Types.VARBINARY),
    LONGVARBINARY(Types.LONGVARBINARY),
    NULL(Types.NULL),
    OTHER(Types.OTHER),
    BLOB(Types.BLOB),
    CLOB(Types.CLOB),
    BOOLEAN(Types.BOOLEAN),
    CURSOR(-10), // Oracle
    UNDEFINED(Integer.MIN_VALUE + 1000),
    NVARCHAR(-9),
    NCHAR(-15),
    NCLOB(2011);

    public final int code;

    public static String getJdbcSqlTypeName(int code) {
        for (JdbcType type : values()) {
            if (type.getCode() == code) {
                return type.name();
            }
        }
        return null;
    }

    public static JdbcType getJdbcTypeBySqlType(String sqlType) {
        if ("int".equalsIgnoreCase(sqlType)) {
            return INTEGER;
        }
        for (JdbcType type : values()) {
            if (sqlType.equalsIgnoreCase(type.name())) {
                return type;
            }
        }
        return VARCHAR;
    }

}
