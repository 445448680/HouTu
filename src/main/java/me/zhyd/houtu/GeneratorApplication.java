package me.zhyd.houtu;


import me.zhyd.houtu.config.Config;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

public class GeneratorApplication {

    @Test
    public void main() {

        String outputDir =System.getProperty("user.dir") + File.separator + "generator-output";
        System.out.println("输出马力"+outputDir);
        Generator g = new Generator(Config.getInstance()
                .setDriver("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://192.168.10.231:3306/quiz_game?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false")
                .setUsername("root")
                .setPassword("sjy@xy_100")
                .setPrimaryKeyType("String")
                .setBasePackage("com.wujun")
                .setBeansPackage("com.wujun.beans")
                .setMapperPackage("com.wujun.mapper")
                .setClassPrefix("")
                .setClearClassPrefix("")
                .setOutRootDir(outputDir));

        //删除生成器的输出目录
        g.deleteOutRootDir();

        // 打印所有表
        g.printAllTableInfo();

//         //生成单个表的Java文件
        g.generate("banner");
        // 生成多个表的Java文件
//        g.generate("test_code");
        // 生成所有表的Java文件
//        g.generate();
        // 生成所有表的Java文件
//        g.generateAll();
        // 生成所有表的Java文件
//        g.generateByPrefix("customer");
    }

    @Test
    public void printProperty() {
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        Properties properties = System.getProperties();
        for (String propertyName : properties.stringPropertyNames()) {
            System.out.println(propertyName + " = " + System.getProperty(propertyName));
        }
    }

}
