package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public interface IColumnMapper {

  IColumn mapFieldToColumn(IField field, IContainer<ITable> referencedTables);

  IContainer<IColumn> mapEntityToColumns(IEntity entity, IContainer<ITable> referencedTables);

  IContainer<IColumn> mapEntityTypeToColumns(Class<? extends IEntity> entityType, IContainer<ITable> referencedTables);
}
