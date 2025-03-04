package ch.nolix.system.objectdata.model;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.field.OptionalValue;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

final class OptionalValueTest extends StandardTest {

  @Test
  void testCase_clear() {

    //setup
    final var testUnit = OptionalValue.withInitialValue("LoremIpsum");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_constructor() {

    //execution
    final var result = OptionalValue.withValueType(String.class);

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
    final var testUnit = OptionalValue.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
