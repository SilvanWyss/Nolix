/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemtest.database.databaseobjectexaminer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ch.nolix.base.testing.standardtest.StandardTest;
import ch.nolix.system.database.databaseobjectexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.database.databaseobject.DatabaseObject;
import ch.nolix.systemapi.database.databaseobject.DatabaseObjectState;

/**
 * @author Silvan Wyss
 */
final class DatabaseObjectExaminerTest extends StandardTest {
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsNew() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsLoaded() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.UNEDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsEdited() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsDeleted() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsClosed() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsNew() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsLoaded() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.UNEDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsEdited() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsDeleted() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsClosed() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsNew() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsLoaded() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.UNEDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsEdited() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsDeleted() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsClosed() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsNew() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsLoaded() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.UNEDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsEdited() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsDeleted() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsClosed() {
    // setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(DatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    // setup testUnit
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    // verify
    expect(result).isFalse();
  }
}
