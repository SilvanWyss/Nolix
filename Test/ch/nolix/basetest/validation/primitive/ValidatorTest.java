/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.primitive;

import org.junit.jupiter.api.Test;

import ch.nolix.base.foundation.util.FunctionService;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.AbstractMethodMediator;
import ch.nolix.base.validation.object.AbstractStringMediator;
import ch.nolix.base.validation.object.NamableIterableMediator;
import ch.nolix.base.validation.primitive.AbstractLongMediator;
import ch.nolix.base.validation.primitive.NamableByteMediator;
import ch.nolix.base.validation.primitive.NamableDoubleMediator;
import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 */
final class ValidatorTest extends StandardTest {
  @Test
  void testCase_assertThat_whenGivenArgumentIsAByte() {
    // setup
    final var argument = (byte) 127;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableByteMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsAByteArray() {
    // setup
    final var argument = new byte[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsADouble() {
    // setup
    final var argument = 1.0;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableDoubleMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsADoubleArray() {
    // setup
    final var argument = new double[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsAnInt() {
    // setup
    final var argument = 50;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractLongMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsAnIntArray() {
    // setup
    final var argument = new int[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsALong() {
    // setup
    final var argument = 50L;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractLongMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsALongArray() {
    // setup
    final var argument = new long[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsAMethod() throws NoSuchMethodException {
    // setup
    final var argument = FunctionService.class.getMethod("doNothing");

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractMethodMediator.class);
  }

  @Test
  void testCase_assertThat_whenGivenArgumentIsAString() {
    // setup
    final var argument = "lorem ipsum";

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractStringMediator.class);
  }
}
