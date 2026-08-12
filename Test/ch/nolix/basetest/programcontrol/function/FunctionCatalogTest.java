/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.programcontrol.function;

import org.junit.jupiter.api.Test;

import ch.nolix.base.foundation.util.FunctionService;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class FunctionCatalogTest extends StandardTest {
  @Test
  void testCase_getFalse() {
    // execute
    final var result = FunctionService.getFalse();

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_getNull() {
    // execute
    final var result = FunctionService.getNull();

    // verify
    expect(result).isNull();
  }

  @Test
  void testCase_getOne() {
    // execute
    final var result = FunctionService.getOne();

    // verify
    expect(result).isEqualTo(1);
  }

  @Test
  void testCase_getStringRepresentationOf_whenNullIsGiven() {
    // execute
    final var result = FunctionService.getStringRepresentationOf(null);

    // verify
    expect(result).isEqualTo("null");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAnIntIsGiven() {
    // execute
    final var result = FunctionService.getStringRepresentationOf(2500);

    // verify
    expect(result).isEqualTo("2500");
  }

  @Test
  void testCase_getStringRepresentationOf_whenAStringIsGiven() {
    // execute
    final var result = FunctionService.getStringRepresentationOf("Lorem ipsum");

    // verify
    expect(result).isEqualTo("Lorem ipsum");
  }

  @Test
  void testCase_getTrue() {
    // execute
    final var result = FunctionService.getTrue();

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_getTypeOf_whenIntIsGiven() {
    // execute
    final var result = FunctionService.getTypeOf(2500);

    // verify
    expect(result).is(Integer.class);
  }

  @Test
  void testCase_getTypeOf_whenStringIsGiven() {
    // execute
    final var result = FunctionService.getTypeOf("");

    // verify
    expect(result).is(String.class);
  }

  @Test
  void testCase_getZero() {
    // execute
    final var result = FunctionService.getZero();

    // verify
    expect(result).isEqualTo(0);
  }
}
