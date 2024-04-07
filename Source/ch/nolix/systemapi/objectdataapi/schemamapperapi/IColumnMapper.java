//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IColumnMapper {

  //method declaration
  IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    IField field,
    IContainer<ITable> referencableTables);

  //method declaration
  <E extends IEntity> IContainer<IColumn> createColumnsFromGivenEntityTypeUsingGivenReferencableTables(
    Class<E> entityType,
    IContainer<ITable> referencableTables);

  //method declaration
  IContainer<IColumn> createColumnsFromGivenEntityUsingGivenReferencableTables(
    IEntity entity,
    IContainer<ITable> referencableTables);
}
