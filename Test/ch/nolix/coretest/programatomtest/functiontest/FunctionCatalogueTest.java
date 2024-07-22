//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class FunctionCatalogueTest extends StandardTest {

  //method
  @Test
  void testCase_getFalse() {

    //execution
    final var result = GlobalFunctionService.getFalse();

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_getNull() {

    //execution
    final var result = GlobalFunctionService.getNull();

    //verification
    expect(result).isNull();
  }

  //method
  @Test
  void testCase_getOne() {

    //execution
    final var result = GlobalFunctionService.getOne();

    //verification
    expect(result).isEqualTo(1);
  }

  //method
  @Test
  void testCase_getStringRepresentationOf_whenNullIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf(null);

    //verification
    expect(result).isEqualTo("null");
  }

  //method
  @Test
  void testCase_getStringRepresentationOf_whenAnIntIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf(2500);

    //verification
    expect(result).isEqualTo("2500");
  }

  //method
  @Test
  void testCase_getStringRepresentationOf_whenAStringIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf("Lorem ipsum");

    //verification
    expect(result).isEqualTo("Lorem ipsum");
  }

  //method
  @Test
  void testCase_getTrue() {

    //execution
    final var result = GlobalFunctionService.getTrue();

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_getTypeOf_whenIntIsGiven() {

    //execution
    final var result = GlobalFunctionService.getTypeOf(2500);

    //verification
    expect(result).is(Integer.class);
  }

  //method
  @Test
  void testCase_getTypeOf_whenStringIsGiven() {

    //execution
    final var result = GlobalFunctionService.getTypeOf("");

    //verification
    expect(result).is(String.class);
  }

  //method
  @Test
  void testCase_getZero() {

    //execution
    final var result = GlobalFunctionService.getZero();

    //verification
    expect(result).isEqualTo(0);
  }
}
