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

@XmlRootElement(name="warehouses", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"warehouseList"})
public class WarehouseZoneContainer implements EntityContainerTemplate<Warehouse, Long> {
  
  @XmlElement( name = "warehouse" )
  private List<Warehouse> warehouseList = new ArrayList<>();
  
  @Override
  public List<Warehouse> getEntityList() {
      return(warehouseList);
  }
  
  public void setEntityList(List<Warehouse> list) {
    if(list != null) {
      this.warehouseList = list;
    }
    else {
      this.warehouseList.clear();
    }
  } 
}