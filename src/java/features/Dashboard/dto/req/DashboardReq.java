/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.Dashboard.dto.req;

/**
 *
 * @author macpc4
 */
public class DashboardReq {
    String  reqCode,userId,token;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




    @Override
    public String toString() {
        return "DashboardReq{" + "reqCode=" + reqCode + ", userId=" + userId + ", token=" + token + '}';
    }

       
    
}
