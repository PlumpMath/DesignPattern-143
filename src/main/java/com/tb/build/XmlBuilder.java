package com.tb.build;

import lombok.*;

/**
 * Created by yangzhuo02 on 2016/12/21.
 */
@ToString
@RequiredArgsConstructor
public class XmlBuilder implements Builder {

    @Getter
    @Setter
    @NonNull
    private Text text;

    @Override
    public String buildHead() {
        StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append(String.format("%s%s%s", "<head>", text.getHead(), "</head>"));
        return result.toString();
    }

    @Override
    public String buildContent() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s%s", "<content>", text.getContent(), "</content>"));
        return result.toString();
    }

    @Override
    public String buildFoot() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s%s%s%s", "<foot>", text.getFoot(), "</foot>", "</xml>"));
        return result.toString();
    }
}
