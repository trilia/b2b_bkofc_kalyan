package com.olp.jpa.domain.docu.wm;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.olp.jpa.common.EntityContainerTemplate;

/*
 * Trilla Inc Confidential
 * Class: LPNumberContainer.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

public class LPNumberContainer implements EntityContainerTemplate<LPNumber, Long> {
  
  @XmlElement( name = "lpnumber" )
  private List<LPNumber> lpNumberList;
  
  @Override
  public List<LPNumber> getEntityList() {
      return(lpNumberList);
  }
  
  public void setEntityList(List<LPNumber> list) {
      this.lpNumberList = list;
  }
  
}