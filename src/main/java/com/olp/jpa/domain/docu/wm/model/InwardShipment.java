package com.olp.jpa.domain.docu.wm.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Trilla Inc Confidential
 * Class: InwardShipment.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="lpn-part", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={ "id", "tenantId", "partitionCode", "productSkuCode", "quantity", "uom", "batchNumber", "validityDate", "serialNumber", "supplierRef", "lpnCode", "childLpnCode", "revisionControl" })
public class InwardShipment {

  @XmlElement(name="lpn-part-id")
  private Long id;
  
  @XmlElement(name="tenant-id")
  private Long tenantId;
  
}
