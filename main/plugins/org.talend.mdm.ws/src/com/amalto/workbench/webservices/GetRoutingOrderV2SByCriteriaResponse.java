
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRoutingOrderV2SByCriteriaResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRoutingOrderV2SByCriteriaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.talend.com/mdm}wsRoutingOrderV2Array" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRoutingOrderV2SByCriteriaResponse", propOrder = {
    "_return"
})
public class GetRoutingOrderV2SByCriteriaResponse {

    @XmlElement(name = "return")
    protected WsRoutingOrderV2Array _return;

    /**
     * Default no-arg constructor
     * 
     */
    public GetRoutingOrderV2SByCriteriaResponse() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public GetRoutingOrderV2SByCriteriaResponse(final WsRoutingOrderV2Array _return) {
        this._return = _return;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WsRoutingOrderV2Array }
     *     
     */
    public WsRoutingOrderV2Array getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WsRoutingOrderV2Array }
     *     
     */
    public void setReturn(WsRoutingOrderV2Array value) {
        this._return = value;
    }

}