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
 * Class: BatchInfoBean.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Embeddable
public class BatchInfoBean {

  @Column(name="batch_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="batch-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="batch-number")
  private String batchNumber;
  
  @Column(name="manufacture_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="manufacture-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="manufacture-date")
  private Date manufactureDate;
  
  @Column(name="use_by_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="use-by-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="use-by-date")
  private Date useByDate;

  @Column(name="expiry_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="expiry-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  @XmlElement(name="expiry-date")
  private Date expiryDate;

  
  /**
   * Getter for batchNumber
   * 
   * @return the batchNumber
   */
  public String getBatchNumber() {
    return batchNumber;
  }

  
  /**
   * Setter for batchNumber to set.
   *
   * @param batchNumber the batchNumber to set
   */
  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
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

  
  /**
   * Getter for useByDate
   * 
   * @return the useByDate
   */
  public Date getUseByDate() {
    return useByDate;
  }

  
  /**
   * Setter for useByDate to set.
   *
   * @param useByDate the useByDate to set
   */
  public void setUseByDate(Date useByDate) {
    this.useByDate = useByDate;
  }

  
  /**
   * Getter for expiryDate
   * 
   * @return the expiryDate
   */
  public Date getExpiryDate() {
    return expiryDate;
  }

  
  /**
   * Setter for expiryDate to set.
   *
   * @param expiryDate the expiryDate to set
   */
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
  
}
