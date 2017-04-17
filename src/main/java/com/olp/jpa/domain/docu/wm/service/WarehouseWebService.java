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
import com.olp.jpa.domain.docu.wm.model.WarehouseEntity;
import com.olp.jpa.domain.docu.wm.model.Warehouse;
import com.olp.service.IBulkService;
import com.olp.service.ISearchService;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
@WebService(targetNamespace="http://trilia-cloud.com/schema/entity/wm")
public interface WarehouseWebService //extends IBulkService<Warehouse> , ISearchService<Warehouse> 
{
    
  @WebMethod(operationName="listAllWarehouses")
  public @WebResult(name="warehousesResponse") List<Warehouse> findAll();
  
  @WebMethod(operationName="findWarehouse")
  public @WebResult(name="warehouseResponse") Warehouse findOne(@WebParam(name="warehouseId") Long id);
  
  @WebMethod(operationName="addWarehouse")
  public @WebResult(name="warehouseResponse") Warehouse add(@WebParam(name="warehouse") Warehouse warehouse);
    
}
