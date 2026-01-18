/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectschema.modeltool;

import ch.nolix.systemapi.databaseobject.modelexaminer.IDatabaseObjectExaminer;
import ch.nolix.systemapi.midschema.fieldproperty.BaseFieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IDatabase;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
 */
public interface IColumnTool extends IDatabaseObjectExaminer {
  void assertBelongsToTable(IColumn column);

  void assertDoesNotBelongToTable(IColumn column);

  void assertIsABackReferenceColumn(IColumn column);

  void assertIsAReferenceColumn(IColumn column);

  boolean belongsToDatabase(IColumn column);

  BaseFieldType getBaseFieldType(IColumn column);

  IDatabase getParentDatabase(IColumn column);

  boolean isABackReferenceColumn(IColumn column);

  boolean isAReferenceColumn(IColumn column);

  boolean isAValidBackReferenceColumn(IColumn column);

  boolean isAValueColumn(IColumn column);

  boolean referencesBackGivenColumn(IColumn column, IColumn probableBackReferencedColumn);

  boolean referencesGivenTable(IColumn column, ITable table);
}
