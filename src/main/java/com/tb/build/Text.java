package com.tb.build;

import lombok.*;

/**
 * Created by yangzhuo02 on 2016/12/21.
 */
@ToString
@RequiredArgsConstructor
public class Text {

    @Getter
    @Setter
    @NonNull
    private String head;
    @Getter
    @Setter
    @NonNull
    private String content;
    @Getter
    @Setter
    @NonNull
    private String foot;

}
