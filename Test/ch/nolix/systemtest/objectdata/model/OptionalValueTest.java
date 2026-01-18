/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.OptionalValueField;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class OptionalValueTest extends StandardTest {
  @Test
  void testCase_clear() {
    //setup
    final var testUnit = OptionalValueField.withInitialValue("LoremIpsum");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_constructor() {
    //execution
    final var result = OptionalValueField.withValueType(String.class);

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isOpen()).isTrue();
    expect(result.isDeleted()).isFalse();
    expect(result.isConnectedWithRealDatabase()).isFalse();
    expect(result.isOptional()).isTrue();
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_setValue() {
    //setup
    final var testUnit = OptionalValueField.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
