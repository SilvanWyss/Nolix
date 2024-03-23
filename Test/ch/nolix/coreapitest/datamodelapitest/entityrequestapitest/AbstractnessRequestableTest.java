package ch.nolix.coreapitest.datamodelapitest.entityrequestapitest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;

//class
final class AbstractnessRequestableTest extends StandardTest {

  //method
  @Test
  void testCase_isConcrete_whenIsAbstract() {

    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(true);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isConcrete_whenIsConcrete() {

    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(false);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expect(result);
  }
}
