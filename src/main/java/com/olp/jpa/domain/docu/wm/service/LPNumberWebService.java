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

import com.olp.jpa.domain.docu.wm.model.LPNumber;
import com.olp.jpa.domain.docu.wm.model.LPNumber;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.service.IBulkService;
import com.olp.service.ISearchService;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
@WebService(targetNamespace="http://trilia-cloud.com/schema/entity/wm")
public interface LPNumberWebService // extends IBulkService<LPNumber> , ISearchService<LPNumber> 
{
    
  @WebMethod(operationName="listAllLPNumbers")
  public @WebResult(name="lpNumbersResponse") List<LPNumber> findAll();
  
  @WebMethod(operationName="findLPNumber")
  public @WebResult(name="lpNumberResponse") LPNumber findOne(@WebParam(name="lpnId") Long id);
  
  @WebMethod(operationName="addLPNumber")
  public @WebResult(name="lpNumberResponse") LPNumber add(@WebParam(name="lpNumber") LPNumber lpNumber);
    
}
