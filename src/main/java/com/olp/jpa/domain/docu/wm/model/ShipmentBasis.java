package com.olp.jpa.domain.docu.wm.model;

/*
 * Trilla Inc Confidential
 * Class: ZoneType.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

public enum ShipmentBasis {

  PO("PO"), 
  Invoice("Invoice"), 
  ASN("ASN"), 
  InterWh("InterWh");
  
  
  private String shipmentBasis;

  ShipmentBasis(String shipmentBasis) {
      this.shipmentBasis = shipmentBasis;
  }

  public String shipmentBasis() {
      return shipmentBasis;
  }
}
