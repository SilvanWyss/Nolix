package ch.nolix.coreapitest.datamodelapitest.entityrequestapitest;

//own imports
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class AbstractnessRequestableTest extends Test {

  //method
  @TestCase
  public void testCase_isConcrete_whenIsAbstract() {

    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(true);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_isConcrete_whenIsConcrete() {

    //setup
    final var testUnit = AbstractnessRequestableMock.withIsAbstractFlag(false);

    //execution
    final var result = testUnit.isConcrete();

    //verification
    expect(result);
  }
}
