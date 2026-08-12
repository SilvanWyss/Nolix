/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.validation.primitive;

import org.junit.jupiter.api.Test;

import ch.nolix.base.foundation.util.FunctionService;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.base.validation.object.MethodMediator;
import ch.nolix.base.validation.object.NamableIterableMediator;
import ch.nolix.base.validation.object.StringMediator;
import ch.nolix.base.validation.primitive.AbstractLongMediator;
import ch.nolix.base.validation.primitive.NamableByteMediator;
import ch.nolix.base.validation.primitive.NamableDoubleMediator;
import ch.nolix.base.validation.validator.Validator;

/**
 * @author Silvan Wyss
 */
final class ValidatorTest extends StandardTest {
  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAByte() {
    // setup
    final var argument = (byte) 127;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableByteMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAByteArray() {
    // setup
    final var argument = new byte[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsADouble() {
    // setup
    final var argument = 1.0;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableDoubleMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsADoubleArray() {
    // setup
    final var argument = new double[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAnInt() {
    // setup
    final var argument = 50;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractLongMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAnIntArray() {
    // setup
    final var argument = new int[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsALong() {
    // setup
    final var argument = 50L;

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(AbstractLongMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsALongArray() {
    // setup
    final var argument = new long[100];

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(NamableIterableMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAMethod() throws NoSuchMethodException {
    // setup
    final var argument = FunctionService.class.getMethod("doNothing");

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(MethodMediator.class);
  }

  @Test
  void testCase_assertThat_whenTheGivenArgumentIsAString() {
    // setup
    final var argument = "lorem ipsum";

    // execute
    final var result = Validator.assertThat(argument);

    // verify
    expect(result).isOfType(StringMediator.class);
  }
}
