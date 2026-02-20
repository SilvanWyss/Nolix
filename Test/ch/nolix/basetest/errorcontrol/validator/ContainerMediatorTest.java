/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.errorcontrol.validator;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.base.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.base.errorcontrol.validator.IterableMediator;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class ContainerMediatorTest extends StandardTest {
  @Test
  void testCase_contains_whenTheGivenConditionIsNull() {
    //setup
    final var list = ImmutableList.withElements("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
    final Predicate<String> condition = null;
    final var testUnit = new IterableMediator<>(list);

    //execution & verification
    expectRunning(() -> testUnit.contains(condition))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given condition is null.");
  }

  @Test
  void testCase_contains_whenTheGivenArgumentDoesNotContainAWantedElement() {
    //setup
    final var list = ImmutableList.withElements("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
    final var testUnit = new IterableMediator<>(list);

    //execution & verification
    expectRunning(() -> testUnit.contains(e -> e.startsWith("e")))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given ImmutableList 'ax,ax,bx,bx,cx,cx,dx,dx' does not contain "
        + "an element that fulfils the given condition.");
  }

  @Test
  void testCase_contains_whenTheGivenArgumentContainsAWantedElement() {
    //setup
    final var list = ImmutableList.withElements("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
    final var testUnit = new IterableMediator<>(list);

    //execution & verification
    expectRunning(() -> testUnit.contains(e -> e.startsWith("c"))).doesNotThrowException();
  }
}
