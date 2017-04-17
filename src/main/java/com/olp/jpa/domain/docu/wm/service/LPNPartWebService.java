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

import com.olp.jpa.domain.docu.wm.model.LPNPart;
import com.olp.service.IBulkService;
import com.olp.service.ISearchService;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
@WebService(targetNamespace="http://trilia-cloud.com/schema/entity/wm")
public interface LPNPartWebService //extends IBulkService<LPNPart> , ISearchService<LPNPart> 
{
  @WebMethod(operationName="listAllLPNParts")
  public @WebResult(name="lpnPartsResponse") List<LPNPart> findAll();
  
  @WebMethod(operationName="findLPNPart")
  public @WebResult(name="lpnPartResponse") LPNPart findOne(@WebParam(name="lpnPartId") Long id);
  
  @WebMethod(operationName="addLPNPart")
  public @WebResult(name="lpnPartResponse") LPNPart add(@WebParam(name="lpnPart") LPNPart lpnPart);

}
