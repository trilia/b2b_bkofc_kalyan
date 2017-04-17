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

@XmlRootElement(name="lp-numbers", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"lpNumberList"})
public class LPNumberContainer implements EntityContainerTemplate<LPNumber, Long> {
  
  @XmlElement( name = "lp-number" )
  private List<LPNumber> lpNumberList = new ArrayList<>();
  
  @Override
  public List<LPNumber> getEntityList() {
      return(lpNumberList);
  }
  
  public void setEntityList(List<LPNumber> list) {
    if(list != null) {
      this.lpNumberList = list;
    }
    else {
      this.lpNumberList.clear();
    }
  }
  
}