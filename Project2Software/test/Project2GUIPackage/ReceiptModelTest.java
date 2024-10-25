/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Project2GUIPackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ekans
 */
public class ReceiptModelTest {
    
    public ReceiptModelTest() {
    }
    
    private ReceiptModel receiptModel;

    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setup() {
        receiptModel = new ReceiptModel("Hamilton", "2024-12-01", "11:00", "T006", 2, 1, 60.0);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getDestination method, of class ReceiptModel.
     */
    @Test
    public void testGetDestination() {
        System.out.println("getDestination");
        ReceiptModel instance = null;
        String expResult = "";
        String result = instance.getDestination();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartureDate method, of class ReceiptModel.
     */
    @Test
    public void testGetDepartureDate() {
        System.out.println("getDepartureDate");
        ReceiptModel instance = null;
        String expResult = "";
        String result = instance.getDepartureDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartureTime method, of class ReceiptModel.
     */
    @Test
    public void testGetDepartureTime() {
        System.out.println("getDepartureTime");
        ReceiptModel instance = null;
        String expResult = "";
        String result = instance.getDepartureTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrainID method, of class ReceiptModel.
     */
    @Test
    public void testGetTrainID() {
        System.out.println("getTrainID");
        ReceiptModel instance = null;
        String expResult = "";
        String result = instance.getTrainID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdultTickets method, of class ReceiptModel.
     */
    @Test
    public void testGetAdultTickets() {
        System.out.println("getAdultTickets");
        ReceiptModel instance = null;
        int expResult = 0;
        int result = instance.getAdultTickets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChildTickets method, of class ReceiptModel.
     */
    @Test
    public void testGetChildTickets() {
        System.out.println("getChildTickets");
        ReceiptModel instance = null;
        int expResult = 0;
        int result = instance.getChildTickets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalCost method, of class ReceiptModel.
     */
    @Test
    public void testGetTotalCost() {
        System.out.println("getTotalCost");
        ReceiptModel instance = null;
        double expResult = 0.0;
        double result = instance.getTotalCost();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}