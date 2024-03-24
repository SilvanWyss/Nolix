//package declaration
package ch.nolix.systemtest.databaseobjecttest.databaseobjecttooltest;

//JUnit imports
import org.junit.jupiter.api.Test;
//Mockito imports
import org.mockito.Mockito;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.system.databaseobject.databaseobjecttool.DatabaseObjectTool;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;

//class
final class DatabaseObjectToolTest extends StandardTest {

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsNull() {

    //setup
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(null);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsNew() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsLoaded() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.LOADED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsEdited() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsDeleted() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrDeleted_whenTheGivenDatabaseObjectIsClosed() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrDeleted(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsNull() {

    //setup
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(null);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsNew() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsLoaded() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.LOADED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsEdited() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsDeleted() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrEdited_whenTheGivenDatabaseObjectIsClosed() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrEdited(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsNull() {

    //setup
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(null);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsNew() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsLoaded() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.LOADED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsEdited() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsDeleted() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoaded_whenTheGivenDatabaseObjectIsClosed() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoaded(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsNull() {

    //setup
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(null);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsNew() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.NEW);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsLoaded() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.LOADED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsEdited() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.EDITED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsDeleted() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.DELETED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    //verification
    expectNot(result);
  }

  //method
  @Test
  void testCase_isNewOrLoadedOrEdited_whenTheGivenDatabaseObjectIsClosed() {

    //setup databaseObjectMock
    final var databaseObjectMock = Mockito.mock(IDatabaseObject.class);
    Mockito.when(databaseObjectMock.getState()).thenReturn(DatabaseObjectState.CLOSED);

    //setup testUnit
    final var testUnit = new DatabaseObjectTool();

    //execution
    final var result = testUnit.isNewOrLoadedOrEdited(databaseObjectMock);

    //verification
    expectNot(result);
  }
}
