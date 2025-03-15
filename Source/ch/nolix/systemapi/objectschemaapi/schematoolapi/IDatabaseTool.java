package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IDatabaseTool extends IDatabaseObjectExaminer {

  boolean allBackReferencesAreValid(IDatabase database);

  void assertAllBackReferencesAreValid(IDatabase database);

  void assertCanAddGivenTable(IDatabase database, ITable table);

  void assertCanSetGivenNameToDatabase(String name);

  void assertContainsTableReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithColumnBackReferencedByGivenColumn(IDatabase database, IColumn column);

  void assertContainsTableWithGivenColumn(IDatabase database, IColumn column);

  void assertDoesNotContainTableWithGivenName(IDatabase database, String name);

  void deleteTableWithGivenName(IDatabase database, String name);

  IContainer<? extends IColumn> getStoredAllBackReferenceColumns(IDatabase database);

  ITable getStoredTableWithGivenName(IDatabase database, String name);

  int getTableCount(IDatabase database);
}
