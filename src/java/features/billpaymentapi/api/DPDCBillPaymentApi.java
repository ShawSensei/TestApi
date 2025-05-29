/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package features.billpaymentapi.api;

import core.utility.JsonDecoder;
import features.billpaymentapi.dao.BillPaymentDao;
import features.billpaymentapi.dto.req.DPDCBillPaymentReqDto;
import features.billpaymentapi.dto.res.DPDCBillPaymentResDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author mobileapps1
 */
@WebServlet(name = "DPDCBillPaymentApi", urlPatterns = {"/v1/dpdc-bill-payment"})
public class DPDCBillPaymentApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DPDCBillPaymentApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DPDCBillPaymentApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

            String billNo = JsonDecoder.getJsonValue(jsonReq, "billNo");
            System.out.println("billNo = " + billNo);
            String mobileNo = JsonDecoder.getJsonValue(jsonReq, "mobileNo");
            System.out.println("mobileNo = " + mobileNo);

            DPDCBillPaymentReqDto req = new DPDCBillPaymentReqDto();
            req.setBillNo(billNo);
            req.setMobileNo(mobileNo);

            BillPaymentDao dao = new BillPaymentDao();
            DPDCBillPaymentResDto res = dao.payDpdcBill(req);

            JSONObject json = new JSONObject();
            json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            json.put("trnID", res.getBillPaymentTrnID());

            response.getWriter().write(json.toString());

        } catch (Exception e) {
            System.out.println("Server Err: " + e.toString());

            JSONObject json = new JSONObject();

            DPDCBillPaymentResDto res = new DPDCBillPaymentResDto();
            res.setOutCode("1");
            res.setOutMessage("Server Err !" + e.toString());
            res.setBillPaymentTrnID("");

            json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            json.put("trnID", res.getBillPaymentTrnID());

            response.getWriter().write(json.toString());

        }
    }

    /*
    {
      "name":"",
      "dob":""
    }
    
     */
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
