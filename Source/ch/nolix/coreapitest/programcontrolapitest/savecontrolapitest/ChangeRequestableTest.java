//package declaration
package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ChangeRequestableTest extends Test {

  // method
  @TestCase
  public void testCase_isChangeFree_whenIsChangeFree() {

    // setup
    final var testUnit = ChangeRequestableStub.withHasChangesFlag(false);

    // execution
    final var result = testUnit.isChangeFree();

    // verification
    expect(result);
  }

  // method
  @TestCase
  public void testCase_isChangeFree_whenHasChanges() {

    // setup
    final var testUnit = ChangeRequestableStub.withHasChangesFlag(true);

    // execution
    final var result = testUnit.isChangeFree();

    // verification
    expectNot(result);
  }
}
