//package declaration
package ch.nolix.coretest.argumentcaptortest.withargumentcaptortest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithDatabaseCaptor;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
final class WithDatabaseCaptorTest extends StandardTest {

  //method
  @Test
  void testCase_getStoredDatabase_whenDoesNotHaveDatabase() {

    //setup
    final var testUnit = new WithDatabaseCaptor<>();

    //execution & verification
    expectRunning(testUnit::getStoredDatabase).throwsException().ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  //method
  @Test
  void testCase_withDatabase_whenHasNext() {

    //parameter definition
    final var database = new MutableNode();

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new WithDatabaseCaptor<IMutableNode<?>, AndNameCaptor<?>>(andNameCaptor);

    //execution
    final var result = testUnit.withDatabase(database);

    //verification
    expect(testUnit.getStoredDatabase()).is(database);
    expect(result).is(andNameCaptor);
  }

  //method
  @Test
  void testCase_withDatabase_whenDoesNotHaveNext() {

    //setup
    final var testUnit = new WithDatabaseCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.withDatabase(new MutableNode()))
      .throwsException()
      .ofType(InvalidArgumentException.class);
  }
}
