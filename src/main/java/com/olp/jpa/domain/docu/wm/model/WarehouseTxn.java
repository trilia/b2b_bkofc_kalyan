package com.olp.jpa.domain.docu.wm.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Trilla Inc Confidential
 * Class: WarehouseTxn.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="warehouse-txn", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "owningWhRef", "owningWhRefId", "otherWhRef", "otherWhRefId", "transactionType", "txnSubType", "sourceSubInv", "destSubInv", "useLPN", "transactionDate", "expectedQuantity", "transactionQuantity", "quantityUOM", "productSkuRef", "skuRefId", "lpnRef", "lpnRefId", "txnDescription", "txnStatus", "approvalStatus", "shipLineRef", "shipmentNumber", "lineNumber", "revisionControl" })
public class WarehouseTxn {

  @XmlElement(name="wh-txn-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
  @XmlElement(name="owning-wh-code")
  private String owningWhRef;
  
  private String owningWhRefId;
  
  @XmlElement(name="other-wh-code")
  private String otherWhRef;
  
  private String otherWhRefId;
  
  @XmlElement(name="transaction-type")
  private String transactionType;
  
  @XmlElement(name="txn-sub-type")
  private String txnSubType;
  
  @XmlElement(name="source-subinv")
  private String sourceSubInv;
  
  @XmlElement(name="dest-subinv")
  private String destSubInv;
  
  @XmlElement(name="use-lpn-flag")
  private String useLPN;
  
  @XmlElement(name="transaction-date")
  private String transactionDate;
  
  @XmlElement(name="expected-quantity")
  private String expectedQuantity;
  
  @XmlElement(name="transaction-quantity")
  private String transactionQuantity;
  
  @XmlElement(name="quantity-uom")
  private String quantityUOM;
  
  @XmlElement(name="product-sku-code")
  private String productSkuRef;
  
  private String skuRefId;
  
  @XmlElement(name="lpn-code")
  private String lpnRef;
  
  private String lpnRefId;
  
  @XmlElement(name="txn-description")
  private String txnDescription;
  
  @XmlElement(name="txn-status")
  private String txnStatus;
  
  @XmlElement(name="approval-status")
  private String approvalStatus;
  
  private String shipLineRef;
  
  @XmlElement(name="shipment-number")
  private String shipmentNumber;
  
  @XmlElement(name="line-number")
  private String lineNumber;

  private String revisionControl;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the tenantId
   */
  public Long getTenantId() {
    return tenantId;
  }

  /**
   * @param tenantId the tenantId to set
   */
  public void setTenantId(Long tenantId) {
    this.tenantId = tenantId;
  }

  /**
   * @return the owningWhRef
   */
  public String getOwningWhRef() {
    return owningWhRef;
  }

  /**
   * @param owningWhRef the owningWhRef to set
   */
  public void setOwningWhRef(String owningWhRef) {
    this.owningWhRef = owningWhRef;
  }

  /**
   * @return the owningWhRefId
   */
  public String getOwningWhRefId() {
    return owningWhRefId;
  }

  /**
   * @param owningWhRefId the owningWhRefId to set
   */
  public void setOwningWhRefId(String owningWhRefId) {
    this.owningWhRefId = owningWhRefId;
  }

  /**
   * @return the otherWhRef
   */
  public String getOtherWhRef() {
    return otherWhRef;
  }

  /**
   * @param otherWhRef the otherWhRef to set
   */
  public void setOtherWhRef(String otherWhRef) {
    this.otherWhRef = otherWhRef;
  }

  /**
   * @return the otherWhRefId
   */
  public String getOtherWhRefId() {
    return otherWhRefId;
  }

  /**
   * @param otherWhRefId the otherWhRefId to set
   */
  public void setOtherWhRefId(String otherWhRefId) {
    this.otherWhRefId = otherWhRefId;
  }

  /**
   * @return the transactionType
   */
  public String getTransactionType() {
    return transactionType;
  }

  /**
   * @param transactionType the transactionType to set
   */
  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  /**
   * @return the txnSubType
   */
  public String getTxnSubType() {
    return txnSubType;
  }

  /**
   * @param txnSubType the txnSubType to set
   */
  public void setTxnSubType(String txnSubType) {
    this.txnSubType = txnSubType;
  }

  /**
   * @return the sourceSubInv
   */
  public String getSourceSubInv() {
    return sourceSubInv;
  }

  /**
   * @param sourceSubInv the sourceSubInv to set
   */
  public void setSourceSubInv(String sourceSubInv) {
    this.sourceSubInv = sourceSubInv;
  }

  /**
   * @return the destSubInv
   */
  public String getDestSubInv() {
    return destSubInv;
  }

  /**
   * @param destSubInv the destSubInv to set
   */
  public void setDestSubInv(String destSubInv) {
    this.destSubInv = destSubInv;
  }

  /**
   * @return the useLPN
   */
  public String getUseLPN() {
    return useLPN;
  }

  /**
   * @param useLPN the useLPN to set
   */
  public void setUseLPN(String useLPN) {
    this.useLPN = useLPN;
  }

  /**
   * @return the transactionDate
   */
  public String getTransactionDate() {
    return transactionDate;
  }

  /**
   * @param transactionDate the transactionDate to set
   */
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }

  /**
   * @return the expectedQuantity
   */
  public String getExpectedQuantity() {
    return expectedQuantity;
  }

  /**
   * @param expectedQuantity the expectedQuantity to set
   */
  public void setExpectedQuantity(String expectedQuantity) {
    this.expectedQuantity = expectedQuantity;
  }

  /**
   * @return the transactionQuantity
   */
  public String getTransactionQuantity() {
    return transactionQuantity;
  }

  /**
   * @param transactionQuantity the transactionQuantity to set
   */
  public void setTransactionQuantity(String transactionQuantity) {
    this.transactionQuantity = transactionQuantity;
  }

  /**
   * @return the quantityUOM
   */
  public String getQuantityUOM() {
    return quantityUOM;
  }

  /**
   * @param quantityUOM the quantityUOM to set
   */
  public void setQuantityUOM(String quantityUOM) {
    this.quantityUOM = quantityUOM;
  }

  /**
   * @return the productSkuRef
   */
  public String getProductSkuRef() {
    return productSkuRef;
  }

  /**
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(String productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  /**
   * @return the skuRefId
   */
  public String getSkuRefId() {
    return skuRefId;
  }

  /**
   * @param skuRefId the skuRefId to set
   */
  public void setSkuRefId(String skuRefId) {
    this.skuRefId = skuRefId;
  }

  /**
   * @return the lpnRef
   */
  public String getLpnRef() {
    return lpnRef;
  }

  /**
   * @param lpnRef the lpnRef to set
   */
  public void setLpnRef(String lpnRef) {
    this.lpnRef = lpnRef;
  }

  /**
   * @return the lpnRefId
   */
  public String getLpnRefId() {
    return lpnRefId;
  }

  /**
   * @param lpnRefId the lpnRefId to set
   */
  public void setLpnRefId(String lpnRefId) {
    this.lpnRefId = lpnRefId;
  }

  /**
   * @return the txnDescription
   */
  public String getTxnDescription() {
    return txnDescription;
  }

  /**
   * @param txnDescription the txnDescription to set
   */
  public void setTxnDescription(String txnDescription) {
    this.txnDescription = txnDescription;
  }

  /**
   * @return the txnStatus
   */
  public String getTxnStatus() {
    return txnStatus;
  }

  /**
   * @param txnStatus the txnStatus to set
   */
  public void setTxnStatus(String txnStatus) {
    this.txnStatus = txnStatus;
  }

  /**
   * @return the approvalStatus
   */
  public String getApprovalStatus() {
    return approvalStatus;
  }

  /**
   * @param approvalStatus the approvalStatus to set
   */
  public void setApprovalStatus(String approvalStatus) {
    this.approvalStatus = approvalStatus;
  }

  /**
   * @return the shipLineRef
   */
  public String getShipLineRef() {
    return shipLineRef;
  }

  /**
   * @param shipLineRef the shipLineRef to set
   */
  public void setShipLineRef(String shipLineRef) {
    this.shipLineRef = shipLineRef;
  }

  /**
   * @return the shipmentNumber
   */
  public String getShipmentNumber() {
    return shipmentNumber;
  }

  /**
   * @param shipmentNumber the shipmentNumber to set
   */
  public void setShipmentNumber(String shipmentNumber) {
    this.shipmentNumber = shipmentNumber;
  }

  /**
   * @return the lineNumber
   */
  public String getLineNumber() {
    return lineNumber;
  }

  /**
   * @param lineNumber the lineNumber to set
   */
  public void setLineNumber(String lineNumber) {
    this.lineNumber = lineNumber;
  }

  /**
   * @return the revisionControl
   */
  public String getRevisionControl() {
    return revisionControl;
  }

  /**
   * @param revisionControl the revisionControl to set
   */
  public void setRevisionControl(String revisionControl) {
    this.revisionControl = revisionControl;
  }
}
