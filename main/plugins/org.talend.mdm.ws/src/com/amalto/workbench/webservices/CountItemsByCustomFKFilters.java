
package com.amalto.workbench.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for countItemsByCustomFKFilters complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="countItemsByCustomFKFilters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.talend.com/mdm}WSCountItemsByCustomFKFilters" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "countItemsByCustomFKFilters", propOrder = {
    "arg0"
})
public class CountItemsByCustomFKFilters {

    protected WSCountItemsByCustomFKFilters arg0;

    /**
     * Default no-arg constructor
     * 
     */
    public CountItemsByCustomFKFilters() {
        super();
    }

    /**
     * Fully-initialising value constructor
     * 
     */
    public CountItemsByCustomFKFilters(final WSCountItemsByCustomFKFilters arg0) {
        this.arg0 = arg0;
    }

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link WSCountItemsByCustomFKFilters }
     *     
     */
    public WSCountItemsByCustomFKFilters getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link WSCountItemsByCustomFKFilters }
     *     
     */
    public void setArg0(WSCountItemsByCustomFKFilters value) {
        this.arg0 = value;
    }

}
