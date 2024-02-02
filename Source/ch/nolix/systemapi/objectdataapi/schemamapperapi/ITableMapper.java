//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ITableMapper {

  //method declaration
  IContainer<ITable> createEmptyTablesFromSchema(ISchema schema);

  //method declaration
  IContainer<ITable> createTablesFrom(ISchema schema);
}
