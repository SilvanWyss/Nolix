package ch.nolix.coretest.errorcontrol.validator;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.IterableMediator;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ContainerMediatorTest extends StandardTest {
  @Test
  void testCase_contains_whenTheGivenConditionIsNull() {
    //setup
    final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
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
    final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
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
    final var list = ImmutableList.withElement("ax", "ax", "bx", "bx", "cx", "cx", "dx", "dx");
    final var testUnit = new IterableMediator<>(list);

    //execution & verification
    expectRunning(() -> testUnit.contains(e -> e.startsWith("c"))).doesNotThrowException();
  }
}
