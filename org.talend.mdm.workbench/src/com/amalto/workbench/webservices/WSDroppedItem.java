// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation ��1.1.2_01������� R40��
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;


public class WSDroppedItem {
    protected java.lang.String revisionID;
    protected com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK;
    protected java.lang.String uniqueId;
    protected java.lang.String conceptName;
    protected java.lang.String[] ids;
    protected java.lang.String partPath;
    protected java.lang.String insertionUserName;
    protected java.lang.Long insertionTime;
    protected java.lang.String projection;
    
    public WSDroppedItem() {
    }
    
    public WSDroppedItem(java.lang.String revisionID, com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK, java.lang.String uniqueId, java.lang.String conceptName, java.lang.String[] ids, java.lang.String partPath, java.lang.String insertionUserName, java.lang.Long insertionTime, java.lang.String projection) {
        this.revisionID = revisionID;
        this.wsDataClusterPK = wsDataClusterPK;
        this.uniqueId = uniqueId;
        this.conceptName = conceptName;
        this.ids = ids;
        this.partPath = partPath;
        this.insertionUserName = insertionUserName;
        this.insertionTime = insertionTime;
        this.projection = projection;
    }
    
    public java.lang.String getRevisionID() {
        return revisionID;
    }
    
    public void setRevisionID(java.lang.String revisionID) {
        this.revisionID = revisionID;
    }
    
    public com.amalto.workbench.webservices.WSDataClusterPK getWsDataClusterPK() {
        return wsDataClusterPK;
    }
    
    public void setWsDataClusterPK(com.amalto.workbench.webservices.WSDataClusterPK wsDataClusterPK) {
        this.wsDataClusterPK = wsDataClusterPK;
    }
    
    public java.lang.String getUniqueId() {
        return uniqueId;
    }
    
    public void setUniqueId(java.lang.String uniqueId) {
        this.uniqueId = uniqueId;
    }
    
    public java.lang.String getConceptName() {
        return conceptName;
    }
    
    public void setConceptName(java.lang.String conceptName) {
        this.conceptName = conceptName;
    }
    
    public java.lang.String[] getIds() {
        return ids;
    }
    
    public void setIds(java.lang.String[] ids) {
        this.ids = ids;
    }
    
    public java.lang.String getPartPath() {
        return partPath;
    }
    
    public void setPartPath(java.lang.String partPath) {
        this.partPath = partPath;
    }
    
    public java.lang.String getInsertionUserName() {
        return insertionUserName;
    }
    
    public void setInsertionUserName(java.lang.String insertionUserName) {
        this.insertionUserName = insertionUserName;
    }
    
    public java.lang.Long getInsertionTime() {
        return insertionTime;
    }
    
    public void setInsertionTime(java.lang.Long insertionTime) {
        this.insertionTime = insertionTime;
    }
    
    public java.lang.String getProjection() {
        return projection;
    }
    
    public void setProjection(java.lang.String projection) {
        this.projection = projection;
    }
}
