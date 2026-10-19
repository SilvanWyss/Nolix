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
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrDeleted(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsNew() {
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
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsLoaded() {
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
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsEdited() {
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
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsDeleted() {
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
  void testCase_isNewOrDeleted_whenGivenDatabaseObjectIsClosed() {
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
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrEdited(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsNew() {
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
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsLoaded() {
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
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsEdited() {
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
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsDeleted() {
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
  void testCase_isNewOrEdited_whenGivenDatabaseObjectIsClosed() {
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
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoaded(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsNew() {
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
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsLoaded() {
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
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsEdited() {
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
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsDeleted() {
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
  void testCase_isNewOrLoaded_whenGivenDatabaseObjectIsClosed() {
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
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsNull() {
    // setup
    final var testUnit = new DatabaseObjectExaminer();

    // execute
    final var result = testUnit.isNewOrLoadedOrEdited(null);

    // verify
    expect(result).isFalse();
  }

  @Test
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsNew() {
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
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsLoaded() {
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
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsEdited() {
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
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsDeleted() {
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
  void testCase_isNewOrLoadedOrEdited_whenGivenDatabaseObjectIsClosed() {
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
