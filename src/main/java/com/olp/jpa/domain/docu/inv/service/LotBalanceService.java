/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.inv.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
public interface LotBalanceService extends IJpaService<LotBalanceEntity, Long> { 

  public void validate(LotBalanceEntity entity) throws EntityValidationException;
  
}
