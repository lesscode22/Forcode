package com.forcode.base.common.gen;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.setting.yaml.YamlUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {

    public static void main(String[] args) {

        Dict dict = YamlUtil.loadByPath("application-dev.yml");
        Map<String, String> sourceConf = dict.getByPath("spring.datasource.ds.qd", HashMap.class);
        // 数据源设置
        DataSourceConfig.Builder dataSourceConf = new DataSourceConfig.Builder(sourceConf.get("url"),
                sourceConf.get("username"),
                sourceConf.get("password"))
                .typeConvert(new CustomOracleTypeConvert());

        // 输出目录
        String projectPath = FileUtil.getWebRoot().getAbsolutePath();
        String today = DateUtil.format(new Date(), "yyyyMMdd");
        String outDir = projectPath + File.separator + today;
        String xmlPath = outDir + File.separator + "xml";

        FastAutoGenerator.create(dataSourceConf)
                .globalConfig(builder -> {
                    builder.author("TJ")        // 设置作者
                            .disableOpenDir()   // 禁止打开输出目录
                            .fileOverride()     // 覆盖已生成文件
                            .outputDir(outDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.forcode.base.test.temp") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, xmlPath)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .idType(IdType.ASSIGN_ID)
                            .enableTableFieldAnnotation();
                    builder.addInclude("TEST_USER"); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
