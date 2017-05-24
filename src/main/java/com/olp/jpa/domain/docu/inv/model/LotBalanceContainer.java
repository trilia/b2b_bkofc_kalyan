package com.olp.jpa.domain.docu.inv.model;

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
@XmlRootElement(name="lot-balance", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"lotBalanceList"})
public class LotBalanceContainer implements EntityContainerTemplate<LotBalance, Long> {
  
  @XmlElement( name = "lot-balance" )
  private List<LotBalance> lotBalanceList = new ArrayList<>();
  
  @Override
  public List<LotBalance> getEntityList() {
      return(lotBalanceList);
  }
  
  public void setEntityList(List<LotBalance> list) {
    if(list != null) {
      this.lotBalanceList = list;
    }
    else {
      this.lotBalanceList.clear();
    }
    
  }
  
}