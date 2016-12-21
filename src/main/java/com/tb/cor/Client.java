package com.tb.cor;

/**
 * Created by yangzhuo on 16-12-21.
 */
public class Client {
    public static void main(String[] args) {
        //请假信息
        RequestObject requestObject = new RequestObject("leave", "test", "Lee");
        RequestObject requestObject1 = new RequestObject("Reimbursement", "10000", "tom");
        Handler leaderHandler = new LeaderHandler();
        Handler bossHandler = new BossHandler();

        leaderHandler.setSuccessors(bossHandler);
        leaderHandler.handleRequest(requestObject);
        leaderHandler.handleRequest(requestObject1);

    }
}
