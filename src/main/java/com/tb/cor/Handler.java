package com.tb.cor;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangzhuo on 16-12-21.
 */

public abstract class Handler {
    @Getter
    @Setter
    protected Handler successors;

    /**
     * 根据请求信息来做相应的处理
     * handler可以根据请求类型来和其他的设计模式进行组合
     * 比如工厂模式,或者生成器设计模式
     *
     * @param requestObject
     */
    public void handleRequest(RequestObject requestObject) {
    }


}
