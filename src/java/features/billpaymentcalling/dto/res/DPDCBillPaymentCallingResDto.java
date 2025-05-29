/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.billpaymentcalling.dto.res;

/**
 *
 * @author mobileapps1
 */
public class DPDCBillPaymentCallingResDto {
    private String outCode;
    private String outMessage;
    private String billPaymentTrnID;

    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public String getBillPaymentTrnID() {
        return billPaymentTrnID;
    }

    public void setBillPaymentTrnID(String billPaymentTrnID) {
        this.billPaymentTrnID = billPaymentTrnID;
    }

    @Override
    public String toString() {
        return "DPDCBillPaymentResDto{" + "outCode=" + outCode + ", outMessage=" + outMessage + ", billPaymentTrnID=" + billPaymentTrnID + '}';
    }
    
    
}
