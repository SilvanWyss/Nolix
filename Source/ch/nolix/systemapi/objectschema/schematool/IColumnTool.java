package ch.nolix.systemapi.objectschema.schematool;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseContentType;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

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
