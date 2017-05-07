package com.olp.jpa.domain.docu.wm.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.olp.jpa.common.EntityContainerTemplate;

/*
 * Trilla Inc Confidential
 * Class: LPNumberContainer.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

@XmlRootElement(name="warehouse-zones", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"warehouseZoneList"})
public class WarehouseContainer implements EntityContainerTemplate<WarehouseZone, Long> {
  
  @XmlElement( name = "warehouse-zone" )
  private List<WarehouseZone> warehouseZoneList = new ArrayList<>();
  
  @Override
  public List<WarehouseZone> getEntityList() {
      return(warehouseZoneList);
  }
  
  public void setEntityList(List<WarehouseZone> list) {
    if(list != null) {
      this.warehouseZoneList = list;
    }
    else {
      this.warehouseZoneList.clear();
    }
  } 
}