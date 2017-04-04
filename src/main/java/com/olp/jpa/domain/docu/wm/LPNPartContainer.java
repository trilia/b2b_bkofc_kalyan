package com.olp.jpa.domain.docu.wm;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.olp.jpa.common.EntityContainerTemplate;

/*
 * Trilla Inc Confidential
 * Class: LPNPartContainer.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */

public class LPNPartContainer implements EntityContainerTemplate<LPNPart, Long> {
  
  @XmlElement( name = "lpnpart" )
  private List<LPNPart> lpnPartList;
  
  @Override
  public List<LPNPart> getEntityList() {
      return(lpnPartList);
  }
  
  public void setEntityList(List<LPNPart> list) {
      this.lpnPartList = list;
  }
  
}