//package declaration
package ch.nolix.coretest.containertest.singlecontainertest;

import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class SingleContainerTest extends Test {

  // method
  @TestCase
  public void testCase_constructor() {

    // execution
    final var result = new SingleContainer<String>();

    // verification
    expect(result.isEmpty());
  }

  // method
  @TestCase
  public void testCase_constructor_whenThereIsGiven1Element() {

    // setup
    final var garfield = "Garfield";

    // execution
    final var result = new SingleContainer<>(garfield);

    // verification
    expect(result.containsAny());
    expect(result.getStoredElement()).is(garfield);
  }
}
