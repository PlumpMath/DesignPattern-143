package com.tb.build;

/**
 * 生成器,以生成xml和json文本举例
 * Created by yangzhuo02 on 2016/12/21.
 */
public interface Builder {
    public String buildHead();

    public String buildContent();

    public String buildFoot();
}
