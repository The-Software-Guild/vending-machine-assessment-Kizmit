package com.jdm.vendingmachine.service;

import com.jdm.vendingmachine.dao.InsufficientFundsException;
import com.jdm.vendingmachine.dao.ItemPersistenceException;
import com.jdm.vendingmachine.dao.NoItemInventoryException;
import com.jdm.vendingmachine.dao.VendingMachineAuditDao;
import com.jdm.vendingmachine.dao.VendingMachineDao;
import com.jdm.vendingmachine.dto.Change;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Joe
 */
public class VendingMachineServiceLayerImplTest {
    VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerImplTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSetInsertedMoneyOverPay() {
        try{
            service.setInsertedMoney(new BigDecimal("5.50"));
        }
        catch(OverPayException | InvalidInputException | ItemPersistenceException e){
            String expected = "Machine doesn't accept more than $5";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testSetInsertedMoneyInvalid() {
        try{
            service.setInsertedMoney(new BigDecimal("-1"));
        }
        catch(OverPayException | InvalidInputException | ItemPersistenceException e){
            String expected = "Invalid monetary amount, value is negative";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    
    @Test
    public void testSetInsertedMoneyValid() {
        boolean valid = false;
        try{
            valid = service.setInsertedMoney(new BigDecimal("4.50"));
        }
        catch(OverPayException | InvalidInputException | ItemPersistenceException e){
            fail("Exception thrown when there shouldn't be");
        }
        finally{
            assertTrue(valid);
        }
    }
    
    @Test
    public void testVendItemNotEnoughMoney() {
        try{
            service.setInsertedMoney(new BigDecimal(".50"));
            service.vendItem("A1");
        }
        catch(InsufficientFundsException | ItemPersistenceException | NoItemInventoryException | InvalidInputException | OverPayException e){
            String expected = "Not enough money inserted to purchase item.";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testVendItemOutOfStock() {
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            service.vendItem("A2");
        }
        catch(InsufficientFundsException | ItemPersistenceException | NoItemInventoryException | InvalidInputException | OverPayException e){
            String expected = "No stock for requested item";
            assertEquals(e.getMessage(), expected);
        }
    }
    
    @Test
    public void testVendItemSuccess() {
        Change change = null;
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            change = service.vendItem("A1");
        }
        catch(InsufficientFundsException | ItemPersistenceException | NoItemInventoryException | InvalidInputException | OverPayException e){
            fail("Exception thrown when there shouldn't be");
        }
        finally{
            assertNotNull(change);
        }
    }    
    
    @Test
    public void testReturnMoney() {
        Change change = null;
        try{
            service.setInsertedMoney(new BigDecimal("4.50"));
            change = service.returnMoney();
        }
        catch(ItemPersistenceException | InvalidInputException | OverPayException e){
            fail("Exception thrown when there shouldn't be");
        }
        finally{
            assertNotNull(change);
            assertEquals(change.getAmount(), new BigDecimal("4.50"));
        }
    }    
    
    
}
