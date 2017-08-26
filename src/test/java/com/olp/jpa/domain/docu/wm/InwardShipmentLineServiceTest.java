/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.wm;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.SortCriteriaBean;
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;
import com.olp.jpa.domain.docu.wm.model.InwardShipmentLine;
import com.olp.jpa.domain.docu.wm.model.InwardShipmentLineEntity;
import com.olp.jpa.domain.docu.wm.service.InwardShipmentLineService;

/**
 *
 * @author raghosh
 */
public class InwardShipmentLineServiceTest extends BaseSpringAwareTest {
  
  @Autowired
  @Qualifier("inwardShipmentLineService")
  private InwardShipmentLineService __service;
  
  @Before
  public void before() {
      
    __service.deleteAll(false); // deletes all referenced entities
      setupData();
  }
  
  private void setupData() {
    
    InwardShipmentLineEntity me = new InwardShipmentLineEntity();
    
    Calendar.Builder builder = new Calendar.Builder();
    builder.setTimeZone(TimeZone.getTimeZone("GMT"));
    
    __service.add(me);
  }


  //@Test
  public void test_addInwardShipmentLine() {
      InwardShipmentLineEntity inwardShipmentLine = makeInwardShipmentLine();
      __service.add(inwardShipmentLine);
  }
  
  //@Test
  public void test_findInwardShipmentLinePaged() {
      
      //PageRequestBean request = new PageRequestBean();
      //request.setPageNum(2);
      //request.setPageSize(4);
      
      PageRequest request = new PageRequest(1, 4);
      
      Page<InwardShipmentLineEntity> response = __service.findAll(request);
      
      assertNotNull("Matching page search should not be null", response);
      assertNotNull("Matching page content should not be null", response.getContent());
      
      assertTrue("Atleast 1 result", response.getContent().size() >= 1);
      assertTrue("Max 4 results", response.getContent().size() == 4);
  }
  
  //@Test
  public void test_simpleSearch1() {
      
      List<InwardShipmentLineEntity> list = __service.findText("cloud", false, (SortCriteriaBean) null);
      
      assertNotNull("Matching cloud keyword should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
  }
  
  //@Test
  public void test_simpleSearch2() {
      
      SortCriteriaBean sort = new SortCriteriaBean();
      List<String> fields = new ArrayList<>();
      fields.add("inwardShipmentLineCode");
      fields.add("inwardShipmentLineName");
      
      sort.setDescOrderFields(fields);
      
      List<InwardShipmentLineEntity> list = __service.findText("trilia", false, sort);
      
      assertNotNull("Matching trilia keyword should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
      
  }
  
  //@Test
  public void test_simpleSearch3() {
      
      List<InwardShipmentLineEntity> list = __service.findText("trla", true, (SortCriteriaBean) null);
      
      assertNotNull("Matching fuzzy search should not be null", list);
      assertTrue("Atleast 1 result", list.size() >= 1);
  }
  
  //@Test
  public void test_simpleSearch4() {
      
      //PageRequestBean request = new PageRequestBean();
      //request.setPageNum(2);
      //request.setPageSize(3);
      
      PageRequest request = new PageRequest(1, 3);
      
      Page<InwardShipmentLineEntity> response = __service.findText("trla", true, request);
      
      assertNotNull("Matching page search should not be null", response);
      assertNotNull("Matching page content should not be null", response.getContent());
      
      assertTrue("Atleast 1 result", response.getContent().size() >= 1);
      assertTrue("Max 3 results", response.getContent().size() == 3);
      
  }
  
  //@Test
  public void test_marshalling() throws JAXBException {
      
      //List<InwardShipmentLineEntity> list = __service.findAll();
      
      InwardShipmentLineEntity entity = __service.find(123L);
      
      JAXBContext ctx = JAXBContext.newInstance(InwardShipmentLine.class);
      StringWriter writer = new StringWriter();
      Marshaller m = ctx.createMarshaller();
      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      m.marshal(entity, writer);
    
      final String xml = writer.toString();
      System.out.println(xml);
      
  }
  
  private InwardShipmentLineEntity makeInwardShipmentLine() {
      
      InwardShipmentLineEntity inwardShipmentLine = new InwardShipmentLineEntity();
      
      IContext ctx = ContextManager.getContext();
      String tid = ctx.getTenantId();
      
      inwardShipmentLine.setReceivedQty(new BigDecimal(1));
      inwardShipmentLine.setProductSkuRefId(1000L);;
      inwardShipmentLine.setTenantId(Long.parseLong(tid));
      inwardShipmentLine.setExpectedQty(new BigDecimal(1));
      
      RevisionControlBean ctrl = new RevisionControlBean();
      ctrl.setCreatedById(100);
      ctrl.setCreatedBy("ut@trilia.com");
      ctrl.setCreationDate(new java.util.Date());
      ctrl.setRevisedById(100);
      ctrl.setCreatedBy("ut@trilia.com");
      ctrl.setCreationDate(new java.util.Date());
      ctrl.setObjectVersionNumber("1.0.0");
      
      inwardShipmentLine.setRevisionControl(ctrl);
      
      return(inwardShipmentLine);
      
  }
}
