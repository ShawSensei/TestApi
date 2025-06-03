/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.Dashboard.dao;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import core.utility.JsonDecoder;
import features.Dashboard.dto.req.DashboardReq;
import features.Dashboard.dto.res.DashboardRes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author macpc4
 */
public class DashboardDao {
    
    private static final String PROFILE_URL = "http://10.11.201.137:8080/api-project/v1/profile";
    private static final String BILL_URL = "http://10.11.201.137:8080/api-project/v1/nusrat-bill-api";
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "1";
    
    public DashboardRes viewDashboard(DashboardReq reqModel) {
        DashboardRes res = new DashboardRes();
        Client restClient = Client.create();
        
        // Call Profile API
        if (!callProfileApi(reqModel, res, restClient)) {
            return res;
        }
        
        // Call Bill API
        callBillApi(res, restClient);
        
        return res;
    }
    
    private boolean callProfileApi(DashboardReq reqModel, DashboardRes res, Client restClient) {
        JSONObject profileJson = createProfileRequest(reqModel);
        
        try {
            WebResource webResource = restClient.resource(PROFILE_URL);
            ClientResponse profileResp = webResource.accept("application/json")
                    .type("application/json")
                    .post(ClientResponse.class, profileJson.toString());
            
            return processProfileResponse(profileResp, res);
            
        } catch (ClientHandlerException e) {
            setErrorResponse(res, "Profile API ClientHandler Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            setErrorResponse(res, "Profile API Error: " + e.getMessage());
            return false;
        }
    }
    
    private JSONObject createProfileRequest(DashboardReq reqModel) {
        JSONObject profileJson = new JSONObject();
        profileJson.put("reqCode", reqModel.getReqCode());
        profileJson.put("userId", reqModel.getUserId());
        profileJson.put("token", reqModel.getToken());
        return profileJson;
    }
    
    private boolean processProfileResponse(ClientResponse profileResp, DashboardRes res) {
        try {
            if (profileResp.getStatus() != 200) {
                setErrorResponse(res, "Profile API Server Error");
                return false;
            }
            
            String output = profileResp.getEntity(String.class);
            JSONObject resJson = JsonDecoder.getJson(output);
            
            String outMessage = JsonDecoder.getJsonValue(resJson, "outMessage");
            String outCode = JsonDecoder.getJsonValue(resJson, "outCode");
            String email = extractEmailFromResponse(resJson);
            
            res.setOutCode(outCode);
            res.setEmail(email);
            res.setOutMessage(outMessage);
            res.setBalance("5000 bdt");
            res.setDate("26-03-2024");
            res.setDueAmount("250 bdt");
            
            return true;
            
        } catch (Exception e) {
            setErrorResponse(res, "Profile API Processing Error: " + e.getMessage());
            return false;
        }
    }
    
    private String extractEmailFromResponse(JSONObject resJson) {
        JSONArray dataArray = (JSONArray) resJson.get("data");
        if (dataArray != null && !dataArray.isEmpty()) {
            JSONObject firstItem = (JSONObject) dataArray.get(0);
            return JsonDecoder.getJsonValue(firstItem, "email");
        }
        return "";
    }
    
    private void callBillApi(DashboardRes res, Client restClient) {
        JSONObject billJson = createBillRequest();
        
        try {
            WebResource billWebResource = restClient.resource(BILL_URL);
            ClientResponse billResp = billWebResource.accept("application/json")
                    .type("application/json")
                    .post(ClientResponse.class, billJson.toString());
            
            processBillResponse(billResp, res);
            
        } catch (Exception e) {
            // Log error but don't fail the entire request
            System.err.println("Bill API Error: " + e.getMessage());
            res.setVoucherDetails(new JSONArray());
        }
    }
    
    private JSONObject createBillRequest() {
        JSONObject billJson = new JSONObject();
        billJson.put("reqCode", "2");
        billJson.put("userID", "123");
        billJson.put("billNo", "456");
        return billJson;
    }
    
    private void processBillResponse(ClientResponse billResp, DashboardRes res) {
        try {
            if (billResp.getStatus() != 200) {
                res.setVoucherDetails(new JSONArray());
                return;
            }
            
            String billOutput = billResp.getEntity(String.class);
            JSONObject billResJson = JsonDecoder.getJson(billOutput);
            
            String billOutCode = JsonDecoder.getJsonValue(billResJson, "outCode");
            
            if (SUCCESS_CODE.equals(billOutCode)) {
                Object voucherDetailsObj = billResJson.get("voucherDetails");
                
                if (voucherDetailsObj instanceof JSONArray) {
                    JSONArray voucherDetails = (JSONArray) voucherDetailsObj;
                    res.setVoucherDetails(voucherDetails.isEmpty() ? new JSONArray() : voucherDetails);
                } else {
                    res.setVoucherDetails(new JSONArray());
                }
            } else {
                res.setVoucherDetails(new JSONArray());
            }
            
        } catch (Exception e) {
            System.err.println("Bill API Processing Error: " + e.getMessage());
            res.setVoucherDetails(new JSONArray());
        }
    }
    
    private void setErrorResponse(DashboardRes res, String message) {
        res.setOutCode(ERROR_CODE);
        res.setOutMessage(message);
        res.setEmail("");
        res.setBalance("");
        res.setDate("");
        res.setDueAmount("");
        res.setVoucherDetails(new JSONArray());
    }
}