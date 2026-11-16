public import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class FineCalculatorTest {

    @Test
    public void testNoFine() {
        // 1. Arrange
        FineCalculator calc = new FineCalculator();
        int days = 0;
        double expected = 0;
        
        // 2. Act
        double actual = calc.calculate(days);
        
        // 3. Assert
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testNormalFine() {
        // 1. Arrange
        FineCalculator calc = new FineCalculator();
        int days = 5;
        double expected = 25000;
        
        // 2. Act
        double actual = calc.calculate(days);
        
        // 3. Assert
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testHeavyFine() {
        // 1. Arrange
        FineCalculator calc = new FineCalculator();
        int days = 11;
        double expected = 66000; // (11 * 5000) * 1.2
        
        // 2. Act
        double actual = calc.calculate(days);
        
        // 3. Assert
        assertEquals(expected, actual, 0.01);
    }
} {
    
}
