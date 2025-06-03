/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.billpaymentcalling.dao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import core.utility.JsonDecoder;
import features.billpaymentcalling.dto.req.DPDCBillPaymentCallingReqDto;
import features.billpaymentcalling.dto.res.DPDCBillPaymentCallingResDto;
import org.json.simple.JSONObject;

/**
 *
 * @author mobileapps1
 */
public class BillPaymentCallingDao {

    public DPDCBillPaymentCallingResDto payDpdcBill(DPDCBillPaymentCallingReqDto req) {
        DPDCBillPaymentCallingResDto res = new DPDCBillPaymentCallingResDto();

        /*if (!req.getBillNo().equals("123")) {
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
        }*/
        String url = "http://localhost:8087/api-project/v1/dpdc-bill-payment";

        JSONObject json = new JSONObject();
        json.put("billNo", req.getBillNo());
        json.put("mobileNo", req.getMobileNo());
        System.out.println(json.toJSONString());
        //{"billNo": "123","mobileNo": "01719885508"}

        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = null;

        try {
            resp = webResource.accept("application/json")
                    .type("application/json")
                    .post(ClientResponse.class, json.toString());
        } catch (ClientHandlerException e) {
            System.out.println(e.toString());
            res.setOutCode("1");
            res.setOutMessage("ClientHandler Server Err!" + e.toString());
            res.setBillPaymentTrnID("");
        } catch (Throwable e) {
            System.out.println(e.toString());
            res.setOutCode("1");
            res.setOutMessage("Throwable Server Err!" + e.toString());
            res.setBillPaymentTrnID("");
        }

        try {
            System.out.println("Status : "+ resp.getStatus());
            System.out.println(resp.getStatus());
            if (resp.getStatus() == 200) {
                //decode
                //System.out.println("Successful");
                String output = resp.getEntity(String.class);
                JSONObject resJson = JsonDecoder.getJson(output);
                System.out.println(resJson.toString());
                
                
                String outMessage = JsonDecoder.getJsonValue(resJson, "outMessage");
                String trnID = JsonDecoder.getJsonValue(resJson, "trnID");
                String outCode = JsonDecoder.getJsonValue(resJson, "outCode");
                
                res.setOutCode(outCode);   
                res.setBillPaymentTrnID(trnID);
                res.setOutMessage(outMessage); 
                
            } else {
                res.setOutCode("1");
                res.setOutMessage("Server Err!");
                res.setBillPaymentTrnID("");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            res.setOutCode("1");
            res.setOutMessage("Server Err!" + e.toString());
            res.setBillPaymentTrnID("");
        }

        return res;

    }

}
