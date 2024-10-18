package ch.nolix.coretest.programatomtest.functiontest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.programatom.function.GlobalFunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;

final class FunctionCatalogueTest extends StandardTest {

  @Test
  void testCase_getFalse() {

    //execution
    final var result = GlobalFunctionService.getFalse();

    //verification
    expectNot(result);
  }

  @Test
  void testCase_getNull() {

    //execution
    final var result = GlobalFunctionService.getNull();

    //verification
    expect(result).isNull();
  }

  @Test
  void testCase_getOne() {

    //execution
    final var result = GlobalFunctionService.getOne();

    //verification
    expect(result).isEqualTo(1);
  }

  @Test
  void testCase_getStringRepresentationOf_whenNullIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf(null);

    //verification
    expect(result).isEqualTo("null");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAnIntIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf(2500);

    //verification
    expect(result).isEqualTo("2500");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAStringIsGiven() {

    //execution
    final var result = GlobalFunctionService.getStringRepresentationOf("Lorem ipsum");

    //verification
    expect(result).isEqualTo("Lorem ipsum");
  }

  @Test
  void testCase_getTrue() {

    //execution
    final var result = GlobalFunctionService.getTrue();

    //verification
    expect(result);
  }

  @Test
  void testCase_getTypeOf_whenIntIsGiven() {

    //execution
    final var result = GlobalFunctionService.getTypeOf(2500);

    //verification
    expect(result).is(Integer.class);
  }

  @Test
  void testCase_getTypeOf_whenStringIsGiven() {

    //execution
    final var result = GlobalFunctionService.getTypeOf("");

    //verification
    expect(result).is(String.class);
  }

  @Test
  void testCase_getZero() {

    //execution
    final var result = GlobalFunctionService.getZero();

    //verification
    expect(result).isEqualTo(0);
  }
}
