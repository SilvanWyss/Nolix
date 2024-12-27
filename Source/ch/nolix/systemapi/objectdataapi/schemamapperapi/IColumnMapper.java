package ch.nolix.systemapi.objectdataapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

public interface IColumnMapper {

  IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    IField field,
    IContainer<ITable> referencableTables);

  <E extends IEntity> IContainer<IColumn> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
    Class<E> entityType,
    IContainer<ITable> referencableTables);

  IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
    IEntity entity,
    IContainer<ITable> referencableTables);
}
