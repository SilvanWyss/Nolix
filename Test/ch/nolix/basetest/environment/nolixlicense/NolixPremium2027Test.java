/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.environment.nolixlicense;

import org.junit.jupiter.api.Test;

import ch.nolix.base.environment.nolixlicense.NolixPremium2027;
import ch.nolix.base.testing.standardtest.StandardTest;

/**
 * @author Silvan Wyss
 */
final class NolixPremium2027Test extends StandardTest {
  @Test
  void testCase_activateWithKey_whenGivenKeyIsWrong() {
    // setup
    final var testUnit = new NolixPremium2027();

    // verify setup
    expect(testUnit.isActivated()).isFalse();

    // execute & verify
    expectRunning(() -> testUnit.activateWithKey("123456")).throwsException();
    expect(testUnit.isActivated()).isFalse();
  }
}
