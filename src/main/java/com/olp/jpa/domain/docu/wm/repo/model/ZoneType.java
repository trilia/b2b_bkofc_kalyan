package com.olp.jpa.domain.docu.wm.repo.model;

/*
 * Trilla Inc Confidential
 * Class: ZoneType.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

public enum ZoneType {

  Storage("Storage"),
  ReceivingDock("Receiving Dock"),
  ShippingDock("Shipping Dock"),
  Sorting("Sorting"),
  PackAndHold("Pack  & Hold"),
  Inspection("Inspection"),
  Miscelleneous("Miscelleneous");
  
  private String zoneType;

  ZoneType(String zoneType) {
      this.zoneType = zoneType;
  }

  public String zoneType() {
      return zoneType;
  }
}
