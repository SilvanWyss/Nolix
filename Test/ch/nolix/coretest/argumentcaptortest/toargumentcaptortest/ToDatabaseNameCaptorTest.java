//package declaration
package ch.nolix.coretest.argumentcaptortest.toargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.toargumentcaptor.ToDatabaseNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ToDatabaseNameCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_getDatabaseName_whenDoesNotHaveADatabaseName() {

    //setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getDatabaseName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_toDatabase_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new ToDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.toDatabase("my_database")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
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
