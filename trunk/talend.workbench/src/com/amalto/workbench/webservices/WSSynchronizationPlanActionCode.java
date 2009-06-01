// This class was generated by the JAXRPC SI, do not edit.
// Contents subject to change without notice.
// JAX-RPC Standard Implementation ��1.1.2_01������� R40��
// Generated source version: 1.1.2

package com.amalto.workbench.webservices;


import java.util.HashMap;
import java.util.Map;

public class WSSynchronizationPlanActionCode {
    private java.lang.String value;
    private static Map valueMap = new HashMap();
    public static final String _START_FULLString = "START_FULL";
    public static final String _START_DIFFERENTIALString = "START_DIFFERENTIAL";
    public static final String _STOPString = "STOP";
    public static final String _RESETString = "RESET";
    public static final String _STATUSString = "STATUS";
    
    public static final java.lang.String _START_FULL = new java.lang.String(_START_FULLString);
    public static final java.lang.String _START_DIFFERENTIAL = new java.lang.String(_START_DIFFERENTIALString);
    public static final java.lang.String _STOP = new java.lang.String(_STOPString);
    public static final java.lang.String _RESET = new java.lang.String(_RESETString);
    public static final java.lang.String _STATUS = new java.lang.String(_STATUSString);
    
    public static final WSSynchronizationPlanActionCode START_FULL = new WSSynchronizationPlanActionCode(_START_FULL);
    public static final WSSynchronizationPlanActionCode START_DIFFERENTIAL = new WSSynchronizationPlanActionCode(_START_DIFFERENTIAL);
    public static final WSSynchronizationPlanActionCode STOP = new WSSynchronizationPlanActionCode(_STOP);
    public static final WSSynchronizationPlanActionCode RESET = new WSSynchronizationPlanActionCode(_RESET);
    public static final WSSynchronizationPlanActionCode STATUS = new WSSynchronizationPlanActionCode(_STATUS);
    
    protected WSSynchronizationPlanActionCode(java.lang.String value) {
        this.value = value;
        valueMap.put(this.toString(), this);
    }
    
    public java.lang.String getValue() {
        return value;
    }
    
    public static WSSynchronizationPlanActionCode fromValue(java.lang.String value)
        throws java.lang.IllegalStateException {
        if (START_FULL.value.equals(value)) {
            return START_FULL;
        } else if (START_DIFFERENTIAL.value.equals(value)) {
            return START_DIFFERENTIAL;
        } else if (STOP.value.equals(value)) {
            return STOP;
        } else if (RESET.value.equals(value)) {
            return RESET;
        } else if (STATUS.value.equals(value)) {
            return STATUS;
        }
        throw new IllegalArgumentException();
    }
    
    public static WSSynchronizationPlanActionCode fromString(String value)
        throws java.lang.IllegalStateException {
        WSSynchronizationPlanActionCode ret = (WSSynchronizationPlanActionCode)valueMap.get(value);
        if (ret != null) {
            return ret;
        }
        if (value.equals(_START_FULLString)) {
            return START_FULL;
        } else if (value.equals(_START_DIFFERENTIALString)) {
            return START_DIFFERENTIAL;
        } else if (value.equals(_STOPString)) {
            return STOP;
        } else if (value.equals(_RESETString)) {
            return RESET;
        } else if (value.equals(_STATUSString)) {
            return STATUS;
        }
        throw new IllegalArgumentException();
    }
    
    public String toString() {
        return value.toString();
    }
    
    private Object readResolve()
        throws java.io.ObjectStreamException {
        return fromValue(getValue());
    }
    
    public boolean equals(Object obj) {
        if (!(obj instanceof WSSynchronizationPlanActionCode)) {
            return false;
        }
        return ((WSSynchronizationPlanActionCode)obj).value.equals(value);
    }
    
    public int hashCode() {
        return value.hashCode();
    }
}
