//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IColumnMapper {

  //method declaration
  IColumn createColumnFromGivenPropertyUsingGivenReferencableTables(
    IProperty property,
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
