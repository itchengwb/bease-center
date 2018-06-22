package com.noriental.util;

/**
 * @author chenlihua
 * @date 2016/6/24
 * @time 14:39
 */
public enum PipelineEnum {
    // 默认 使用天下畅通通道
    DEFAULT("默认"),
    // 自动 通过号码号段判断使用哪个短信通道
    AUTO("自动"),
    // 中国移动
    CMCC("中国移动"),
    // 中国联通
    CUCC("中国联通"),
    // 中国电信
    CTCC("中国电信"),
    ;
    private String name;

    PipelineEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
