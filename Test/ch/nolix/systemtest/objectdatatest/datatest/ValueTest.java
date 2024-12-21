package ch.nolix.systemtest.objectdatatest.datatest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

final class ValueTest extends StandardTest {

  @Test
  void testCase_constructor() {

    //execution
    final var result = Value.withValueType(String.class);

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
    final var testUnit = Value.withValueType(String.class);

    //execution
    testUnit.setValue("LoremIpsum");

    //verification
    expect(testUnit.containsAny()).isTrue();
    expect(testUnit.getStoredValue()).isEqualTo("LoremIpsum");
  }
}
