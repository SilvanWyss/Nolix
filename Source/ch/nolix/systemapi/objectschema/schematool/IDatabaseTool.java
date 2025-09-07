package ch.nolix.systemapi.objectschema.schematool;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

//TODO: Move assert methods to IDatabaseValidator
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
