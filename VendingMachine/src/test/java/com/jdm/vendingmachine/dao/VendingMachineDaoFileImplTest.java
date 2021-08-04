package com.jdm.vendingmachine.dao;

import com.jdm.vendingmachine.dto.Change;
import com.jdm.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
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
public class VendingMachineDaoFileImplTest {
    public VendingMachineDao testDao;
    
    public VendingMachineDaoFileImplTest() {
        
    }
    
    @BeforeAll
    public static void setUpClass() {
    
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception {
        String testFile = "testInventory.txt";
        
        testDao =  new VendingMachineDaoFileImpl(testFile);
        testDao.loadInventory();
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetItem() throws Exception {
        assertNotNull(testDao.getItem("A1"));
        assertEquals(testDao.getItem("A1").getName(), "TestItem1");
        assertEquals(testDao.getItem("A1").getMenuKey(), "A1");
        assertEquals(testDao.getItem("A1").getPrice(), new BigDecimal("3.50"));
        assertEquals(testDao.getItem("A1").getStock(), 5);
    }
    
    @Test
    public void testGetAllItems() throws Exception{
        List<Item> itemList = testDao.getAllItems();
        
        assertNotNull(itemList);
        assertEquals(itemList.size(), 3); //Theres 3 test items in inventoryTest.txt
    }
    
    @Test
    public void testVendItem() throws Exception{
        testDao.vendItem("A1");
        assertEquals(testDao.getItem("A1").getStock(), 4);
    }
    
    @Test
    public void testCalculateChange() throws Exception{
        testDao.setInsertedMoney(new BigDecimal ("4.17"));
        Change change = testDao.calculateChange("A1");
        
        assertNotNull(change);
        assertEquals(change.getAmount(), new BigDecimal(".67"));
        assertEquals(change.getNumOfQuarters(), 2);
        assertEquals(change.getNumOfDimes(), 1);
        assertEquals(change.getNumOfNickels(), 1);
        assertEquals(change.getNumOfPennies(), 2);
    }

}
