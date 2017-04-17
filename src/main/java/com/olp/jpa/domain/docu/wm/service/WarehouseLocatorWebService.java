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

import com.olp.jpa.domain.docu.wm.model.WarehouseLocator;
import com.olp.jpa.domain.docu.wm.model.WarehouseLocator;
import com.olp.service.IBulkService;
import com.olp.service.ISearchService;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
@WebService(targetNamespace="http://trilia-cloud.com/schema/entity/wm")
public interface WarehouseLocatorWebService //extends IBulkService<WarehouseLocator> , ISearchService<WarehouseLocator> 
{
    
  @WebMethod(operationName="listAllWarehousesLocator")
  public @WebResult(name="warehouseLocatorsResponse") List<WarehouseLocator> findAll();
  
  @WebMethod(operationName="findWarehouseLocator")
  public @WebResult(name="warehouseLocatorResponse") WarehouseLocator findOne(@WebParam(name="warehouseId") Long id);
  
  @WebMethod(operationName="addWarehouseLocator")
  public @WebResult(name="warehouseLocatorResponse") WarehouseLocator add(@WebParam(name="warehouseLocator") WarehouseLocator warehouseLocator);

}
