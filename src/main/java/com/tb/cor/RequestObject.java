package com.tb.cor;

import lombok.*;

/**
 * 将请求信息抽象成一个对象
 * Created by yangzhuo on 16-12-21.
 */
@ToString
@RequiredArgsConstructor
public class RequestObject {
    //请求类型,代表是流程类型
    @Getter
    @Setter
    @NonNull
    private String type;
    //请求信息
    @Getter
    @Setter
    @NonNull
    private String message;
    //请求用户
    @Getter
    @Setter
    @NonNull
    private String user;
}
