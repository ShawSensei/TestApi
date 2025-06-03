/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.Dashboard.dto.res;
import org.json.simple.JSONArray;
/**
 *
 * @author macpc4
 */
public class DashboardRes {
    String outCode, outMessage, balance, dueAmount, date, email;
    JSONArray voucherDetails;
    
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
    
    public String getBalance() {
        return balance;
    }
    
    public void setBalance(String balance) {
        this.balance = balance;
    }
    
    public String getDueAmount() {
        return dueAmount;
    }
    
    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public JSONArray getVoucherDetails() {
        return voucherDetails;
    }
    
    public void setVoucherDetails(JSONArray voucherDetails) {
        this.voucherDetails = voucherDetails;
    }
    
    @Override
    public String toString() {
        return "DashboardRes{" + 
               "outCode=" + outCode + 
               ", outMessage=" + outMessage + 
               ", balance=" + balance + 
               ", dueAmount=" + dueAmount + 
               ", date=" + date + 
               ", email=" + email + 
               ", voucherDetails=" + voucherDetails + 
               '}';
    }
}