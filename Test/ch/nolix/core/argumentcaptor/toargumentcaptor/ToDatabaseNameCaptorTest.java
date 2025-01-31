package ch.nolix.core.argumentcaptor.toargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ToDatabaseNameCaptorTest extends StandardTest {

  @Test
  void testCase_getDatabaseName_whenDoesNotHaveDatabaseName() {

    //setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getDatabaseName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_toDatabase_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.toDatabase("my_database")).throwsException().ofType(InvalidArgumentException.class);
  }

  @Test
  void testCase_toDatabaseName_whenHasNext() {

    //parameter definition
    final var databaseName = "my_database";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ToDatabaseNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.toDatabase(databaseName);

    //verification
    expect(testUnit.getDatabaseName()).isEqualTo(databaseName);
    expect(result).is(andNameCaptor);
  }
}
