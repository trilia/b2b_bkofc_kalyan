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
 * Class: InwardShipmentContainer.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="inward-shipment", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"inwardShipmentList"})
public class InwardShipmentContainer implements EntityContainerTemplate<InwardShipment, Long> {
  
  @XmlElement( name = "inward-shipment" )
  private List<InwardShipment> inwardShipmentList = new ArrayList<>();
  
  @Override
  public List<InwardShipment> getEntityList() {
      return(inwardShipmentList);
  }
  
  public void setEntityList(List<InwardShipment> list) {
    if(list != null) {
      this.inwardShipmentList = list;
    }
    else {
      this.inwardShipmentList.clear();
    }
    
  }
  
}