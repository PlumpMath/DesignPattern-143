package com.tb.build;

import lombok.*;

/**
 * Created by yangzhuo02 on 2016/12/21.
 */
@ToString
@RequiredArgsConstructor
public class Director {


    @Getter
    @Setter
    @NonNull
    private Builder builder;

    public String build() {

        StringBuilder stringBuilder = new StringBuilder("");
        if (builder != null) {
            stringBuilder.append(builder.buildHead());
            stringBuilder.append(builder.buildContent());
            stringBuilder.append(builder.buildFoot());
        }
        return stringBuilder.toString();
    }

}
