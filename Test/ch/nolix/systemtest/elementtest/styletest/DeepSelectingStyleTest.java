package ch.nolix.systemtest.elementtest.styletest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.element.style.DeepSelectingStyle;

final class DeepSelectingStyleTest extends StandardTest {

  @Test
  void testCase_selectsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.selectsChildElements();

    //verification
    expect(result);
  }

  @Test
  void testCase_skipsChildElements() {

    //setup
    final var testUnit = new DeepSelectingStyle();

    //execution
    final var result = testUnit.skipsChildElements();

    //verification
    expectNot(result);
  }
}
