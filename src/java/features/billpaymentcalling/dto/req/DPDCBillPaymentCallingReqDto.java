/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.billpaymentcalling.dto.req;

/**
 *
 * @author mobileapps1
 */
public class DPDCBillPaymentCallingReqDto {

    
    private String userID;
    private String token;
    private String billNo;
    private String mobileNo;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "DPDCBillPaymentReqDto{" + "billNo=" + billNo + ", mobileNo=" + mobileNo + '}';
    }
    
    
    
}
