package Project2GUIPackage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ReceiptModelTest {

    private ReceiptModel receiptModel;

    @BeforeEach
    public void setup() {
        // Initialize the receipt model before each test
        receiptModel = new ReceiptModel("Hamilton", "2024-12-01", "11:00", "T006", 2, 1, 60.0);
    }

    @Test
    public void testGetDestination() {
        assertEquals("Hamilton", receiptModel.getDestination(), "Destination should be Hamilton");
    }

    @Test
    public void testGetDepartureDate() {
        assertEquals("2024-12-01", receiptModel.getDepartureDate(), "Departure date should be 2024-12-01");
    }

    @Test
    public void testGetDepartureTime() {
        assertEquals("11:00", receiptModel.getDepartureTime(), "Departure time should be 11:00");
    }

    @Test
    public void testGetTrainID() {
        assertEquals("T006", receiptModel.getTrainID(), "Train ID should be T006");
    }

    @Test
    public void testGetAdultTickets() {
        assertEquals(2, receiptModel.getAdultTickets(), "There should be 2 adult tickets");
    }

    @Test
    public void testGetChildTickets() {
        assertEquals(1, receiptModel.getChildTickets(), "There should be 1 child ticket");
    }

    @Test
    public void testGetTotalCost() {
        assertEquals(60.0, receiptModel.getTotalCost(), 0.01, "Total cost should be 60.0");
    }
}
