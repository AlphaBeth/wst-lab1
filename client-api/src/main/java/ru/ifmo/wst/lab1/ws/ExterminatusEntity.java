package ru.ifmo.wst.lab1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for exterminatusEntity complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="exterminatusEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="initiator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="method" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planet" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "exterminatusEntity", propOrder = {
        "date",
        "id",
        "initiator",
        "method",
        "planet",
        "reason"
})
public class ExterminatusEntity {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    protected Long id;
    protected String initiator;
    protected String method;
    protected String planet;
    protected String reason;

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the id property.
     *
     * @return possible object is
     * {@link Long }
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value allowed object is
     *              {@link Long }
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the initiator property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInitiator() {
        return initiator;
    }

    /**
     * Sets the value of the initiator property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInitiator(String value) {
        this.initiator = value;
    }

    /**
     * Gets the value of the method property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets the value of the method property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMethod(String value) {
        this.method = value;
    }

    /**
     * Gets the value of the planet property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPlanet() {
        return planet;
    }

    /**
     * Sets the value of the planet property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPlanet(String value) {
        this.planet = value;
    }

    /**
     * Gets the value of the reason property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReason(String value) {
        this.reason = value;
    }

}
