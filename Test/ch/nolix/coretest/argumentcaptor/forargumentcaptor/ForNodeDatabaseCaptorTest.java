package ch.nolix.coretest.argumentcaptor.forargumentcaptor;

import org.junit.jupiter.api.Test;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndNameCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.core.document.node.MutableNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.testing.standardtest.StandardTest;
import ch.nolix.coreapi.document.node.IMutableNode;

final class ForNodeDatabaseCaptorTest extends StandardTest {

  @Test
  void testCase_forNodeDatabase_whenDoesNotHaveNext() {

    //setup
    final var database = MutableNode.createEmpty();
    final var testUnit = new ForNodeDatabaseCaptor<>();

    //execution & verification
    expectRunning(() -> testUnit.forNodeDatabase(database))
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }

  @Test
  void testCase_forNodeDatabase_whenHasNext() {

    //setup
    final var database = MutableNode.createEmpty();
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForNodeDatabaseCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forNodeDatabase(database);

    //verification
    expect(testUnit.getStoredNodeDatabase()).isEqualTo(database);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_forTemporaryInMemoryNodeDatabase_whenHasNext() {

    //setup
    final var andNameCaptor = new AndNameCaptor<>();
    final var testUnit = new ForNodeDatabaseCaptor<>(andNameCaptor);

    //execution
    final var result = testUnit.forTemporaryInMemoryNodeDatabase();

    //verification
    expect(testUnit.getStoredNodeDatabase()).isOfType(IMutableNode.class);
    expect(result).is(andNameCaptor);
  }

  @Test
  void testCase_getStoredNodeDatabase_whenDoesNotHaveNodeDatabase() {

    //setup
    final var testUnit = new ForNodeDatabaseCaptor<>();

    //execution & verification
    expectRunning(testUnit::getStoredNodeDatabase)
      .throwsException()
      .ofType(ArgumentDoesNotHaveAttributeException.class);
  }
}
