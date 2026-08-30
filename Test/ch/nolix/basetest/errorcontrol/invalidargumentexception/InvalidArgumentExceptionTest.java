/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.errorcontrol.invalidargumentexception;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * @author Silvan Wyss
 */
final class InvalidArgumentExceptionTest extends StandardTest {
  @Test
  void testCase_forArgument_whenArgumentIsNull() {
    // execute
    final var result = InvalidArgumentException.forArgument(null);

    // verify
    expect(result.getArgumentName()).isEqualTo("argument");
    expect(result.getStoredArgument()).isNull();
    expect(result.getErrorPredicate()).isEqualTo("is not valid");
    expect(result.getMessage()).isEqualTo("The given argument is not valid.");
  }

  @Test
  void testCase_forArgument_whenArgumentIsANode() {
    // setup
    final var node = ImmutableNode.fromString("Parking(Slot(Id(A)),Slot(Id(B)))");

    // execute
    final var result = InvalidArgumentException.forArgument(node);

    // verify
    expect(result.getArgumentName()).isEqualTo("ImmutableNode");
    expect(result.getStoredArgument()).is(node);
    expect(result.getErrorPredicate()).isEqualTo("is not valid");
    expect(result.getMessage()).isEqualTo("The given ImmutableNode 'Parking(Slot(Id(A)),Slot(Id(B)))' is not valid.");
  }

  @Test
  void testCase_forArgumentAndErrorPredicate() {
    // setup
    final var amount = BigDecimal.valueOf(10.5);

    // execute
    final var result = InvalidArgumentException.forArgumentAndErrorPredicate(amount, "is not a whole number");

    // verify
    expect(result.getArgumentName()).isEqualTo("BigDecimal");
    expect(result.getStoredArgument()).is(amount);
    expect(result.getErrorPredicate()).isEqualTo("is not a whole number");
    expect(result.getMessage()).isEqualTo("The given BigDecimal '10.5' is not a whole number.");
  }

  @Test
  void testCase_forArgumentNameAndArgumentAndErrorPredicate() {
    // setup
    final var amount = BigDecimal.valueOf(10.5);

    // execute
    final var result = InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
      amount,
      "amount",
      "is not a whole number");

    // verify
    expect(result.getArgumentName()).isEqualTo("amount");
    expect(result.getStoredArgument()).is(amount);
    expect(result.getErrorPredicate()).isEqualTo("is not a whole number");
    expect(result.getMessage()).isEqualTo("The given amount '10.5' is not a whole number.");
  }
}
