/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.data.repository.NoRepositoryBean;

import com.olp.jpa.domain.docu.wm.model.Warehouse;
import com.olp.jpa.domain.docu.wm.model.WarehouseZone;
import com.olp.jpa.domain.docu.wm.model.WarehouseZone;
import com.olp.jpa.domain.docu.wm.model.WarehouseZoneEntity;
import com.olp.service.IBulkService;
import com.olp.service.ISearchService;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
@WebService(targetNamespace="http://trilia-cloud.com/schema/entity/wm")
public interface WarehouseZoneWebService //extends IBulkService<WarehouseZone> , ISearchService<WarehouseZone> 
{
    
  @WebMethod(operationName="listAllWarehousesZone")
  public @WebResult(name="warehouseZonesResponse") List<WarehouseZone> findAll();
  
  @WebMethod(operationName="findWarehouseZone")
  public @WebResult(name="warehouseZoneResponse") WarehouseZone findOne(@WebParam(name="zoneId") Long id);
  
  @WebMethod(operationName="addWarehouseZone")
  public @WebResult(name="warehouseZoneResponse") WarehouseZone add(@WebParam(name="warehouseZone") WarehouseZone warehouseZone);
    
}
