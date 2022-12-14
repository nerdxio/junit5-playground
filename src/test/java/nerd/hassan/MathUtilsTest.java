package nerd.hassan;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {
    MathUtils mathUtils;

    @BeforeAll
    void beforeALlInit() {
        System.out.println("this needs to run");
    }

    @BeforeEach
    void init() {
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanUp() {
        System.out.println("Cleaning up ...");
    }

    @Test
    @DisplayName("Testing add methode")
    void testAdd() {
        int expected = 2;
        int actual = mathUtils.add(1, 1);
        assertEquals(expected, actual, "The add method ");
    }

    @Test
    @Tag("Math")
    void testDivide() {
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "if /0 throw ex");
    }

    @Test
    void testComputeCircleArea() {
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "should return the area");
    }

    @Test
    @Disabled
    @Tag("TDD")
    @DisplayName("TDD test disabled")
    void testDrivenDevelopment() {
        fail("it is going to fail");
    }

    @Test
    @Tag("OS")
    @EnabledOnOs(OS.LINUX)
    @DisplayName("Run only on LINUX")
    void conditionalExecutionsTest() {
        System.out.println("Conditional Executions Test Run only on LINUX");
    }

    @Test
    @DisplayName("Assume if test is true it is going to work other wise not work")
    void assumeThatTest() {
        boolean isServerUp = true;
        assumeTrue(isServerUp);
        //if  assumeTrue(isServerUp) is true assertThrows() is going to work
        assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0));
    }

    @Test
    @DisplayName("Test more than one multiply method with assertAll()")
    void testAllMultiplyCases() {
        // if one fail all are going to fail
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2, 2)),
                () -> assertEquals(0, mathUtils.multiply(2, 0)),
                () -> assertEquals(-2, mathUtils.multiply(2, -1))
        );
    }

    // away to group related tests
    @Nested
    @Tag("Math")
    class AddTest {
        @Test
        @DisplayName("Testing add methode for positive number")
        void testAddPositiveNumber() {
            assertEquals(2, mathUtils.add(1, 1), "This methode should add to positive num");
        }

        @Test
        @DisplayName("Testing add methode for positive number")
        void testAddNegativeNumber() {
            assertEquals(-2, mathUtils.add(-1, -1), "This methode should add to negative num");
        }
    }

    @RepeatedTest(5)
    @DisplayName("Testing add methode for positive number and repeated 5 time")
    void testAddPositive() {
        assertEquals(2, mathUtils.add(1, 1), "This methode should add to positive num");
    }
}
