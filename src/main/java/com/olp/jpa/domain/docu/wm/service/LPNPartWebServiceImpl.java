/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.olp.jpa.domain.docu.wm.model.LPNPart;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;

/**
 *
 * @author raghosh
 */
@Component("lpnPartWebService")
@WebService(serviceName="LPNPartService", endpointInterface="com.olp.jpa.domain.docu.wm.service.LPNPartWebService", targetNamespace="http://trilia-cloud.com/schema/entity/wm")
@Path("/lpnparts")
public class LPNPartWebServiceImpl implements LPNPartWebService {
  
  @Override
  @Transactional(readOnly=true)
  @Path("/")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<LPNPart> findAll() {
      
      ArrayList<LPNPart> list2 = new ArrayList<>();
      list2.add(makeLPNPart());
      
      return(list2);
  }

  @Override
  @Transactional(readOnly=true)
  @Path("/lpn/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public LPNPart findOne(@PathParam("id") Long id) {
      
      LPNPart bean2 = makeLPNPart();
      
      return(bean2);
  }

  @Override
  @Transactional
  @Path("/lpn")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public LPNPart add(LPNPart lpn) {
      
      return(lpn);
  }
  
  private LPNPart makeLPNPart() {
      
      LPNPartEntity per = new LPNPartEntity();
      per.setId(1001L);
      per.setBatchNumber("B000001");
      per.setQuantity(1);
      
      LPNPart entity = per.convertTo();
      
      return(entity);
  }
}
