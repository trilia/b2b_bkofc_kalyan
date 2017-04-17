/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.AbstractRepositoryImpl;
import com.olp.jpa.domain.docu.ut.model.PersonEntity;
import com.olp.jpa.domain.docu.ut.model.PersonEntityPub;
import com.olp.jpa.domain.docu.wm.model.WarehouseZone;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;
import com.olp.jpa.domain.docu.wm.repo.WarehouseZoneRepository;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author raghosh
 */
@Component("warehouseZoneWebService")
@WebService(serviceName="WarehouseZoneService", endpointInterface="com.olp.jpa.domain.docu.wm.service.WarehouseZoneWebService", targetNamespace="http://trilia-cloud.com/schema/entity/wm")
@Path("/warehousezones")
public class WarehouseZoneWebServiceImpl implements WarehouseZoneWebService {
  
  @Override
  @Transactional(readOnly=true)
  @Path("/")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<WarehouseZone> findAll() {
      
      ArrayList<WarehouseZone> list2 = new ArrayList<>();
      list2.add(makeWarehouseZone());
      
      return(list2);
  }

  @Override
  @Transactional(readOnly=true)
  @Path("/whzone/{id}")
  @GET
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WarehouseZone findOne(@PathParam("id") Long id) {
      
      WarehouseZone bean2 = makeWarehouseZone();
      
      return(bean2);
  }

  @Override
  @Transactional
  @Path("/whzone")
  @PUT
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public WarehouseZone add(WarehouseZone warehouseZone) {
      
      return(warehouseZone);
  }
  
  private WarehouseZone makeWarehouseZone() {
      
      WarehouseZoneEntity per = new WarehouseZoneEntity();
      per.setId(1001L);
      per.setSubInventory("SUBI000001");
      per.setZoneCode("Z00129");
      per.setTenantId(100L);
      
      WarehouseZone entity = per.convertTo();
      
      return(entity);
  }
}
