// ========== NanoDashboardDao.java ==========

package features.nano_dashboard.dao;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import core.utility.JsonDecoder;
import features.nano_dashboard.dto.req.NanoDashboardReq;
import features.nano_dashboard.dto.res.NanoDashboardRes;
import org.json.simple.JSONObject;

/**
 *
 * @author macpc4
 */
public class NanoDashboardDao {
    
    private static final String DASHBOARD_URL = "http://10.11.201.180:8084/nanoloan-adminpannel-api/v1/dashboard/dashboard-data";
    private static final String SUCCESS_CODE = "0";
    private static final String ERROR_CODE = "1";
    
    public NanoDashboardRes getDashboardData(NanoDashboardReq reqModel, String authorization, String userIdHeader) {
        NanoDashboardRes res = new NanoDashboardRes();
        
        try {
            JSONObject requestJson = createDashboardRequest(reqModel);
            ClientResponse response = callDashboardApi(requestJson, authorization, userIdHeader);
            processDashboardResponse(response, res);
            
        } catch (Exception e) {
            System.err.println("Dashboard API Error: " + e.getMessage());
            setErrorResponse(res, "Dashboard API Error: " + e.getMessage());
        }
        
        return res;
    }
    
    private JSONObject createDashboardRequest(NanoDashboardReq reqModel) {
        JSONObject requestJson = new JSONObject();
        requestJson.put("userId", reqModel.getUserId());
        return requestJson;
    }
    
    private ClientResponse callDashboardApi(JSONObject requestJson, String authorization, String userIdHeader) throws Exception {
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(DASHBOARD_URL);
        
        return webResource.accept("application/json")
                .type("application/json")
                .header("Authorization", authorization)
                .header("userId", userIdHeader)
                .post(ClientResponse.class, requestJson.toString());
    }
    
    private void processDashboardResponse(ClientResponse response, NanoDashboardRes res) {
        try {
            if (response.getStatus() != 200) {
                setErrorResponse(res, "Dashboard API returned status: " + response.getStatus());
                return;
            }
            
            String output = response.getEntity(String.class);
            JSONObject resJson = JsonDecoder.getJson(output);
            
            String outCode = JsonDecoder.getJsonValue(resJson, "outCode");
            String outMessage = JsonDecoder.getJsonValue(resJson, "outMessage");
            
            if (SUCCESS_CODE.equals(outCode)) {
                res.setOutCode(outCode);
                res.setOutMessage(outMessage);
                
                // Parse all dashboard fields
                res.settREQ(parseIntSafely(resJson, "tREQ"));
                res.settSUC_REQ(parseIntSafely(resJson, "tSUC_REQ"));
                res.settFAIL_REQ(parseIntSafely(resJson, "tFAIL_REQ"));
                res.settDSB_REQ(parseIntSafely(resJson, "tDSB_REQ"));
                res.settREQ_AMT(parseIntSafely(resJson, "tREQ_AMT"));
                res.settDSB_AMT(parseIntSafely(resJson, "tDSB_AMT"));
                res.settDSB_DBT_AMT(parseIntSafely(resJson, "tDSB_DBT_AMT"));
                res.settDSB_CH_AMT(parseIntSafely(resJson, "tDSB_CH_AMT"));
                res.settDSB_VAT_AMT(parseIntSafely(resJson, "tDSB_VAT_AMT"));
                res.setfV_REQ(parseIntSafely(resJson, "fV_REQ"));
                res.setfV_AUTO(parseIntSafely(resJson, "fV_AUTO"));
                res.setfV_MANUAL(parseIntSafely(resJson, "fV_MANUAL"));
                res.setfV_PENDING(parseIntSafely(resJson, "fV_PENDING"));
                res.setcIB_REQ(parseIntSafely(resJson, "cIB_REQ"));
                res.setcIB_VERIFIED(parseIntSafely(resJson, "cIB_VERIFIED"));
                res.setcIB_CL(parseIntSafely(resJson, "cIB_CL"));
                res.setcIB_UCL(parseIntSafely(resJson, "cIB_UCL"));
                res.setcIB_PENDING(parseIntSafely(resJson, "cIB_PENDING"));
            } else {
                setErrorResponse(res, outMessage != null ? outMessage : "Dashboard API returned error");
            }
            
        } catch (Exception e) {
            System.err.println("Dashboard Response Processing Error: " + e.getMessage());
            setErrorResponse(res, "Response processing error: " + e.getMessage());
        }
    }
    
    private int parseIntSafely(JSONObject json, String key) {
        try {
            Object value = json.get(key);
            if (value == null) return 0;
            if (value instanceof Long) return ((Long) value).intValue();
            if (value instanceof Integer) return (Integer) value;
            if (value instanceof String) return Integer.parseInt((String) value);
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private void setErrorResponse(NanoDashboardRes res, String message) {
        res.setOutCode(ERROR_CODE);
        res.setOutMessage(message);
        res.settREQ(0);
        res.settSUC_REQ(0);
        res.settFAIL_REQ(0);
        res.settDSB_REQ(0);
        res.settREQ_AMT(0);
        res.settDSB_AMT(0);
        res.settDSB_DBT_AMT(0);
        res.settDSB_CH_AMT(0);
        res.settDSB_VAT_AMT(0);
        res.setfV_REQ(0);
        res.setfV_AUTO(0);
        res.setfV_MANUAL(0);
        res.setfV_PENDING(0);
        res.setcIB_REQ(0);
        res.setcIB_VERIFIED(0);
        res.setcIB_CL(0);
        res.setcIB_UCL(0);
        res.setcIB_PENDING(0);
    }
}
