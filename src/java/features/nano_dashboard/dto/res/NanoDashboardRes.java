/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.nano_dashboard.dto.res;

/**
 *
 * @author macpc4
 */
public class NanoDashboardRes {
 String outCode,outMessage;
 int tREQ,tSUC_REQ, tFAIL_REQ,tDSB_REQ, tREQ_AMT, tDSB_AMT , tDSB_DBT_AMT, tDSB_CH_AMT ,tDSB_VAT_AMT,  fV_REQ,fV_AUTO,fV_MANUAL,fV_PENDING,  cIB_REQ,cIB_VERIFIED, cIB_CL, cIB_UCL,cIB_PENDING;      

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

    public int gettREQ() {
        return tREQ;
    }

    public void settREQ(int tREQ) {
        this.tREQ = tREQ;
    }

    public int gettSUC_REQ() {
        return tSUC_REQ;
    }

    public void settSUC_REQ(int tSUC_REQ) {
        this.tSUC_REQ = tSUC_REQ;
    }

    public int gettFAIL_REQ() {
        return tFAIL_REQ;
    }

    public void settFAIL_REQ(int tFAIL_REQ) {
        this.tFAIL_REQ = tFAIL_REQ;
    }

    public int gettDSB_REQ() {
        return tDSB_REQ;
    }

    public void settDSB_REQ(int tDSB_REQ) {
        this.tDSB_REQ = tDSB_REQ;
    }

    public int gettREQ_AMT() {
        return tREQ_AMT;
    }

    public void settREQ_AMT(int tREQ_AMT) {
        this.tREQ_AMT = tREQ_AMT;
    }

    public int gettDSB_AMT() {
        return tDSB_AMT;
    }

    public void settDSB_AMT(int tDSB_AMT) {
        this.tDSB_AMT = tDSB_AMT;
    }

    public int gettDSB_DBT_AMT() {
        return tDSB_DBT_AMT;
    }

    public void settDSB_DBT_AMT(int tDSB_DBT_AMT) {
        this.tDSB_DBT_AMT = tDSB_DBT_AMT;
    }

    public int gettDSB_CH_AMT() {
        return tDSB_CH_AMT;
    }

    public void settDSB_CH_AMT(int tDSB_CH_AMT) {
        this.tDSB_CH_AMT = tDSB_CH_AMT;
    }

    public int gettDSB_VAT_AMT() {
        return tDSB_VAT_AMT;
    }

    public void settDSB_VAT_AMT(int tDSB_VAT_AMT) {
        this.tDSB_VAT_AMT = tDSB_VAT_AMT;
    }

    public int getfV_REQ() {
        return fV_REQ;
    }

    public void setfV_REQ(int fV_REQ) {
        this.fV_REQ = fV_REQ;
    }

    public int getfV_AUTO() {
        return fV_AUTO;
    }

    public void setfV_AUTO(int fV_AUTO) {
        this.fV_AUTO = fV_AUTO;
    }

    public int getfV_MANUAL() {
        return fV_MANUAL;
    }

    public void setfV_MANUAL(int fV_MANUAL) {
        this.fV_MANUAL = fV_MANUAL;
    }

    public int getfV_PENDING() {
        return fV_PENDING;
    }

    public void setfV_PENDING(int fV_PENDING) {
        this.fV_PENDING = fV_PENDING;
    }

    public int getcIB_REQ() {
        return cIB_REQ;
    }

    public void setcIB_REQ(int cIB_REQ) {
        this.cIB_REQ = cIB_REQ;
    }

    public int getcIB_VERIFIED() {
        return cIB_VERIFIED;
    }

    public void setcIB_VERIFIED(int cIB_VERIFIED) {
        this.cIB_VERIFIED = cIB_VERIFIED;
    }

    public int getcIB_CL() {
        return cIB_CL;
    }

    public void setcIB_CL(int cIB_CL) {
        this.cIB_CL = cIB_CL;
    }

    public int getcIB_UCL() {
        return cIB_UCL;
    }

    public void setcIB_UCL(int cIB_UCL) {
        this.cIB_UCL = cIB_UCL;
    }

    public int getcIB_PENDING() {
        return cIB_PENDING;
    }

    public void setcIB_PENDING(int cIB_PENDING) {
        this.cIB_PENDING = cIB_PENDING;
    }

    @Override
    public String toString() {
        return "NanoDashboardRes{" + "outCode=" + outCode + ", outMessage=" + outMessage + ", tREQ=" + tREQ + ", tSUC_REQ=" + tSUC_REQ + ", tFAIL_REQ=" + tFAIL_REQ + ", tDSB_REQ=" + tDSB_REQ + ", tREQ_AMT=" + tREQ_AMT + ", tDSB_AMT=" + tDSB_AMT + ", tDSB_DBT_AMT=" + tDSB_DBT_AMT + ", tDSB_CH_AMT=" + tDSB_CH_AMT + ", tDSB_VAT_AMT=" + tDSB_VAT_AMT + ", fV_REQ=" + fV_REQ + ", fV_AUTO=" + fV_AUTO + ", fV_MANUAL=" + fV_MANUAL + ", fV_PENDING=" + fV_PENDING + ", cIB_REQ=" + cIB_REQ + ", cIB_VERIFIED=" + cIB_VERIFIED + ", cIB_CL=" + cIB_CL + ", cIB_UCL=" + cIB_UCL + ", cIB_PENDING=" + cIB_PENDING + '}';
    }
  
 
}
