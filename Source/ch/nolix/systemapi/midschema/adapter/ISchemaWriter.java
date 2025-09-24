package ch.nolix.systemapi.midschema.adapter;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.midschema.model.TableDto;
import ch.nolix.systemapi.midschema.structure.TableIdentification;

public interface ISchemaWriter extends IResettableChangeSaver {
  void addColumn(TableIdentification table, ColumnDto column);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);

  void setColumnModel(
    String tableName,
    String columnName,
    FieldType fieldType,
    DataType dataType,
    IContainer<String> referenceableTableIds,
    IContainer<String> backReferenceableColumnIds);
}
