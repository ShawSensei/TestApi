package features.BillPayment.api;

import core.utility.JsonDecoder;
import features.BillPayment.dao.BillPaymentDao;
import features.BillPayment.dto.req.BillTypeReq;
import features.BillPayment.dto.res.BillTypeRes;
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
@WebServlet(name = "BillPaymentApi", urlPatterns = {"/v1/billPayment"})
public class BillPaymentApi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String token = request.getParameter("token");
//        String id = request.getParameter("id");
//        String billType = request.getParameter("billType");
//
//        System.out.println("Received BillPayment POST with: token=" + token + ", id=" + id + ", billType=" + billType);
//
//        BillTypeReq reqModel = new BillTypeReq();
//        reqModel.setToken(token);
//        reqModel.setId(id);
//        reqModel.setBillType(billType);

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

            String token = JsonDecoder.getJsonValue(jsonReq, "token");
          
            String id = JsonDecoder.getJsonValue(jsonReq, "id");
            
            String billType = JsonDecoder.getJsonValue(jsonReq, "billType");

            BillTypeReq reqModel = new BillTypeReq();
            reqModel.setToken(token);
            reqModel.setId(id);
             reqModel.setBillType(billType);

        BillPaymentDao dao = new BillPaymentDao();
        BillTypeRes res = dao.doLogin(reqModel);

        JSONObject json = new JSONObject();
        json.put("billName", res.getBillName());
        json.put("amount", res.getAmount());
        json.put("date", res.getDate());
        json.put("outMessage", res.getOutMessage());

        response.setContentType("application/json");
        response.getWriter().write(json.toJSONString());
        } catch (Exception e) {
            System.out.println("Server Err: " + e.toString());

            JSONObject json = new JSONObject();

            BillTypeRes res = new BillTypeRes();
            //res.setOutCode("1");
            res.setOutMessage("Overa all Server Err !" + e.toString());
            //res.setBillPaymentTrnID("");

            //json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            //json.put("trnID", res.getBillPaymentTrnID());

            response.getWriter().write(json.toString());

        }
    }

    @Override
    public String getServletInfo() {
        return "Handles Bill Payment requests";
    }
}
