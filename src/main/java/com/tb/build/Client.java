package com.tb.build;

/**
 * Created by yangzhuo02 on 2016/12/21.
 */
public class Client {
    public static void main(String[] args) {
        Text    text        = new Text("开始", "这本书内容不错", "结束");
        Builder xmlBuilder  = new XmlBuilder(text); //创建xml生成器
        Builder jsonBuilder = new JsonBuilder(text); //创建json生成器

        Director xmlDirector = new Director(xmlBuilder);
        String   xmlResult   = xmlDirector.build();
        System.out.println(xmlResult);

        Director jsonDirector = new Director(jsonBuilder);
        String   jsonResult   = jsonDirector.build();
        System.out.println(jsonResult);

    }
}
