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

import com.olp.jpa.domain.docu.wm.model.Warehouse;
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;

/**
 *
 * @author raghosh
 */
@Component("warehouseWebService")
@WebService(serviceName="WarehouseService", endpointInterface="com.olp.jpa.domain.docu.wm.service.WarehouseWebService", targetNamespace="http://trilia-cloud.com/schema/entity/wm")
@Path("/warehousezones")
public class WarehouseWebServiceImpl implements WarehouseWebService {
  
  @Override
  @Transactional(readOnly=true)
  @Path("/")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Warehouse> findAll() {
      
      ArrayList<Warehouse> list2 = new ArrayList<>();
      list2.add(makeWarehouse());
      
      return(list2);
  }

  @Override
  @Transactional(readOnly=true)
  @Path("/warehouse/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Warehouse findOne(@PathParam("id") Long id) {
      
      Warehouse bean2 = makeWarehouse();
      
      return(bean2);
  }

  @Override
  @Transactional
  @Path("/warehouse")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Warehouse add(Warehouse warehouse) {
      
      return(warehouse);
  }
  
  private Warehouse makeWarehouse() {
      
      WarehouseEntity per = new WarehouseEntity();
      per.setId(1001L);
      per.setWarehouseName("Ware House #1");
      per.setTenantId(100L);
      per.setWarehouseCode("WH0001");
      
      Warehouse entity = per.convertTo();
      
      return(entity);
  }
}
