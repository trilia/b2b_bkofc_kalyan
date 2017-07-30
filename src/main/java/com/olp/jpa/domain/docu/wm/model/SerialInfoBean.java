package com.olp.jpa.domain.docu.wm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/*
 * Trilla Inc Confidential
 * Class: SerialInfoBean.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@Embeddable
public class SerialInfoBean {

  @Column(name="serial_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="serial-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="serial-number")
  private String serialNumber;
  
  @Column(name="manufacture_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="manufacture-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="manufacture-date")
  private Date manufactureDate;

  
  /**
   * Getter for serialNumber
   * 
   * @return the serialNumber
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  
  /**
   * Setter for serialNumber to set.
   *
   * @param serialNumber the serialNumber to set
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  
  /**
   * Getter for manufactureDate
   * 
   * @return the manufactureDate
   */
  public Date getManufactureDate() {
    return manufactureDate;
  }

  
  /**
   * Setter for manufactureDate to set.
   *
   * @param manufactureDate the manufactureDate to set
   */
  public void setManufactureDate(Date manufactureDate) {
    this.manufactureDate = manufactureDate;
  }

}
