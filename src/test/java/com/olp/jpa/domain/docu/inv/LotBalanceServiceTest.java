/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olp.jpa.domain.docu.inv;

import com.olp.jpa.domain.docu.inv.model.LotBalance;
import com.olp.jpa.domain.docu.inv.model.LotBalanceEntity;
import com.olp.jpa.domain.docu.inv.service.LotBalanceService;
import com.olp.fwk.common.BaseSpringAwareTest;
import com.olp.fwk.common.ContextManager;
import com.olp.fwk.common.IContext;
import com.olp.jpa.common.RevisionControlBean;
import com.olp.jpa.common.SortCriteriaBean;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import static org.junit.Assert.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author raghosh
 */
public class LotBalanceServiceTest extends BaseSpringAwareTest {
    
    @Autowired
    @Qualifier("lotBalanceService")
    private LotBalanceService __service;
    
    //@Test
    public void test_addLotBalance() {
        LotBalanceEntity lotBalance = makeLotBalance();
        __service.add(lotBalance);
    }
    
    //@Test
    public void test_findLotBalancePaged() {
        
        //PageRequestBean request = new PageRequestBean();
        //request.setPageNum(2);
        //request.setPageSize(4);
        
        PageRequest request = new PageRequest(1, 4);
        
        Page<LotBalanceEntity> response = __service.findAll(request);
        
        assertNotNull("Matching page search should not be null", response);
        assertNotNull("Matching page content should not be null", response.getContent());
        
        assertTrue("Atleast 1 result", response.getContent().size() >= 1);
        assertTrue("Max 4 results", response.getContent().size() == 4);
    }
    
    //@Test
    public void test_simpleSearch1() {
        
        List<LotBalanceEntity> list = __service.findText("cloud", false, (SortCriteriaBean) null);
        
        assertNotNull("Matching cloud keyword should not be null", list);
        assertTrue("Atleast 1 result", list.size() >= 1);
    }
    
    //@Test
    public void test_simpleSearch2() {
        
        SortCriteriaBean sort = new SortCriteriaBean();
        List<String> fields = new ArrayList<>();
        fields.add("lotBalanceCode");
        fields.add("lotBalanceName");
        
        sort.setDescOrderFields(fields);
        
        List<LotBalanceEntity> list = __service.findText("trilia", false, sort);
        
        assertNotNull("Matching trilia keyword should not be null", list);
        assertTrue("Atleast 1 result", list.size() >= 1);
        
    }
    
    //@Test
    public void test_simpleSearch3() {
        
        List<LotBalanceEntity> list = __service.findText("trla", true, (SortCriteriaBean) null);
        
        assertNotNull("Matching fuzzy search should not be null", list);
        assertTrue("Atleast 1 result", list.size() >= 1);
    }
    
    //@Test
    public void test_simpleSearch4() {
        
        //PageRequestBean request = new PageRequestBean();
        //request.setPageNum(2);
        //request.setPageSize(3);
        
        PageRequest request = new PageRequest(1, 3);
        
        Page<LotBalanceEntity> response = __service.findText("trla", true, request);
        
        assertNotNull("Matching page search should not be null", response);
        assertNotNull("Matching page content should not be null", response.getContent());
        
        assertTrue("Atleast 1 result", response.getContent().size() >= 1);
        assertTrue("Max 3 results", response.getContent().size() == 3);
        
    }
    
    //@Test
    public void test_marshalling() throws JAXBException {
        
        //List<LotBalanceEntity> list = __service.findAll();
        
        LotBalanceEntity entity = __service.find(123L);
        
        JAXBContext ctx = JAXBContext.newInstance(LotBalance.class);
        StringWriter writer = new StringWriter();
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(entity, writer);
      
        final String xml = writer.toString();
        System.out.println(xml);
        
    }
    
    private LotBalanceEntity makeLotBalance() {
        
        LotBalanceEntity lotBalance = new LotBalanceEntity();
        
        IContext ctx = ContextManager.getContext();
        String tid = ctx.getTenantId();
        
        lotBalance.setQuantity("1");
        lotBalance.setProductSkuRefId(1000L);;
        lotBalance.setTenantId(Long.parseLong(tid));
        lotBalance.setZoneRefId(1L);
        
        RevisionControlBean ctrl = new RevisionControlBean();
        ctrl.setCreatedById(100);
        ctrl.setCreatedBy("ut@trilia.com");
        ctrl.setCreationDate(new java.util.Date());
        ctrl.setRevisedById(100);
        ctrl.setCreatedBy("ut@trilia.com");
        ctrl.setCreationDate(new java.util.Date());
        ctrl.setObjectVersionNumber("1.0.0");
        
        lotBalance.setRevisionControl(ctrl);
        
        return(lotBalance);
        
    }
}
