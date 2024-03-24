//package declaration
package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;

//class
final class ChangeRequestableTest extends StandardTest {

  //method
  @Test
  void testCase_isChangeFree_whenIsChangeFree() {

    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(false);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isChangeFree_whenHasChanges() {

    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(true);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expectNot(result);
  }
}
