package com.olp.jpa.domain.docu.wm.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;

/*
 * Trilla Inc Confidential
 * Class: InwardShipment.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name = "trl-in-shipments", namespace = "http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "tenantId", "shipmentNumber", "shipmentPart", "receivedDate", "receivingWhRef",
    "receivingWhRefId", "logisticAgent", "awbNumber", "shipmentBasis", "externalAsnRef", "asnRef", "asnDate",
    "supplierRef", "revisionControl", "shipmentLines" })
public class InwardShipment {

  @XmlElement(name = "shipment-id")
  private Long id;

  @XmlElement(name = "tenant-id")
  private Long tenantId;

  @XmlElement(name = "shipment-number")
  private String shipmentNumber;

  @XmlElement(name = "shipment-part")
  private Integer shipmentPart;

  @XmlElement(name = "received-date")
  private Date receivedDate;

  @XmlElement(name = "receiving-wh-code")
  private String receivingWhRef;

  private Long receivingWhRefId;

  @XmlElement(name = "logistic-agent")
  private String logisticAgent;

  @XmlElement(name = "awb-number")
  private String awbNumber;

  @XmlElement(name = "shipment-basis")
  private ShipmentBasis shipmentBasis;

  @XmlElement(name = "external_asn_ref")
  private String externalAsnRef;

  @XmlElement(name = "asn-code")
  private String asnRef;

  @XmlElement(name = "asn-date")
  private Date asnDate;

  @XmlElement(name = "supplier-code")
  private String supplierRef;

  @XmlElement(name = "shipment-line")
  private Integer shipmentLines;

  private RevisionControlBean revisionControl;

  
  /**
   * Getter for id
   * 
   * @return the id
   */
  public Long getId() {
    return id;
  }

  
  /**
   * Setter for id to set.
   *
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   * Getter for tenantId
   * 
   * @return the tenantId
   */
  public Long getTenantId() {
    return tenantId;
  }

  
  /**
   * Setter for tenantId to set.
   *
   * @param tenantId the tenantId to set
   */
  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  
  /**
   * Getter for shipmentNumber
   * 
   * @return the shipmentNumber
   */
  public String getShipmentNumber() {
    return shipmentNumber;
  }

  
  /**
   * Setter for shipmentNumber to set.
   *
   * @param shipmentNumber the shipmentNumber to set
   */
  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
  }

  
  /**
   * Getter for shipmentPart
   * 
   * @return the shipmentPart
   */
  public Integer getShipmentPart() {
    return shipmentPart;
  }

  
  /**
   * Setter for shipmentPart to set.
   *
   * @param shipmentPart the shipmentPart to set
   */
  public void setShipmentPart(Integer shipmentPart) {
    this.shipmentPart = shipmentPart;
  }

  
  /**
   * Getter for receivedDate
   * 
   * @return the receivedDate
   */
  public Date getReceivedDate() {
    return receivedDate;
  }

  
  /**
   * Setter for receivedDate to set.
   *
   * @param receivedDate the receivedDate to set
   */
  public void setReceivedDate(Date receivedDate) {
    this.receivedDate = receivedDate;
  }

  
  /**
   * Getter for receivingWhRef
   * 
   * @return the receivingWhRef
   */
  public String getReceivingWhRef() {
    return receivingWhRef;
  }

  
  /**
   * Setter for receivingWhRef to set.
   *
   * @param receivingWhRef the receivingWhRef to set
   */
  public void setReceivingWhRef(String receivingWhRef) {
    this.receivingWhRef = receivingWhRef;
  }

  
  /**
   * Getter for receivingWhRefId
   * 
   * @return the receivingWhRefId
   */
  public Long getReceivingWhRefId() {
    return receivingWhRefId;
  }

  
  /**
   * Setter for receivingWhRefId to set.
   *
   * @param receivingWhRefId the receivingWhRefId to set
   */
  public void setReceivingWhRefId(Long receivingWhRefId) {
    this.receivingWhRefId = receivingWhRefId;
  }

  
  /**
   * Getter for logisticAgent
   * 
   * @return the logisticAgent
   */
  public String getLogisticAgent() {
    return logisticAgent;
  }

  
  /**
   * Setter for logisticAgent to set.
   *
   * @param logisticAgent the logisticAgent to set
   */
  public void setLogisticAgent(String logisticAgent) {
    this.logisticAgent = logisticAgent;
  }

  
  /**
   * Getter for awbNumber
   * 
   * @return the awbNumber
   */
  public String getAwbNumber() {
    return awbNumber;
  }

  
  /**
   * Setter for awbNumber to set.
   *
   * @param awbNumber the awbNumber to set
   */
  public void setAwbNumber(String awbNumber) {
    this.awbNumber = awbNumber;
  }

  
  /**
   * Getter for shipmentBasis
   * 
   * @return the shipmentBasis
   */
  public ShipmentBasis getShipmentBasis() {
    return shipmentBasis;
  }

  
  /**
   * Setter for shipmentBasis to set.
   *
   * @param shipmentBasis the shipmentBasis to set
   */
  public void setShipmentBasis(ShipmentBasis shipmentBasis) {
    this.shipmentBasis = shipmentBasis;
  }

  
  /**
   * Getter for externalAsnRef
   * 
   * @return the externalAsnRef
   */
  public String getExternalAsnRef() {
    return externalAsnRef;
  }

  
  /**
   * Setter for externalAsnRef to set.
   *
   * @param externalAsnRef the externalAsnRef to set
   */
  public void setExternalAsnRef(String externalAsnRef) {
    this.externalAsnRef = externalAsnRef;
  }

  
  /**
   * Getter for asnRef
   * 
   * @return the asnRef
   */
  public String getAsnRef() {
    return asnRef;
  }

  
  /**
   * Setter for asnRef to set.
   *
   * @param asnRef the asnRef to set
   */
  public void setAsnRef(String asnRef) {
    this.asnRef = asnRef;
  }

  
  /**
   * Getter for asnDate
   * 
   * @return the asnDate
   */
  public Date getAsnDate() {
    return asnDate;
  }

  
  /**
   * Setter for asnDate to set.
   *
   * @param asnDate the asnDate to set
   */
  public void setAsnDate(Date asnDate) {
    this.asnDate = asnDate;
  }

  
  /**
   * Getter for supplierRef
   * 
   * @return the supplierRef
   */
  public String getSupplierRef() {
    return supplierRef;
  }

  
  /**
   * Setter for supplierRef to set.
   *
   * @param supplierRef the supplierRef to set
   */
  public void setSupplierRef(String supplierRef) {
    this.supplierRef = supplierRef;
  }

  
  /**
   * Getter for shipmentLines
   * 
   * @return the shipmentLines
   */
  public Integer getShipmentLines() {
    return shipmentLines;
  }

  
  /**
   * Setter for shipmentLines to set.
   *
   * @param shipmentLines the shipmentLines to set
   */
  public void setShipmentLines(Integer shipmentLines) {
    this.shipmentLines = shipmentLines;
  }

  
  /**
   * Getter for revisionControl
   * 
   * @return the revisionControl
   */
  public RevisionControlBean getRevisionControl() {
    return revisionControl;
  }

  
  /**
   * Setter for revisionControl to set.
   *
   * @param revisionControl the revisionControl to set
   */
  public void setRevisionControl(RevisionControlBean revisionControl) {
    this.revisionControl = revisionControl;
  }

  public InwardShipmentEntity convertTo(int mode) {
    
    InwardShipmentEntity bean = new InwardShipmentEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    bean.setShipmentNumber(shipmentNumber);
    bean.setShipmentPart(shipmentPart);
    bean.setReceivedDate(receivedDate);
    
    WarehouseEntity  whEntity = new WarehouseEntity();
    whEntity.setWarehouseCode(receivingWhRef);
    bean.setReceivingWhRef(whEntity);
    
    bean.setReceivingWhRefId(receivingWhRefId);
    bean.setLogisticAgent(logisticAgent);
    bean.setAwbNumber(awbNumber);
    bean.setShipmentBasis(shipmentBasis);
    bean.setExternalAsnRef(externalAsnRef);
    bean.setAsnRef(asnRef);
    bean.setAsnDate(asnDate);
    bean.setSupplierRef(supplierRef);
    bean.setRevisionControl(revisionControl);
    
    InwardShipmentLineEntity shipmentEntity = new InwardShipmentLineEntity();
    shipmentEntity.setLineNumber(shipmentLines);
    bean.setShipmentLines(shipmentEntity);
    
    return(bean);
  }
  
}

