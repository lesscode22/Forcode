package com.forcode.base.common.gen;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.OracleTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.select.BranchBuilder;
import com.baomidou.mybatisplus.generator.config.converts.select.Selector;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.*;

/**
 * 修改 Number 类型映射方式为旧版本的
 * 否则主键 Number(20, 0) 也会映射为 BigDecimal
 */
public class CustomOracleTypeConvert extends OracleTypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {

        return use(fieldType)
                .test(containsAny("char", "clob").then(STRING))
                .test(containsAny("date", "timestamp").then(p -> toDateType(config)))
                .test(contains("number").then(p -> {
                    if (p.matches("number\\(+\\d\\)")) {
                        return DbColumnType.INTEGER;
                    } else if (p.matches("number\\(+\\d{2}+\\)")) {
                        return DbColumnType.LONG;
                    }
                    return BIG_DECIMAL;
                }))
                .test(contains("float").then(FLOAT))
                .test(contains("blob").then(BLOB))
                .test(containsAny("binary", "raw").then(BYTE_ARRAY))
                .or(STRING);
    }

    static Selector<String, IColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }

    /**
     * 这个分支构建器用于构建用于支持 {@link String#contains(CharSequence)} 的分支
     *
     * @param value 分支的值
     * @return 返回分支构建器
     * @see #containsAny(CharSequence...)
     */
    static BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of(s -> s.contains(value));
    }

    /**
     * @see #contains(CharSequence)
     */
    static BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of(s -> {
            for (CharSequence value : values) {
                if (s.contains(value)) return true;
            }
            return false;
        });
    }
}
