package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IColumnTool extends IDatabaseObjectExaminer {

  void assertBelongsToTable(IColumn column);

  void assertDoesNotBelongToTable(IColumn column);

  void assertIsABackReferenceColumn(IColumn column);

  void assertIsAReferenceColumn(IColumn column);

  boolean belongsToDatabase(IColumn column);

  BaseContentType getBaseFieldType(IColumn column);

  ContentType getFieldType(IColumn column);

  IDatabase getParentDatabase(IColumn column);

  boolean isABackReferenceColumn(IColumn column);

  boolean isAReferenceColumn(IColumn column);

  boolean isAValidBackReferenceColumn(IColumn column);

  boolean isAValueColumn(IColumn column);

  boolean referencesBackGivenColumn(IColumn column, IColumn probableBackReferencedColumn);

  boolean referencesGivenTable(IColumn column, ITable table);
}
