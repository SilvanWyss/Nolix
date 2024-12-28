package ch.nolix.systemtest.objectdatatest.entityflyweighttest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.system.objectdata.modelflyweight.VoidEntityFlyWeight;

final class VoidEntityFlyWeightTest extends StandardTest {

  @Test
  void testCase_creation() {

    //execution
    final var testUnit = new VoidEntityFlyWeight();

    //verification
    expect(testUnit.isVoid()).isTrue();
  }
}
