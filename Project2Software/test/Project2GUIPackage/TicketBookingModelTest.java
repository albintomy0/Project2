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

public class TicketBookingModelTest {
    
    public TicketBookingModelTest() {
    }
    
    private TicketBookingModel bookingModel;

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setup() {
        bookingModel = new TicketBookingModel();  // Initialize the booking model before each test
    }
    
    @AfterEach
    public void tearDown() {
    }

    // Test to calculate total cost considering only adult tickets
    @Test
    public void testCalculateTotalCost_ValidTickets() {
        double totalCost = bookingModel.calculateTotalCost(2, 1, 20.0);  // 2 adults, 1 child, adult ticket price = 20.0
        assertEquals(40.0, totalCost);  // Total cost should only consider adult tickets (20 * 2)
    }

    // Test to calculate total cost when no tickets are selected
    @Test
    public void testCalculateTotalCost_NoTickets() {
        double totalCost = bookingModel.calculateTotalCost(0, 0, 20.0);  // No tickets selected
        assertEquals(0.0, totalCost);  // Total cost should be 0
    }

    // Test to calculate total cost when only child tickets are selected
    @Test
    public void testCalculateTotalCost_ChildTicketsOnly() {
        double totalCost = bookingModel.calculateTotalCost(0, 2, 20.0);  // 2 child tickets
        assertEquals(0.0, totalCost);  // Total cost should be 0 for child tickets
    }

    // Test to ensure that all months are returned correctly
    @Test
    public void testGetAllMonths_ReturnsAllMonths() {
        String[] months = bookingModel.getAllMonths();
        assertEquals(12, months.length);  // Ensure all 12 months are returned
        assertEquals("01", months[0]);  // Check if January is the first month
        assertEquals("12", months[11]);  // Check if December is the last month
    }

    // Test to ensure validateTicketSelection works correctly when no tickets are selected
    @Test
    public void testValidateTicketSelection_NoTickets() {
        int adultTickets = 0;
        int childTickets = 0;
        boolean result = bookingModel.validateTicketSelection(adultTickets, childTickets);
        assertFalse(result);  // Expect false because no tickets are selected
    }

    // Test to ensure validateTicketSelection works when valid tickets are selected
    @Test
    public void testValidateTicketSelection_ValidTickets() {
        int adultTickets = 2;
        int childTickets = 1;
        boolean result = bookingModel.validateTicketSelection(adultTickets, childTickets);
        assertTrue(result);  // Expect true because valid tickets are selected
    }
}
