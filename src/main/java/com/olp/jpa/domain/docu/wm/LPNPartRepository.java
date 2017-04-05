/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm;

import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author raghosh
 */
@NoRepositoryBean
public interface LPNPartRepository extends JpaRepository<LPNPartEntity, Long>, ITextRepository<LPNPartEntity, Long> {
    
}
