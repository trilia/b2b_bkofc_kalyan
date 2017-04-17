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
 * Class: LPNPartContainer.java
 * (C) Copyright Trilla Inc. 2017
 * 
 * @author raghosh
 */
@XmlRootElement(name="lpn-parts", namespace="http://trilia-cloud.com/schema/entity/wm")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"lpnPartList"})
public class LPNPartContainer implements EntityContainerTemplate<LPNPart, Long> {
  
  @XmlElement( name = "lpn-part" )
  private List<LPNPart> lpnPartList = new ArrayList<>();
  
  @Override
  public List<LPNPart> getEntityList() {
      return(lpnPartList);
  }
  
  public void setEntityList(List<LPNPart> list) {
    if(list != null) {
      this.lpnPartList = list;
    }
    else {
      this.lpnPartList.clear();
    }
    
  }
  
}