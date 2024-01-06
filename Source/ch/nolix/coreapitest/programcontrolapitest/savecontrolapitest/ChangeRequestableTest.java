//package declaration
package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.testingapi.testapi.TestCase;

//class
public final class ChangeRequestableTest extends Test {

  //method
  @TestCase
  public void testCase_isChangeFree_whenIsChangeFree() {

    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(false);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_isChangeFree_whenHasChanges() {

    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(true);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expectNot(result);
  }
}
