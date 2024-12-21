package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface ITableTool extends IDatabaseObjectExaminer {

  void assertContainsGivenColumn(ITable table, IColumn column);

  void assertDoesNotBelongToDatabase(ITable table);

  void assertDoesNotContainGivenColumn(ITable table, IColumn column);

  void assertDoesNotContainColumnWithGivenName(ITable table, String name);

  void assertIsNotReferenced(ITable table);

  boolean canBeAddedToDatabase(ITable table);

  boolean containsGivenColumn(ITable table, IColumn column);

  boolean containsColumnBackReferencedByGivenColumn(ITable table, IColumn column);

  boolean containsColumnThatReferencesBackGivenColumn(ITable table, IColumn column);

  boolean containsColumnThatReferencesGivenTable(ITable table, ITable probableReferencedTable);

  boolean containsColumnWithGivenName(ITable table, String name);

  int getColumnCount(ITable table);

  IContainer<IColumn> getStoredBackReferenceColumns(ITable table);

  IContainer<IColumn> getStoredBackReferencingColumns(ITable table);

  IContainer<IColumn> getStoredReferencingColumns(ITable table);

  boolean isReferenced(ITable table);
}
