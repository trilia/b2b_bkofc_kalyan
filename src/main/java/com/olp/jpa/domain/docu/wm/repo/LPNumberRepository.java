/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.repo;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.wm.repo.model.LPNumberEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
public interface LPNumberRepository extends JpaRepository<LPNumberEntity, Long>, ITextRepository<LPNumberEntity, Long> {
    
    public LPNumberEntity findByLpnCode(String warehouseCode, String lpnCode);
    
}
