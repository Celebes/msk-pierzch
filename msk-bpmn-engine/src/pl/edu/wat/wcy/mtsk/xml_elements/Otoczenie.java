//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.06.07 at 10:43:30 PM CEST 
//


package pl.edu.wat.wcy.mtsk.xml_elements;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}rozklad"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dlugoscGenerowania" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="maxIloscZgloszen" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rozklad"
})
@XmlRootElement(name = "otoczenie")
public class Otoczenie {

    @XmlElement(required = true)
    protected Rozklad rozklad;
    @XmlAttribute(name = "dlugoscGenerowania", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String dlugoscGenerowania;
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String id;
    @XmlAttribute(name = "maxIloscZgloszen", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String maxIloscZgloszen;

    /**
     * Gets the value of the rozklad property.
     * 
     * @return
     *     possible object is
     *     {@link Rozklad }
     *     
     */
    public Rozklad getRozklad() {
        return rozklad;
    }

    /**
     * Sets the value of the rozklad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rozklad }
     *     
     */
    public void setRozklad(Rozklad value) {
        this.rozklad = value;
    }

    /**
     * Gets the value of the dlugoscGenerowania property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDlugoscGenerowania() {
        return dlugoscGenerowania;
    }

    /**
     * Sets the value of the dlugoscGenerowania property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDlugoscGenerowania(String value) {
        this.dlugoscGenerowania = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the maxIloscZgloszen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxIloscZgloszen() {
        return maxIloscZgloszen;
    }

    /**
     * Sets the value of the maxIloscZgloszen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxIloscZgloszen(String value) {
        this.maxIloscZgloszen = value;
    }

}
