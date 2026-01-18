/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coretest.misc.function;

import org.junit.jupiter.api.Test;

import ch.nolix.core.misc.function.FunctionService;
import ch.nolix.core.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class FunctionCatalogTest extends StandardTest {
  @Test
  void testCase_getFalse() {
    //execution
    final var result = FunctionService.getFalse();

    //verification
    expect(result).isFalse();
  }

  @Test
  void testCase_getNull() {
    //execution
    final var result = FunctionService.getNull();

    //verification
    expect(result).isNull();
  }

  @Test
  void testCase_getOne() {
    //execution
    final var result = FunctionService.getOne();

    //verification
    expect(result).isEqualTo(1);
  }

  @Test
  void testCase_getStringRepresentationOf_whenNullIsGiven() {
    //execution
    final var result = FunctionService.getStringRepresentationOf(null);

    //verification
    expect(result).isEqualTo("null");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAnIntIsGiven() {
    //execution
    final var result = FunctionService.getStringRepresentationOf(2500);

    //verification
    expect(result).isEqualTo("2500");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAStringIsGiven() {
    //execution
    final var result = FunctionService.getStringRepresentationOf("Lorem ipsum");

    //verification
    expect(result).isEqualTo("Lorem ipsum");
  }

  @Test
  void testCase_getTrue() {
    //execution
    final var result = FunctionService.getTrue();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_getTypeOf_whenIntIsGiven() {
    //execution
    final var result = FunctionService.getTypeOf(2500);

    //verification
    expect(result).is(Integer.class);
  }

  @Test
  void testCase_getTypeOf_whenStringIsGiven() {
    //execution
    final var result = FunctionService.getTypeOf("");

    //verification
    expect(result).is(String.class);
  }

  @Test
  void testCase_getZero() {
    //execution
    final var result = FunctionService.getZero();

    //verification
    expect(result).isEqualTo(0);
  }
}
