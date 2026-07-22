/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.property.extension;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.property.extension.ExtensionProperty;

/**
 * @author Silvan Wyss
 */
final class ExtensionPropertyTest extends StandardTest {
  @Test
  void testCase_isMaterialized() {
    // setup
    final var testUnit = ExtensionProperty.withExtension(mock());

   // execute
    final var result = testUnit.isMaterialized();

   // verify
    expect(result).isTrue();
  }
}
