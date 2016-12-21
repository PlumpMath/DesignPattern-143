package com.tb.cor;

import java.util.Objects;

/**
 * 经理处理者,经理后继是boss
 * Created by yangzhuo on 16-12-21.
 */
public class LeaderHandler extends Handler {
    @Override
    public void handleRequest(RequestObject requestObject) {
        if (requestObject.getType() == "leave") {
            handleLeaveRequest(requestObject);
        } else {
            handleReimbursementRequest(requestObject);
        }
    }

    /**
     * @param requestObject
     */
    public void handleLeaveRequest(RequestObject requestObject) {
        //为了简单,假设一线经理只能审批自己组员的请假情况
        if (requestObject.getUser() == "Lee") {
            System.out.println("审批通过");
        } else {
            this.getSuccessors().handleRequest(requestObject);
        }
    }

    /**
     * @param requestObject
     */
    public void handleReimbursementRequest(RequestObject requestObject) {
        //为了简单,假设一线经理只能审核1000元的报销单
        if (requestObject.getMessage() == "1000") {
            System.out.println("审批通过");
        } else if (!Objects.isNull(this.getSuccessors())) {
            this.getSuccessors().handleRequest(requestObject);
        } else {
            System.out.println("审批流程结束");
        }
    }

}
