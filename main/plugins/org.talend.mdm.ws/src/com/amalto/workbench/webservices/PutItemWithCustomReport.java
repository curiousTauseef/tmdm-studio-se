
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for putItemWithCustomReport complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="putItemWithCustomReport">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.talend.com/mdm}WSPutItemWithCustomReport" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "putItemWithCustomReport", propOrder = {
    "arg0"
})
public class PutItemWithCustomReport {

    protected WSPutItemWithCustomReport arg0;

    /**
     * Default no-arg constructor
     * 
     */
    public PutItemWithCustomReport() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public PutItemWithCustomReport(final WSPutItemWithCustomReport arg0) {
        this.arg0 = arg0;
    }

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link WSPutItemWithCustomReport }
     *     
     */
    public WSPutItemWithCustomReport getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSPutItemWithCustomReport }
     *     
     */
    public void setArg0(WSPutItemWithCustomReport value) {
        this.arg0 = value;
    }

}
