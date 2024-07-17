//package declaration
package ch.nolix.coreapitest.mathapitest.functiontest;

//JUnit imports
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.mathapi.function.LongToDoubleFunctionCatalogue;

//class
final class LongToDoubleFunctionCatalogueTest extends StandardTest {

  //method
  @ParameterizedTest
  @CsvSource({
  "-300, 1.0", //
  "-200, 1.0", //
  "-100, 1.0", //
  "-30, 1.0", //
  "-20, 1.0", //
  "-10, 1.0", //
  "-3, 1.0", //
  "-2, 1.0", //
  "-1, 1.0", //
  "0, 1.0", //
  "1, 1.0", //
  "2, 1.0", //
  "3, 1.0", //
  "10, 1.0", //
  "20, 1.0", //
  "30, 1.0", //
  "100, 1.0", //
  "200, 1.0", //
  "300, 1.0", //
  })
  void testCase_constantFunction(final long input, final double expectedResult) {

    //execution
    final var result = LongToDoubleFunctionCatalogue.CONSTANT_FUNCTION.applyAsDouble(input);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "-300, -300.0", //
  "-200, -200.0", //
  "-100, -100.0", //
  "-30, -30.0", //
  "-20, -20.0", //
  "-10, -10.0", //
  "-3, -3.0", //
  "-2, -2.0", //
  "-1, -1.0", //
  "0, 0.0", //
  "1, 1.0", //
  "2, 2.0", //
  "3, 3.0", //
  "10, 10.0", //
  "20, 20.0", //
  "30, 30.0", //
  "100, 100.0", //
  "200, 200.0", //
  "300, 300.0", //
  })
  void testCase_linearFunction(final long input, final double expectedResult) {

    //execution
    final var result = LongToDoubleFunctionCatalogue.LINEAR_FUNCTION.applyAsDouble(input);

    //verification
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @ParameterizedTest
  @CsvSource({
  "-300, 90000.0", //
  "-200, 40000.0", //
  "-100, 10000.0", //
  "-30, 900.0", //
  "-20, 400.0", //
  "-10, 100.0", //
  "-3, 9.0", //
  "-2, 4.0", //
  "-1, 1.0", //
  "0, 0.0", //
  "1, 1.0", //
  "2, 4.0", //
  "3, 9.0", //
  "10, 100.0", //
  "20, 400.0", //
  "30, 900.0", //
  "100, 10000.0", //
  "200, 40000.0", //
  "300, 90000.0", //
  })
  void testCase_quadraticFunction(final long input, final double expectedResult) {

    //execution
    final var result = LongToDoubleFunctionCatalogue.QUADRATIC_FUNCTION.applyAsDouble(input);

    //verification
    expect(result).isEqualTo(expectedResult);
  }
}