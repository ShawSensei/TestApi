/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.billpaymentapi.dao;

import features.billpaymentapi.dto.req.DPDCBillPaymentReqDto;
import features.billpaymentapi.dto.res.DPDCBillPaymentResDto;


/**
 *
 * @author mobileapps1
 */
public class BillPaymentDao {

    public DPDCBillPaymentResDto payDpdcBill(DPDCBillPaymentReqDto req) {
        DPDCBillPaymentResDto res = new DPDCBillPaymentResDto();

        if (!req.getBillNo().equals("123")) {
            res.setOutCode("1");
            res.setOutMessage("Bill Not Found!");
            res.setBillPaymentTrnID("");
        } else if (!req.getMobileNo().equals("01719885508")) {
            res.setOutCode("1");
            res.setOutMessage("Mobile No Not Match with Customer Mobile No!");
            res.setBillPaymentTrnID("");
        } else {
            res.setOutCode("0");
            res.setOutMessage("Bill Payment Successful!");
            res.setBillPaymentTrnID("123456");
        }

        return res;

    }

}
