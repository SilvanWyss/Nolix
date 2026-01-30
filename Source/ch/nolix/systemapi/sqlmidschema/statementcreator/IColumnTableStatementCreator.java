/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.sqlmidschema.statementcreator;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.structure.ColumnIdentification;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

/**
 * @author Silvan Wyss
 */
public interface IColumnTableStatementCreator {
  String createStatementToAddColumnIntoColumnTable(TableIdentification table, ColumnDto column);

  String createStatementToSetContentModelInColumnTable(
    ColumnIdentification column,
    FieldType fieldType,
    DataType dataType);
}
