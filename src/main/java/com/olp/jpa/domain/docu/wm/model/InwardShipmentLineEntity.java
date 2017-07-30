package com.olp.jpa.domain.docu.wm.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.FullTextFilterDef;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.olp.annotations.KeyAttribute;
import com.olp.annotations.MultiTenant;
import com.olp.fwk.common.Constants;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.TenantBasedSearchFilterFactory;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;

/*
 * Trilla Inc Confidential
 * Class: InwardShipmentLineEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@Entity
@Table(name="trl_in_shipment_lines"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id"})
)
@NamedQueries({
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-shipment-lines-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class InwardShipmentLineEntity {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="shipment-line-id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @Column(name="line_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="line-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer lineNumber;
  
  @Column(name="shipment_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private InwardShipmentEntity shipmentRef;
  
  @Column(name="product_sku_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="sku-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private ProductSkuEntity productSkuRef;
  
  @Column(name="product_sku_ref_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long productSkuRefId;
  
  @Column(name="product_desc", nullable=false)
  @Fields({
      @Field,
      @Field(name="product-desc", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String productDesc;
  
  @Column(name="shipped_quantity", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipped-quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private BigDecimal shippedQty;
  
  @Column(name="shipment_basis", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-basis", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private BigDecimal expectedQty;
  
  @Column(name="expected_quantity", nullable=false)
  @Fields({
      @Field,
      @Field(name="expected-quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private BigDecimal receivedQty;
  
  @Column(name="received_quantity", nullable=false)
  @Fields({
      @Field,
      @Field(name="received-quantity", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private BigDecimal returnedQty;
  
  @Column(name="shipping_uom", nullable=false)
  @Fields({
      @Field,
      @Field(name="uom-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String shippingUom;
  
  @Column(name="external_po_num", nullable=false)
  @Fields({
      @Field,
      @Field(name="external-po-num", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String externalPONum;
  
  @Column(name="external_po_rev", nullable=false)
  @Fields({
      @Field,
      @Field(name="external-po-rev", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer externalPORev;
  
  @Column(name="po_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="po-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String poRef;
  
  @Column(name="po_revision", nullable=false)
  @Fields({
      @Field,
      @Field(name="po-revision", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer poRevision;
  
  @Column(name="po_line", nullable=false)
  @Fields({
      @Field,
      @Field(name="po-line", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer poLine;
  
  @Column(name="invoice_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="invoice-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String invoiceNumber;
  
  @Column(name="invoice_line", nullable=false)
  @Fields({
      @Field,
      @Field(name="invoice-line", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer InvoiceLine;
  
  @Column(name="invoice_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="invoice-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Date invoiceDate;
  
  @Column(name="asn_line", nullable=false)
  @Fields({
      @Field,
      @Field(name="asn-line", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer asnLine;
  
  @Column(name="manufacturer", nullable=false)
  @Fields({
      @Field,
      @Field(name="manufacturer", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String manufacturer;
  
  @Column(name="model_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="model-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String modelNumber;
  
  private BatchInfoBean batchInfo;
  
  private List<SerialInfoBean> serialInfo;
  
  @Embedded
  @IndexedEmbedded
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
  public InwardShipmentEntity getShipmentRef() {
    return shipmentRef;
  }

  
  /**
   * Setter for shipmentRef to set.
   *
   * @param shipmentRef the shipmentRef to set
   */
  public void setShipmentRef(InwardShipmentEntity shipmentRef) {
    this.shipmentRef = shipmentRef;
  }

  
  /**
   * Getter for productSkuRef
   * 
   * @return the productSkuRef
   */
  public ProductSkuEntity getProductSkuRef() {
    return productSkuRef;
  }

  
  /**
   * Setter for productSkuRef to set.
   *
   * @param productSkuRef the productSkuRef to set
   */
  public void setProductSkuRef(ProductSkuEntity productSkuRef) {
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

  public InwardShipmentLine convertTo(int mode) {
    
    InwardShipmentLine bean = new InwardShipmentLine();
    
    if (mode <= Constants.CONV_COMPLETE_DEFINITION)
        bean.setId(this.id);
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    bean.setLineNumber(lineNumber);
    
    bean.setShipmentRef(shipmentRef.getShipmentNumber());
    
    bean.setProductSkuRef(productSkuRef.getSource());
    
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
    
    if (mode <= Constants.CONV_WITH_REVISION_INFO)
        bean.setRevisionControl(this.revisionControl);
    
    
    return(bean);
  }

}
