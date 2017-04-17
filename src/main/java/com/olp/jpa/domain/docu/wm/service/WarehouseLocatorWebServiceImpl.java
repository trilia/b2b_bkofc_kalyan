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

import com.olp.jpa.domain.docu.wm.model.WarehouseLocator;
import com.olp.jpa.domain.docu.wm.model.WarehouseLocatorEntity;

/**
 *
 * @author raghosh
 */
@Component("warehouseLocatorWebService")
@WebService(serviceName="WarehouseLocatorService", endpointInterface="com.olp.jpa.domain.docu.wm.service.WarehouseLocatorWebService", targetNamespace="http://trilia-cloud.com/schema/entity/wm")
@Path("/warehouselocators")
public class WarehouseLocatorWebServiceImpl implements WarehouseLocatorWebService {
  
  @Override
  @Transactional(readOnly=true)
  @Path("/")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<WarehouseLocator> findAll() {
      
      ArrayList<WarehouseLocator> list2 = new ArrayList<>();
      list2.add(makeWarehouseLocator());
      
      return(list2);
  }

  @Override
  @Transactional(readOnly=true)
  @Path("/whlocator/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WarehouseLocator findOne(@PathParam("id") Long id) {
      
      WarehouseLocator bean2 = makeWarehouseLocator();
      
      return(bean2);
  }

  @Override
  @Transactional
  @Path("/whlocator")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WarehouseLocator add(WarehouseLocator warehouseLocator) {
      
      return(warehouseLocator);
  }
  
  private WarehouseLocator makeWarehouseLocator() {
      
      WarehouseLocatorEntity per = new WarehouseLocatorEntity();
      per.setId(1001L);
      per.setBinValue("B000001");
      per.setTenantId(100L);
      
      WarehouseLocator entity = per.convertTo();
      
      return(entity);
  }
}
