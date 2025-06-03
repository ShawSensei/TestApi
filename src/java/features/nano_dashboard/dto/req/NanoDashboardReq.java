/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.nano_dashboard.dto.req;

/**
 *
 * @author macpc4
 */
public class NanoDashboardReq {
       String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "NanoDashboardRes{" + "userId=" + userId + '}';
    }
   
}
