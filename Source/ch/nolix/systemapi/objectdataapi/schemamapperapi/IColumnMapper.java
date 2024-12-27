package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IColumnMapper {

  IColumn mapFieldToColumn(IField field, IContainer<ITable> referencedTables);

  IContainer<IColumn> mapEnityTypeToColumns(Class<? extends IEntity> entityType, IContainer<ITable> referencedTables);

  IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
    IEntity entity,
    IContainer<ITable> referencableTables);
}
