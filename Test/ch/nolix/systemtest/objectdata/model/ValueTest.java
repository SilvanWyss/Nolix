/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.model.ValueField;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class ValueTest extends StandardTest {
  @Test
  void testCase_constructor() {
    //execution
    final var result = ValueField.withValueType(String.class);

    //verification
    expect(result.getState()).isEqualTo(DatabaseObjectState.NEW);
    expect(result.isOpen()).isTrue();
    expect(result.isDeleted()).isFalse();
    expect(result.isConnectedWithRealDatabase()).isFalse();
    expect(result.isMandatory());
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_setValue() {
    //setup
    final var testUnit = ValueField.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
