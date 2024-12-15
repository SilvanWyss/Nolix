package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IDatabaseTool extends IDatabaseObjectTool {

  boolean allBackReferencesAreValid(IDatabase database);

  void assertAllBackReferencesAreValid(IDatabase database);

  void assertCanAddGivenTable(IDatabase database, ITable table);

  void assertCanSetGivenNameToDatabase(String name);

  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);

  boolean canAddGivenTable(IDatabase database, ITable table);

  boolean canAddTable(IDatabase database);

  boolean canSetGivenNameToDatabase(String name);

  boolean containsGivenTable(IDatabase database, ITable table);

  boolean containsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  boolean containsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  boolean containsTableWithGivenColumn(IDatabase database, IColumn column);

  boolean containsTableWithGivenName(IDatabase database, String name);

  void deleteTableWithGivenName(IDatabase database, String name);

  IContainer<IColumn> getStoredAllBackReferenceColumns(IDatabase database);

  ITable getStoredTableWithGivenName(IDatabase database, String name);

  int getTableCount(IDatabase database);
}
