
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for existsTransformerResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="existsTransformerResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.talend.com/mdm}WSBoolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "existsTransformerResponse", propOrder = {
    "_return"
})
public class ExistsTransformerResponse {

    @XmlElement(name = "return")
    protected WSBoolean _return;

    /**
     * Default no-arg constructor
     * 
     */
    public ExistsTransformerResponse() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public ExistsTransformerResponse(final WSBoolean _return) {
        this._return = _return;
    }

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link WSBoolean }
     *     
     */
    public WSBoolean getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSBoolean }
     *     
     */
    public void setReturn(WSBoolean value) {
        this._return = value;
    }

}
