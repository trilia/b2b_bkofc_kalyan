package com.olp.jpa.domain.docu.wm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.inv.model.SkuBean;

/*
 * Trilla Inc Confidential
 * Class: InwardShipmentLine.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="trl-in-shipments-line", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "lineNumber", "shipmentRef", "productSkuRef", "productSkuRefId", "productDesc", "shippedQty", "expectedQty", "receivedQty", "returnedQty", "shippingUom", "externalPONum", "externalPORev", "poRef", "poRevision", "poLine", "invoiceNumber", "InvoiceLine", "invoiceDate", "asnLine", "manufacturer", "modelNumber", "batchInfo", "serialInfo", "revisionControl" })
public class InwardShipmentLine {

  @XmlElement(name="lpn-part-id")
  private  Long id;
  
  @XmlElement(name="tenant-id")
  private  Long tenantId;
  
  @XmlElement(name="line-number")
  private Integer lineNumber;
    
  @XmlElement(name="shipment-code")
  private String shipmentRef;
    
  @XmlElement(name="sku-code")
  private String productSkuRef;
    
  private  Long productSkuRefId;
    
  @XmlElement(name="product-desc")
  private String productDesc;
    
  @XmlElement(name="shipped-quantity")
  private BigDecimal shippedQty;
    
  @XmlElement(name="shipment-basis")
  private BigDecimal expectedQty;
    
  @XmlElement(name="expected-quantity")
  private BigDecimal receivedQty;
    
  @XmlElement(name="received-quantity")
  private BigDecimal returnedQty;
    
  @XmlElement(name="uom-code")
  private String shippingUom;
    
  @XmlElement(name="external-po-num")
  private String externalPONum;
    
  @XmlElement(name="external-po-rev")
  private Integer externalPORev;
    
  @XmlElement(name="po-number")
  private String poRef;
    
  @XmlElement(name="po-revision")
  private Integer poRevision;
    
  @XmlElement(name="po-line")
  private Integer poLine;
    
  @XmlElement(name="invoice-number")
  private String invoiceNumber;
    
  @XmlElement(name="invoice-line")
  private Integer InvoiceLine;
    
  @XmlElement(name="invoice-date")
  private Date invoiceDate;
    
  @XmlElement(name="asn-line")
  private Integer asnLine;
    
  @XmlElement(name="manufacturer")
  private String manufacturer;
    
  @XmlElement(name="model-number")
  private String modelNumber;
    
  private BatchInfoBean batchInfo;
    
  private List<SerialInfoBean> serialInfo;

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
   * Getter for lineNumber
   * 
   * @return the lineNumber
   */
  public Integer getLineNumber() {
    return lineNumber;
  }

  
  /**
   * Setter for lineNumber to set.
   *
   * @param lineNumber the lineNumber to set
   */
  public void setLineNumber(Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  
  /**
   * Getter for shipmentRef
   * 
   * @return the shipmentRef
   */
  public String getShipmentRef() {
    return shipmentRef;
  }

  
  /**
   * Setter for shipmentRef to set.
   *
   * @param shipmentRef the shipmentRef to set
   */
  public void setShipmentRef(String shipmentRef) {
    this.shipmentRef = shipmentRef;
  }

  
  /**
   * Getter for productSkuRef
   * 
   * @return the productSkuRef
   */
  public String getProductSkuRef() {
    return productSkuRef;
  }

  
  /**
   * Setter for productSkuRef to set.
   *
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(String productSkuRef) {
    this.productSkuRef = productSkuRef;
  }

  
  /**
   * Getter for productSkuRefId
   * 
   * @return the productSkuRefId
   */
  public Long getProductSkuRefId() {
    return productSkuRefId;
  }

  
  /**
   * Setter for productSkuRefId to set.
   *
   * @param productSkuRefId the productSkuRefId to set
   */
  public void setProductSkuRefId(Long productSkuRefId) {
    this.productSkuRefId = productSkuRefId;
  }

  
  /**
   * Getter for productDesc
   * 
   * @return the productDesc
   */
  public String getProductDesc() {
    return productDesc;
  }

  
  /**
   * Setter for productDesc to set.
   *
   * @param productDesc the productDesc to set
   */
  public void setProductDesc(String productDesc) {
    this.productDesc = productDesc;
  }

  
  /**
   * Getter for shippedQty
   * 
   * @return the shippedQty
   */
  public BigDecimal getShippedQty() {
    return shippedQty;
  }

  
  /**
   * Setter for shippedQty to set.
   *
   * @param shippedQty the shippedQty to set
   */
  public void setShippedQty(BigDecimal shippedQty) {
    this.shippedQty = shippedQty;
  }

  
  /**
   * Getter for expectedQty
   * 
   * @return the expectedQty
   */
  public BigDecimal getExpectedQty() {
    return expectedQty;
  }

  
  /**
   * Setter for expectedQty to set.
   *
   * @param expectedQty the expectedQty to set
   */
  public void setExpectedQty(BigDecimal expectedQty) {
    this.expectedQty = expectedQty;
  }

  
  /**
   * Getter for receivedQty
   * 
   * @return the receivedQty
   */
  public BigDecimal getReceivedQty() {
    return receivedQty;
  }

  
  /**
   * Setter for receivedQty to set.
   *
   * @param receivedQty the receivedQty to set
   */
  public void setReceivedQty(BigDecimal receivedQty) {
    this.receivedQty = receivedQty;
  }

  
  /**
   * Getter for returnedQty
   * 
   * @return the returnedQty
   */
  public BigDecimal getReturnedQty() {
    return returnedQty;
  }

  
  /**
   * Setter for returnedQty to set.
   *
   * @param returnedQty the returnedQty to set
   */
  public void setReturnedQty(BigDecimal returnedQty) {
    this.returnedQty = returnedQty;
  }

  
  /**
   * Getter for shippingUom
   * 
   * @return the shippingUom
   */
  public String getShippingUom() {
    return shippingUom;
  }

  
  /**
   * Setter for shippingUom to set.
   *
   * @param shippingUom the shippingUom to set
   */
  public void setShippingUom(String shippingUom) {
    this.shippingUom = shippingUom;
  }

  
  /**
   * Getter for externalPONum
   * 
   * @return the externalPONum
   */
  public String getExternalPONum() {
    return externalPONum;
  }

  
  /**
   * Setter for externalPONum to set.
   *
   * @param externalPONum the externalPONum to set
   */
  public void setExternalPONum(String externalPONum) {
    this.externalPONum = externalPONum;
  }

  
  /**
   * Getter for externalPORev
   * 
   * @return the externalPORev
   */
  public Integer getExternalPORev() {
    return externalPORev;
  }

  
  /**
   * Setter for externalPORev to set.
   *
   * @param externalPORev the externalPORev to set
   */
  public void setExternalPORev(Integer externalPORev) {
    this.externalPORev = externalPORev;
  }

  
  /**
   * Getter for poRef
   * 
   * @return the poRef
   */
  public String getPoRef() {
    return poRef;
  }

  
  /**
   * Setter for poRef to set.
   *
   * @param poRef the poRef to set
   */
  public void setPoRef(String poRef) {
    this.poRef = poRef;
  }

  
  /**
   * Getter for poRevision
   * 
   * @return the poRevision
   */
  public Integer getPoRevision() {
    return poRevision;
  }

  
  /**
   * Setter for poRevision to set.
   *
   * @param poRevision the poRevision to set
   */
  public void setPoRevision(Integer poRevision) {
    this.poRevision = poRevision;
  }

  
  /**
   * Getter for poLine
   * 
   * @return the poLine
   */
  public Integer getPoLine() {
    return poLine;
  }

  
  /**
   * Setter for poLine to set.
   *
   * @param poLine the poLine to set
   */
  public void setPoLine(Integer poLine) {
    this.poLine = poLine;
  }

  
  /**
   * Getter for invoiceNumber
   * 
   * @return the invoiceNumber
   */
  public String getInvoiceNumber() {
    return invoiceNumber;
  }

  
  /**
   * Setter for invoiceNumber to set.
   *
   * @param invoiceNumber the invoiceNumber to set
   */
  public void setInvoiceNumber(String invoiceNumber) {
    this.invoiceNumber = invoiceNumber;
  }

  
  /**
   * Getter for InvoiceLine
   * 
   * @return the invoiceLine
   */
  public Integer getInvoiceLine() {
    return InvoiceLine;
  }

  
  /**
   * Setter for invoiceLine to set.
   *
   * @param invoiceLine the invoiceLine to set
   */
  public void setInvoiceLine(Integer invoiceLine) {
    InvoiceLine = invoiceLine;
  }

  
  /**
   * Getter for invoiceDate
   * 
   * @return the invoiceDate
   */
  public Date getInvoiceDate() {
    return invoiceDate;
  }

  
  /**
   * Setter for invoiceDate to set.
   *
   * @param invoiceDate the invoiceDate to set
   */
  public void setInvoiceDate(Date invoiceDate) {
    this.invoiceDate = invoiceDate;
  }

  
  /**
   * Getter for asnLine
   * 
   * @return the asnLine
   */
  public Integer getAsnLine() {
    return asnLine;
  }

  
  /**
   * Setter for asnLine to set.
   *
   * @param asnLine the asnLine to set
   */
  public void setAsnLine(Integer asnLine) {
    this.asnLine = asnLine;
  }

  
  /**
   * Getter for manufacturer
   * 
   * @return the manufacturer
   */
  public String getManufacturer() {
    return manufacturer;
  }

  
  /**
   * Setter for manufacturer to set.
   *
   * @param manufacturer the manufacturer to set
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  
  /**
   * Getter for modelNumber
   * 
   * @return the modelNumber
   */
  public String getModelNumber() {
    return modelNumber;
  }

  
  /**
   * Setter for modelNumber to set.
   *
   * @param modelNumber the modelNumber to set
   */
  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  
  /**
   * Getter for batchInfo
   * 
   * @return the batchInfo
   */
  public BatchInfoBean getBatchInfo() {
    return batchInfo;
  }

  
  /**
   * Setter for batchInfo to set.
   *
   * @param batchInfo the batchInfo to set
   */
  public void setBatchInfo(BatchInfoBean batchInfo) {
    this.batchInfo = batchInfo;
  }

  
  /**
   * Getter for serialInfo
   * 
   * @return the serialInfo
   */
  public List<SerialInfoBean> getSerialInfo() {
    return serialInfo;
  }

  
  /**
   * Setter for serialInfo to set.
   *
   * @param serialInfo the serialInfo to set
   */
  public void setSerialInfo(List<SerialInfoBean> serialInfo) {
    this.serialInfo = serialInfo;
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
  
  public InwardShipmentLineEntity convertTo(int mode) {
    
    InwardShipmentLineEntity bean = new InwardShipmentLineEntity();
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    bean.setLineNumber(lineNumber);
    
    InwardShipmentEntity shipmentRefEntity = new InwardShipmentEntity();
    shipmentRefEntity.setShipmentNumber(shipmentRef);
    bean.setShipmentRef(shipmentRefEntity);
    
    ProductSkuEntity sku = new ProductSkuEntity();
    SkuBean skuBean = SkuBean.parse(this.productSkuRef);
    sku.setSku(skuBean);
    bean.setProductSkuRef(sku);
    
    bean.setProductSkuRefId(productSkuRefId);
    bean.setProductDesc(productDesc);
    bean.setShippedQty(shippedQty);
    bean.setExpectedQty(expectedQty);
    bean.setReceivedQty(receivedQty);
    bean.setReturnedQty(returnedQty);
    bean.setShippingUom(shippingUom);
    bean.setExternalPONum(externalPONum);
    bean.setExternalPORev(externalPORev);
    bean.setPoRef(poRef);
    bean.setPoRevision(poRevision);
    bean.setPoLine(poLine);
    bean.setInvoiceNumber(invoiceNumber);
    bean.setInvoiceLine(InvoiceLine);
    bean.setInvoiceDate(invoiceDate);
    bean.setAsnLine(asnLine);
    bean.setManufacturer(manufacturer);
    bean.setModelNumber(modelNumber);
    bean.setBatchInfo(batchInfo);
    bean.setSerialInfo(serialInfo);
    bean.setRevisionControl(revisionControl);
    
    return(bean);
  }
  
}
