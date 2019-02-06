package ru.ifmo.wst.lab1.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sqlException complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="sqlException">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.lab1.wst.ifmo.ru/}exception">
 *       &lt;sequence>
 *         &lt;element name="nextException" type="{http://ws.lab1.wst.ifmo.ru/}sqlException" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sqlException", propOrder = {
        "nextException"
})
public class SqlException
        extends Exception {

    protected SqlException nextException;

    /**
     * Gets the value of the nextException property.
     *
     * @return possible object is
     * {@link SqlException }
     */
    public SqlException getNextException() {
        return nextException;
    }

    /**
     * Sets the value of the nextException property.
     *
     * @param value allowed object is
     *              {@link SqlException }
     */
    public void setNextException(SqlException value) {
        this.nextException = value;
    }

}
