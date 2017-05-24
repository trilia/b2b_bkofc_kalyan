package com.olp.jpa.domain.docu.wm.model;

/*
 * Trilla Inc Confidential
 * Class: ZoneType.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

public enum TransactionType {

  Storage("Storage"),
  ReceivingDock("Receiving Dock"),
  ShippingDock("Shipping Dock"),
  Sorting("Sorting"),
  PackAndHold("Pack  & Hold"),
  Inspection("Inspection"),
  Miscelleneous("Miscelleneous");
  
  private String zoneType;

  TransactionType(String zoneType) {
      this.zoneType = zoneType;
  }

  public String zoneType() {
      return zoneType;
  }
}
