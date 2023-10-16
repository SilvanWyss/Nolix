//package declaration
package ch.nolix.systemapi.objectschemaapi.schemahelperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjecthelperapi.IDatabaseObjectHelper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseHelper extends IDatabaseObjectHelper {

  //method declaration
  boolean allBackReferencesAreValid(IDatabase database);

  //method declaration
  void assertAllBackReferencesAreValid(IDatabase database);

  //method declaration
  void assertCanAddGivenTable(IDatabase database, ITable table);

  //method declaration
  void assertCanSetGivenNameToDatabase(String name);

  //method declaration
  void assertContainsGivenTable(IDatabase database, ITable table);

  //method declaration
  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  //method declaration
  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  //method declaration
  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  //method declaration
  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);

  //method declaration
  boolean canAddGivenTable(IDatabase database, ITable table);

  //method declaration
  boolean canAddTable(IDatabase database);

  //method declaration
  boolean canSetGivenNameToDatabase(String name);

  //method declaration
  boolean containsGivenTable(IDatabase database, ITable table);

  //method declaration
  boolean containsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  //method declaration
  boolean containsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  //method declaration
  boolean containsTableWithGivenColumn(IDatabase database, IColumn column);

  //method declaration
  boolean containsTableWithGivenName(IDatabase database, String name);

  //method declaration
  void deleteTableWithGivenName(IDatabase database, String name);

  //method declaration
  IContainer<IColumn> getStoredAllBackReferenceColumns(IDatabase database);

  //method declaration
  ITable getStoredTableWithGivenName(IDatabase database, String name);

  //method declaration
  int getTableCount(IDatabase database);
}
