package ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest;

import org.junit.jupiter.api.Test;

import ch.nolix.core.testing.standardtest.StandardTest;

final class ChangeRequestableTest extends StandardTest {

  @Test
  void testCase_isChangeFree_whenIsChangeFree() {

    //setup
    final var testUnit = ChangeRequestableMock.withHasChangesFlag(false);

    //execution
    final var result = testUnit.isChangeFree();

    //verification
    expect(result);
  }

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
