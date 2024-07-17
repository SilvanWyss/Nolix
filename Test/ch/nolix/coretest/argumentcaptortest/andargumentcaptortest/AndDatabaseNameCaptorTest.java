//package declaration
package ch.nolix.coretest.argumentcaptortest.andargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndDatabaseNameCaptor;
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class AndDatabaseNameCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_andDatabase_whenHasNext() {

    //parameter definition
    final var databaseName = "my_database";

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new AndDatabaseNameCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.andDatabase(databaseName);

    //verification
    expect(testUnit.getDatabaseName()).isEqualTo(databaseName);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_andDatabase_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new AndDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.andDatabase("my_database")).throwsException().ofType(InvalidArgumentException.class);
  }

  //method
  @Test
  void testCase_getDatabaseName_whenDoesNotHaveDatabaseName() {

    //setup
    final var testUnit = new AndDatabaseNameCaptor<>();

    //execution & verification
    expectRunning(testUnit::getDatabaseName).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
