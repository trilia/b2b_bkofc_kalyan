/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olp.fwk.common.error.EntityValidationException;
import com.olp.jpa.common.AbstractServiceImpl;
import com.olp.jpa.common.ITextRepository;
import com.olp.jpa.domain.docu.wm.model.LPNPartEntity;
import com.olp.jpa.domain.docu.wm.model.LPNumberEntity;
import com.olp.jpa.domain.docu.wm.repo.LPNPartRepository;
import com.olp.jpa.domain.docu.wm.repo.LPNumberRepository;

/**
 *
 * @author raghosh
 */
@Service("lpnPartService")
public class LPNPartServiceImpl extends AbstractServiceImpl<LPNPartEntity, Long> implements LPNPartService {

  @Autowired
  @Qualifier("lpnPartRepository")
  private LPNPartRepository lpnPartRepo;
  
  @Autowired
  @Qualifier("lpNumberRepository")
  private LPNumberRepository lpNumberRepo;
  
  @Override
  @Transactional(readOnly=true, noRollbackFor={javax.persistence.NoResultException.class})
  public void validate(LPNPartEntity entity) throws EntityValidationException {

    // LP Number
    LPNumberEntity originLpn = entity.getLpnRef(), originLpn2 = entity.getChildLpnRef();
      if (originLpn != null) {
          if (originLpn.getId() == null) {
              try {
                  originLpn2 = lpNumberRepo.findByLpNumber(originLpn.getWarehouseRef().getWarehouseCode(), originLpn.getLpnCode());
              } catch (javax.persistence.NoResultException ex) {
                  throw new EntityValidationException("Could not find origin LP Number with code - " + originLpn.getLpnCode());
              }
          } else {
              try {
                  originLpn2 = lpNumberRepo.findOne(originLpn.getId());
              } catch (javax.persistence.NoResultException ex) {
                  throw new EntityValidationException("Could not find origin LP Number with id - " + originLpn.getId());
              }
          }
          if (originLpn2 ==  null)
              throw new EntityValidationException("Could not find origin LP Number with code or id !");
      }
      
      // Child LP Number
      LPNumberEntity childLpn = entity.getLpnRef(), childLpn2 = entity.getChildLpnRef();
        if (childLpn != null) {
            if (childLpn.getId() == null) {
                try {
                    childLpn2 = lpNumberRepo.findByLpNumber(childLpn.getWarehouseRef().getWarehouseCode(), childLpn.getLpnCode());
                } catch (javax.persistence.NoResultException ex) {
                    throw new EntityValidationException("Could not find child LP Number with code - " + childLpn.getLpnCode());
                }
            } else {
                try {
                    childLpn2 = lpNumberRepo.findOne(childLpn.getId());
                } catch (javax.persistence.NoResultException ex) {
                    throw new EntityValidationException("Could not find child LP Number with id - " + childLpn.getId());
                }
            }
            if (childLpn2 ==  null)
                throw new EntityValidationException("Could not find child LP Number with code or id !");
        }
      
      this.updateTenantWithRevision(entity);
  }

  @Override
  protected String getAlternateKeyAsString(LPNPartEntity entity) {
        StringBuilder buff = new StringBuilder();
        buff.append("{ \"lpn_code\" : \"").append(entity.getLpnRef().getLpnCode()).append("\" }");
        
        return(buff.toString());
    }
    

  @Override
  protected JpaRepository<LPNPartEntity, Long> getRepository() {
      return(lpnPartRepo);
  }

  @Override
  protected ITextRepository<LPNPartEntity, Long> getTextRepository() {
      return(lpnPartRepo);
  }


  @Override
  protected Outcome postProcess(int opCode, LPNPartEntity entity) throws EntityValidationException {
      
      Outcome result = new Outcome();
      result.setResult(true);
      
      switch(opCode) {
          case ADD:
          case ADD_BULK:
          case UPDATE:
          case UPDATE_BULK:
          case DELETE:
          case DELETE_BULK:
          default:
              break;
      }
      
      return(result);
  }

  @Override
  protected Outcome preProcess(int opCode, LPNPartEntity entity) throws EntityValidationException {
      
      Outcome result = new Outcome();
      result.setResult(true);
      
      switch(opCode) {
          case ADD:
          case ADD_BULK:
              preProcessAdd(entity);
              break;
          case UPDATE:
          case UPDATE_BULK:
              preProcessUpdate(entity);
              break;
          case DELETE:
          case DELETE_BULK:
          default:
              break;
      }
      
      return(result);
  }
  
  private void preProcessAdd(LPNPartEntity entity) throws EntityValidationException {
    validate(entity);
    entity.setQuantity(1);
  }

  private void preProcessUpdate(LPNPartEntity entity) throws EntityValidationException {
        validate(entity);
  }
  
}
