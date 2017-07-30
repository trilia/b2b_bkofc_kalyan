/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import org.springframework.data.repository.NoRepositoryBean;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.IJpaService;
import com.olp.jpa.domain.docu.wm.model.InwardShipmentLineEntity;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
public interface InwardShipmentLineService extends IJpaService<InwardShipmentLineEntity, Long> { 

  public void validate(InwardShipmentLineEntity entity) throws EntityValidationException;
  
}
