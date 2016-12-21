package com.tb.cor;

import java.util.Objects;

/**
 * Created by yangzhuo on 16-12-21.
 */
public class BossHandler extends Handler {
    @Override
    public void handleRequest(RequestObject requestObject) {
        //这一步和leader重复了  其实可以结合其他设计模式实现
        if (requestObject.getType() == "leave") {
            handleLeaveRequest(requestObject);
        } else if(!Objects.isNull(requestObject)){
            handleReimbursementRequest(requestObject);
        }else {
            System.out.println("审批流程结束");
        }
    }

    /**
     * @param requestObject
     */
    public void handleLeaveRequest(RequestObject requestObject) {
        if (requestObject.getUser() == "tom") {
            System.out.println("审核通过");
        } else {
            this.getSuccessors().handleRequest(requestObject);
        }
    }

    /**
     * @param requestObject
     */
    public void handleReimbursementRequest(RequestObject requestObject) {
        if (Integer.valueOf(requestObject.getMessage()) > 1000) {
            System.out.println("审核通过");
        } else {
            this.getSuccessors().handleRequest(requestObject);
        }
    }

}
