/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.inv.repo;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
public interface LotBalanceRepository extends JpaRepository<LotBalanceEntity, Long>, ITextRepository<LotBalanceEntity, Long> {
 
  public LotBalanceEntity findLoBalance(Long productSkuRefId, Long warehouseRefId);

}
