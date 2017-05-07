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

@XmlRootElement(name="warehouse-locators", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"warehouseLocatorList"})
public class WarehouseLocatorContainer implements EntityContainerTemplate<WarehouseLocator, Long> {
  
  @XmlElement( name = "warehouse-locator" )
  private List<WarehouseLocator> warehouseLocatorList = new ArrayList<>();
  
  @Override
  public List<WarehouseLocator> getEntityList() {
      return(warehouseLocatorList);
  }
  
  public void setEntityList(List<WarehouseLocator> list) {
    if(list != null) {
      this.warehouseLocatorList = list;
    }
    else {
      this.warehouseLocatorList.clear();
    }
  } 
}