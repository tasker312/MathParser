import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathParserTest {

    MathParser calc = new MathParser();

/*    @Test
    void testString1() {
        String input = "3+11";
        String actual = calc.transformString(input);
        assertEquals("3 + 11", actual);
    }

    @Test
    void testString2() {
        String input = "3*11";
        String actual = calc.transformString(input);
        assertEquals("3 * 11", actual);
    }

    @Test
    void testString3() {
        String input = "3-11";
        String actual = calc.transformString(input);
        assertEquals("3 - 11", actual);
    }
    @Test

    void testString4() {
        String input = "cos(3)";
        String actual = calc.transformString(input);
        assertEquals("cos ( 3 )", actual);
    }*/

    @Test
    void testPlus() {
        String expr = "3 + 4";
        double actual = calc.calculate(expr);
        double expected = 3 + 4;
        assertEquals(expected, actual);
    }

    @Test
    void testTwoSign() {
        String expr = "12 * 3 + 4";
        double actual = calc.calculate(expr);
        double expected = 12 * 3 + 4;
        assertEquals(expected, actual);
    }

    @Test
    void testTwoSignWithOrder() {
        String expr = "12 + 3 * 4";
        double actual = calc.calculate(expr);
        double expected = 12 + 3 * 4;
        assertEquals(expected, actual);
    }

    @Test
    void testManySignWithOrder() {
        String expr = "1 + 2 * 3 - 4";
        double actual = calc.calculate(expr);
        double expected = 1 + 2 * 3 - 4;
        assertEquals(expected, actual);
    }

    @Test
    void testManyMinus() {
        String expr = "3 - 4 - 8 - 6 * 2 + 1 - 3 - 4";
        double actual = calc.calculate(expr);
        double expected = 3 - 4 - 8 - 6 * 2 + 1 - 3 - 4;
        assertEquals(expected, actual);
    }

    @Test
    void testBrackets() {
        String expr = "( 1 + 2 ) * 3 - 4";
        double actual = calc.calculate(expr);
        double expected = (1 + 2) * 3 - 4;
        assertEquals(expected, actual);
    }

    @Test
    void testManyBrackets1() {
        String expr = "( 6 + 4 * ( 2 - 1 ) ) / 5";
        double actual = calc.calculate(expr);
        double expected = (6 + 4 * (2 - 1)) / 5.0;
        assertEquals(expected, actual);
    }

    @Test
    void testManyBrackets2() {
        String expr = "( 2 - 3 ) * ( 12 - 10 ) + 4 / 2";
        double actual = calc.calculate(expr);
        double expected = (2 - 3) * (12 - 10) + 4 / 2.0;
        assertEquals(expected, actual);
    }

    @Test
    void testPower() {
        String expr = "2 + ( 3 - 5 ) ^ 2 * 3";
        double actual = calc.calculate(expr);
        double expected = 2 + Math.pow(3 - 5, 2) * 3;
        assertEquals(expected, actual);
    }

    @Test
    void testSin() {
        String expr = "sin ( 4 )";
        double actual = calc.calculate(expr);
        double expected = Math.sin(4);
        assertEquals(expected, actual);
    }

    @Test
    void testSinWithArguments() {
        String expr = "sin ( 4 + 8 * 2 )";
        double actual = calc.calculate(expr);
        double expected = Math.sin(4 + 8 * 2);
        assertEquals(expected, actual);
    }

    @Test
    void testSinBrackets() {
        String expr = "sin((3+2)*4)";
        double actual = calc.calculate(expr);
        double expected = Math.sin((3 + 2) * 4);
        assertEquals(expected, actual);
    }

    @Test
    void testSinCos() {
        String expr = "sin(cos(4))";
        double actual = calc.calculate(expr);
        double expected = Math.sin(Math.cos(4));
        assertEquals(expected, actual);
    }

    @Test
    void testSinManySign() {
        String expr = "sin(1+5*4)";
        double actual = calc.calculate(expr);
        double expected = Math.sin(1 + 5 * 4);
        assertEquals(expected, actual);
    }

    @Test
    void testConstants() {
        String expr = "sin(cos(pi*e))";
        double actual = calc.calculate(expr);
        double expected = Math.sin(Math.cos(Math.PI * Math.E));
        assertEquals(expected, actual);
    }

    @Test
    void testFirstMinusSign() {
        String expr = "-4*6";
        double actual = calc.calculate(expr);
        double expected = -4 * 6;
        assertEquals(expected, actual);
    }

    @Test
    void testFirstMinusSignManySing() {
        String expr = "-4*6+2";
        double actual = calc.calculate(expr);
        double expected = -4 * 6 + 2;
        assertEquals(expected, actual);
    }

    @Test
    void testFirstMinusSignInBrackets() {
        String expr = "(-5 - 9 + 23)";
        double actual = calc.calculate(expr);
        double expected = (-5 - 9 + 23);
        assertEquals(expected, actual);
    }

    @Test
    void testFirstMinusSignInManyBrackets() {
        String expr = "(-4+6)*(-1*7)";
        double actual = calc.calculate(expr);
        double expected = (-4 + 6) * (-1 * 7);
        assertEquals(expected, actual);
    }

    @Test
    void testPositiveAbs() {
        String expr = "abs(10-4)";
        double actual = calc.calculate(expr);
        double expected = Math.abs(10 - 4);
        assertEquals(expected, actual);
    }

    @Test
    void testNegativeAbs() {
        String expr = "abs(5-10)";
        double actual = calc.calculate(expr);
        double expected = Math.abs(5-10);
        assertEquals(expected, actual);
    }

    @Test
    void testAbsWithBrackets() {
        String expr = "abs(-1*(-4))+abs(-4*4)";
        double actual = calc.calculate(expr);
        double expected = Math.abs(-1 * -4) + Math.abs(-4 * 4);
        assertEquals(expected, actual);
    }

    @Test
    void something1() {
        String expr = "(((2-3)+(3+2))-(6-1))";
        double actual = calc.calculate(expr);
        double expected = (((2 - 3) + (3 + 2)) - (6 - 1));
        assertEquals(expected, actual);
    }

    @Test
    void something2() {
        String expr = "1-(2+3*4)/5";
        double actual = calc.calculate(expr);
        double expected = 1 - (2 + 3 * 4) / 5.0;
        assertEquals(expected, actual);
    }

    @Test
    void something3() {
        String expr = "5 * (-3 + 8)";
        double actual = calc.calculate(expr);
        double expected = 5 * (-3 + 8);
        assertEquals(expected, actual);
    }
}