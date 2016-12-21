package com.tb.build;

import lombok.*;

/**
 * Created by yangzhuo02 on 2016/12/21.
 */
@ToString
@RequiredArgsConstructor
public class JsonBuilder implements Builder {

    @Getter
    @Setter
    @NonNull
    private Text text;

    @Override
    public String buildHead() {
        StringBuilder result = new StringBuilder();
        result.append("{");
        result.append(String.format("%s%s%s%s%s", "\"head\":", "\"", text.getHead(), "\"", ","));
        return result.toString();
    }

    @Override
    public String buildContent() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s%s%s%s", "\"content\":", "\"", text.getContent(), "\"", ","));
        return result.toString();
    }

    @Override
    public String buildFoot() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s%s%s%s", "\"foot\":", "\"", text.getFoot(), "\"", "}"));
        return result.toString();
    }
}
