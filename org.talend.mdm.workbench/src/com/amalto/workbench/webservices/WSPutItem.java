// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation （1.1.2_01，编译版 R40）
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;


public class WSPutItem {
    protected com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK;
    protected java.lang.String xmlString;
    protected com.amalto.workbench.webservices.WSDataModelPK wsDataModelPK;
    protected java.lang.Boolean isUpdate;
    
    public WSPutItem() {
    }
    
    public WSPutItem(com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK, java.lang.String xmlString, com.amalto.workbench.webservices.WSDataModelPK wsDataModelPK, java.lang.Boolean isUpdate) {
        this.wsDataClusterPK = wsDataClusterPK;
        this.xmlString = xmlString;
        this.wsDataModelPK = wsDataModelPK;
        this.isUpdate = isUpdate;
    }
    
    public com.amalto.workbench.webservices.WSDataClusterPK getWsDataClusterPK() {
        return wsDataClusterPK;
    }
    
    public void setWsDataClusterPK(com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK) {
        this.wsDataClusterPK = wsDataClusterPK;
    }
    
    public java.lang.String getXmlString() {
        return xmlString;
    }
    
    public void setXmlString(java.lang.String xmlString) {
        this.xmlString = xmlString;
    }
    
    public com.amalto.workbench.webservices.WSDataModelPK getWsDataModelPK() {
        return wsDataModelPK;
    }
    
    public void setWsDataModelPK(com.amalto.workbench.webservices.WSDataModelPK wsDataModelPK) {
        this.wsDataModelPK = wsDataModelPK;
    }
    
    public java.lang.Boolean getIsUpdate() {
        return isUpdate;
    }
    
    public void setIsUpdate(java.lang.Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}