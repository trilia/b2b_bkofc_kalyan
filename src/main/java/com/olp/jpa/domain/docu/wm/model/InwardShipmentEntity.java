package com.olp.jpa.domain.docu.wm.model;

import java.util.Date;

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

/*
 * Trilla Inc Confidential
 * Class: InwardShipmentEntity.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@Entity
@Table(name="trl_in_shipments"
      , uniqueConstraints=@UniqueConstraint(columnNames={"tenant_id"})
)
@NamedQueries({
})
@Cacheable(true)
@Indexed(index="UnitTest")
@FullTextFilterDef(name="filter-shipment-by-tenant", impl=TenantBasedSearchFilterFactory.class)
@MultiTenant(level = MultiTenant.Levels.ONE_TENANT)
public class InwardShipmentEntity {

  private static final long serialVersionUID = -1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="shipment-id", nullable=false)
  @DocumentId
  private Long id;

  @KeyAttribute
  @Column(name="tenant_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long tenantId;

  @Column(name="shipment_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String shipmentNumber;
  
  @Column(name="shipment_part", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-part", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Integer  shipmentPart;
  
  @Column(name="received_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="received-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Date  receivedDate;
  
  @Column(name="receiving_wh_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="receiving-wh-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private WarehouseEntity receivingWhRef;
  
  @Column(name="receiving_wh_ref_id", nullable=false)
  @Field(analyze=Analyze.NO, store = Store.YES)
  private Long  receivingWhRefId;
  
  @Column(name="logistic_agent", nullable=false)
  @Fields({
      @Field,
      @Field(name="logistic-agent", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String  logisticAgent;
    
  @Column(name="awb_number", nullable=false)
  @Fields({
      @Field,
      @Field(name="awb-number", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String  awbNumber;
    
  @Column(name="shipment_basis", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-basis", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private ShipmentBasis shipmentBasis;
  
  @Column(name="external-asn-code", nullable=false)
  @Fields({
      @Field,
      @Field(name="external_asn_ref", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String  externalAsnRef;
  
  @Column(name="asn_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="asn-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String  asnRef;
  
  @Column(name="asn_date", nullable=false)
  @Fields({
      @Field,
      @Field(name="asn-date", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private Date  asnDate;
  
  @Column(name="supplier_ref", nullable=false)
  @Fields({
      @Field,
      @Field(name="supplier-code", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private String  supplierRef;
  
  @Column(name="quantity", nullable=false)
  @Fields({
      @Field,
      @Field(name="shipment-line", index=Index.YES, analyze=Analyze.NO, store=Store.NO)
  })
  private InwardShipmentLineEntity shipmentLines;

  @Embedded
  @IndexedEmbedded
  private RevisionControlBean  revisionControl;

  
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
  public WarehouseEntity getReceivingWhRef() {
    return receivingWhRef;
  }

  
  /**
   * Setter for receivingWhRef to set.
   *
   * @param receivingWhRef the receivingWhRef to set
   */
  public void setReceivingWhRef(WarehouseEntity receivingWhRef) {
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
  public InwardShipmentLineEntity getShipmentLines() {
    return shipmentLines;
  }

  
  /**
   * Setter for shipmentLines to set.
   *
   * @param shipmentLines the shipmentLines to set
   */
  public void setShipmentLines(InwardShipmentLineEntity shipmentLines) {
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
  
  public InwardShipment convertTo(int mode) {
    
    InwardShipment bean = new InwardShipment();
    
    if (mode <= Constants.CONV_COMPLETE_DEFINITION)
        bean.setId(this.id);
    
    bean.setId(this.id);
    bean.setTenantId(this.tenantId);
    
    bean.setShipmentNumber(shipmentNumber);
    bean.setShipmentPart(shipmentPart);
    bean.setReceivedDate(receivedDate);
    bean.setReceivingWhRef(receivingWhRef.getWarehouseCode());
    bean.setReceivingWhRefId(receivingWhRefId);
    bean.setLogisticAgent(logisticAgent);
    bean.setAwbNumber(awbNumber);
    bean.setShipmentBasis(shipmentBasis);
    bean.setExternalAsnRef(externalAsnRef);
    bean.setAsnRef(asnRef);
    bean.setAsnDate(asnDate);
    bean.setSupplierRef(supplierRef);
    bean.setRevisionControl(revisionControl);
    bean.setShipmentLines(shipmentLines.getLineNumber());    
    if (mode <= Constants.CONV_WITH_REVISION_INFO)
        bean.setRevisionControl(this.revisionControl);
    
    
    return(bean);
  }

  
}
