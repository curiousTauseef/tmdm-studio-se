
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for routeItemV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="routeItemV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.talend.com/mdm}WSRouteItemV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "routeItemV2", propOrder = {
    "arg0"
})
public class RouteItemV2 {

    protected WSRouteItemV2 arg0;

    /**
     * Default no-arg constructor
     * 
     */
    public RouteItemV2() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public RouteItemV2(final WSRouteItemV2 arg0) {
        this.arg0 = arg0;
    }

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link WSRouteItemV2 }
     *     
     */
    public WSRouteItemV2 getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSRouteItemV2 }
     *     
     */
    public void setArg0(WSRouteItemV2 value) {
        this.arg0 = value;
    }

}
