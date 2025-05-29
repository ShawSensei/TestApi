/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.billpaymentapi.dto.req;

/**
 *
 * @author mobileapps1
 */
public class DPDCBillPaymentReqDto {

    private String billNo;
    private String mobileNo;

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
