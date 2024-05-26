import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class SILab2Test {

    private static List<Item> allItems;

    @BeforeAll
    static void AddItem() {
        allItems = new ArrayList<>();}

    @Test
    void everyBranch(){
        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null,100));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

        allItems.add(new Item("item","1m3",100,0));
        RuntimeException exception1 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems,100));
        assertTrue(exception1.getMessage().contains("Invalid character in item barcode!"));
        allItems.clear();

        allItems.add(new Item("item",null,100,0));
        RuntimeException exception2 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems,100));
        assertTrue(exception2.getMessage().contains("No barcode!"));
        allItems.clear();

        allItems.add(new Item("","123",100,0));
        boolean check = SILab2.checkCart(allItems,100);
        assertTrue(check);
        allItems.clear();

        allItems.add(new Item("item","012",350,1));
        allItems.add(new Item("item","123",100,1));
        boolean check2 = SILab2.checkCart(allItems,50);
        assertFalse(check2);
    }

    @Test
    void MultipleCondition(){
        allItems.add(new Item("item","012",350,1));
        assertEquals(320, allItems.get(0).price-30);
        allItems.clear();

        allItems.add(new Item("item","123",350,1));
        boolean check = SILab2.checkCart(allItems,100);
        assertFalse(check);
        allItems.clear();

        allItems.add(new Item("item","",350,0));
        boolean check2 = SILab2.checkCart(allItems,100);
        assertFalse(check2);
        allItems.clear();

        allItems.add(new Item("item","",100,0));
        boolean check3 = SILab2.checkCart(allItems,10);
        assertFalse(check3);
    }


}
