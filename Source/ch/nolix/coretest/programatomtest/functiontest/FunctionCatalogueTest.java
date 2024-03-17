//package declaration
package ch.nolix.coretest.programatomtest.functiontest;

//own imports
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class FunctionCatalogueTest extends StandardTest {

  //method
  @TestCase
  public void testCase_getFalse() {

    //execution
    final var result = FunctionCatalogue.getFalse();

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_getNull() {

    //execution
    final var result = FunctionCatalogue.getNull();

    //verification
    expect(result).isNull();
  }

  //method
  @TestCase
  public void testCase_getOne() {

    //execution
    final var result = FunctionCatalogue.getOne();

    //verification
    expect(result).isEqualTo(1);
  }

  //method
  @TestCase
  public void testCase_getStringRepresentationOf_whenNullIsGiven() {

    //execution
    final var result = FunctionCatalogue.getStringRepresentationOf(null);

    //verification
    expect(result).isEqualTo("null");
  }

  //method
  @TestCase
  public void testCase_getStringRepresentationOf_whenAnIntIsGiven() {

    //execution
    final var result = FunctionCatalogue.getStringRepresentationOf(2500);

    //verification
    expect(result).isEqualTo("2500");
  }

  //method
  @TestCase
  public void testCase_getStringRepresentationOf_whenAStringIsGiven() {

    //execution
    final var result = FunctionCatalogue.getStringRepresentationOf("Lorem ipsum");

    //verification
    expect(result).isEqualTo("Lorem ipsum");
  }

  //method
  @TestCase
  public void testCase_getTrue() {

    //execution
    final var result = FunctionCatalogue.getTrue();

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_getTypeOf_whenIntIsGiven() {

    //execution
    final var result = FunctionCatalogue.getTypeOf(2500);

    //verification
    expect(result).is(Integer.class);
  }

  //method
  @TestCase
  public void testCase_getTypeOf_whenStringIsGiven() {

    //execution
    final var result = FunctionCatalogue.getTypeOf("");

    //verification
    expect(result).is(String.class);
  }

  //method
  @TestCase
  public void testCase_getZero() {

    //execution
    final var result = FunctionCatalogue.getZero();

    //verification
    expect(result).isEqualTo(0);
  }
}
