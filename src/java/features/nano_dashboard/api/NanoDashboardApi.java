package features.nano_dashboard.api;

import core.utility.JsonDecoder;
import features.nano_dashboard.dao.NanoDashboardDao;
import features.nano_dashboard.dto.req.NanoDashboardReq;
import features.nano_dashboard.dto.res.NanoDashboardRes;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author macpc4
 */
@WebServlet(name = "NanoDashboardApi", urlPatterns = {"/v1/nanoDashboard"})
public class NanoDashboardApi extends HttpServlet {

    private final NanoDashboardDao dashboardDao = new NanoDashboardDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int counter = 0;
            StringBuffer jb = new StringBuffer();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    //System.out.println(counter + " - " + line);
                    jb.append(line);
                    counter++;
                }
            } catch (Exception e) {
                System.out.println("Request Body Receiving Err: " + e.toString());
            }
            
            JSONParser parser = new JSONParser();
            JSONObject jsonReq = (JSONObject) parser.parse(jb.toString());
            System.out.println(jsonReq.toString());
            
            String userId = JsonDecoder.getJsonValue(jsonReq, "userId");
            
            // Get headers
            String authorization = request.getHeader("Authorization");
            String userIdHeader = request.getHeader("userId");
            
            // Validate required fields
            if (userId == null || userId.trim().isEmpty()) {
                JSONObject json = new JSONObject();
                json.put("outCode", "1");
                json.put("outMessage", "UserId is required");
                response.setContentType("application/json");
                response.getWriter().write(json.toJSONString());
                return;
            }
            
            if (authorization == null || authorization.trim().isEmpty()) {
                JSONObject json = new JSONObject();
                json.put("outCode", "1");
                json.put("outMessage", "Authorization header is required");
                response.setContentType("application/json");
                response.getWriter().write(json.toJSONString());
                return;
            }
            
            if (userIdHeader == null || userIdHeader.trim().isEmpty()) {
                JSONObject json = new JSONObject();
                json.put("outCode", "1");
                json.put("outMessage", "userId header is required");
                response.setContentType("application/json");
                response.getWriter().write(json.toJSONString());
                return;
            }
            
            NanoDashboardReq reqModel = new NanoDashboardReq();
            reqModel.setUserId(userId);
            
            NanoDashboardDao dao = new NanoDashboardDao();
            NanoDashboardRes res = dao.getDashboardData(reqModel, authorization, userIdHeader);
            
            JSONObject json = new JSONObject();
            json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            json.put("tREQ", res.gettREQ());
            json.put("tSUC_REQ", res.gettSUC_REQ());
            json.put("tFAIL_REQ", res.gettFAIL_REQ());
            json.put("tDSB_REQ", res.gettDSB_REQ());
            json.put("tREQ_AMT", res.gettREQ_AMT());
            json.put("tDSB_AMT", res.gettDSB_AMT());
            json.put("tDSB_DBT_AMT", res.gettDSB_DBT_AMT());
            json.put("tDSB_CH_AMT", res.gettDSB_CH_AMT());
            json.put("tDSB_VAT_AMT", res.gettDSB_VAT_AMT());
            json.put("fV_REQ", res.getfV_REQ());
            json.put("fV_AUTO", res.getfV_AUTO());
            json.put("fV_MANUAL", res.getfV_MANUAL());
            json.put("fV_PENDING", res.getfV_PENDING());
            json.put("cIB_REQ", res.getcIB_REQ());
            json.put("cIB_VERIFIED", res.getcIB_VERIFIED());
            json.put("cIB_CL", res.getcIB_CL());
            json.put("cIB_UCL", res.getcIB_UCL());
            json.put("cIB_PENDING", res.getcIB_PENDING());
            
            response.setContentType("application/json");
            response.getWriter().write(json.toJSONString());
            
        } catch (Exception e) {
            System.out.println("Server Err: " + e.toString());
            JSONObject json = new JSONObject();
            NanoDashboardRes res = new NanoDashboardRes();
            res.setOutMessage("Overall Server Error! " + e.toString());
            json.put("outCode", "1");
            json.put("outMessage", res.getOutMessage());
            response.setContentType("application/json");
            response.getWriter().write(json.toJSONString());
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject json = new JSONObject();
        json.put("outCode", "1");
        json.put("outMessage", "Only POST method is supported");
        response.getWriter().write(json.toJSONString());
    }

    @Override
    public String getServletInfo() {
        return "Handles Nano Dashboard requests";
    }
}