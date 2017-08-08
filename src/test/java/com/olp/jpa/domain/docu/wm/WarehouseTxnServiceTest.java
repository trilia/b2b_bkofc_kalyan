/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.SortCriteriaBean;
import com.olp.jpa.domain.docu.inv.model.ProductSkuEntity;
import com.olp.jpa.domain.docu.wm.model.WarehouseTxn;
import com.olp.jpa.domain.docu.wm.model.WarehouseTxnEntity;
import com.olp.jpa.domain.docu.wm.service.WarehouseTxnService;

/**
 *
 * @author raghosh
 */
public class WarehouseTxnServiceTest extends BaseSpringAwareTest {
  
  @Autowired
  @Qualifier("warehouseTxnService")
  private WarehouseTxnService __service;
  
  //@Test
  public void test_addWarehouseTxn() {
      WarehouseTxnEntity warehouseTxn = makeWarehouseTxn();
      __service.add(warehouseTxn);
  }
  
  //@Test
  public void test_findWarehouseTxnPaged() {
      
      //PageRequestBean request = new PageRequestBean();
      //request.setPageNum(2);
      //request.setPageSize(4);
      
      PageRequest request = new PageRequest(1, 4);
      
      Page<WarehouseTxnEntity> response = __service.findAll(request);
      
      assertNotNull("Matching page search should not be null", response);
      assertNotNull("Matching page content should not be null", response.getContent());
      
      assertTrue("Atleast 1 result", response.getContent().size() >= 1);
      assertTrue("Max 4 results", response.getContent().size() == 4);
  }
  
  //@Test
  public void test_simpleSearch1() {
      
      List<WarehouseTxnEntity> list = __service.findText("cloud", false, (SortCriteriaBean) null);
      
      assertNotNull("Matching cloud keyword should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
  }
  
  //@Test
  public void test_simpleSearch2() {
      
      SortCriteriaBean sort = new SortCriteriaBean();
      List<String> fields = new ArrayList<>();
      fields.add("warehouseTxnCode");
      fields.add("warehouseTxnName");
      
      sort.setDescOrderFields(fields);
      
      List<WarehouseTxnEntity> list = __service.findText("trilia", false, sort);
      
      assertNotNull("Matching trilia keyword should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
      
  }
  
  //@Test
  public void test_simpleSearch3() {
      
      List<WarehouseTxnEntity> list = __service.findText("trla", true, (SortCriteriaBean) null);
      
      assertNotNull("Matching fuzzy search should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
  }
  
  //@Test
  public void test_simpleSearch4() {
      
      //PageRequestBean request = new PageRequestBean();
      //request.setPageNum(2);
      //request.setPageSize(3);
      
      PageRequest request = new PageRequest(1, 3);
      
      Page<WarehouseTxnEntity> response = __service.findText("trla", true, request);
      
      assertNotNull("Matching page search should not be null", response);
      assertNotNull("Matching page content should not be null", response.getContent());
      
      assertTrue("Atleast 1 result", response.getContent().size() >= 1);
      assertTrue("Max 3 results", response.getContent().size() == 3);
      
  }
  
  //@Test
  public void test_marshalling() throws JAXBException {
      
      //List<WarehouseTxnEntity> list = __service.findAll();
      
      WarehouseTxnEntity entity = __service.find(123L);
      
      JAXBContext ctx = JAXBContext.newInstance(WarehouseTxn.class);
      StringWriter writer = new StringWriter();
      Marshaller m = ctx.createMarshaller();
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      m.marshal(entity, writer);
    
      final String xml = writer.toString();
      System.out.println(xml);
      
  }
  
  private WarehouseTxnEntity makeWarehouseTxn() {
      
      WarehouseTxnEntity warehouseTxn = new WarehouseTxnEntity();
      
      IContext ctx = ContextManager.getContext();
      String tid = ctx.getTenantId();
      
      warehouseTxn.setExpectedQuantity("1");
      warehouseTxn.setProductSkuRef(new ProductSkuEntity());;
      warehouseTxn.setTenantId(Long.parseLong(tid));
      warehouseTxn.setOtherWhRefId(1000L);
      warehouseTxn.setLpnRefId("LP1000");
      warehouseTxn.setQuantityUOM("1");
      
      RevisionControlBean ctrl = new RevisionControlBean();
      ctrl.setCreatedById(100);
      ctrl.setCreatedBy("ut@trilia.com");
      ctrl.setCreationDate(new java.util.Date());
      ctrl.setRevisedById(100);
      ctrl.setCreatedBy("ut@trilia.com");
      ctrl.setCreationDate(new java.util.Date());
      ctrl.setObjectVersionNumber("1.0.0");
      
      warehouseTxn.setRevisionControl(ctrl);
      
      return(warehouseTxn);
      
  }
}
