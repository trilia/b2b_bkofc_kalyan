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

import com.olp.jpa.domain.docu.wm.model.LPNumber;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;

/**
 *
 * @author raghosh
 */
@Component("lpNumberWebService")
@WebService(serviceName="LPNumberService", endpointInterface="com.olp.jpa.domain.docu.wm.service.LPNumberWebService", targetNamespace="http://trilia-cloud.com/schema/entity/wm")
@Path("/lpnumbers")
public class LPNumberWebServiceImpl implements LPNumberWebService {
  
  @Override
  @Transactional(readOnly=true)
  @Path("/")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<LPNumber> findAll() {
      
      ArrayList<LPNumber> list2 = new ArrayList<>();
      list2.add(makeLPNumber());
      
      return(list2);
  }

  @Override
  @Transactional(readOnly=true)
  @Path("/lpn/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public LPNumber findOne(@PathParam("id") Long id) {
      
      LPNumber bean2 = makeLPNumber();
      
      return(bean2);
  }

  @Override
  @Transactional
  @Path("/lpn")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public LPNumber add(LPNumber lpn) {
      
      return(lpn);
  }
  
  private LPNumber makeLPNumber() {
      
      LPNumberEntity per = new LPNumberEntity();
      per.setId(1001L);
      per.setLpnCode("LP00001");
      per.setTenantId(100L);
      
      LPNumber entity = per.convertTo();
      
      return(entity);
  }
}
