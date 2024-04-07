//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//interface
public interface ITable
extends
Deletable,
IDatabaseObject,
IIdHolder,
IFluentMutableNameHolder<ITable> {

  //method declaration
  ITable addColumn(IColumn column);

  //method declaration
  boolean belongsToDatabase();

  //method declaration
  ITable createColumnWithNameAndParameterizedPropertyType(
    String name,
    IParameterizedFieldType parameterizedFieldType);

  //method declaration
  IFlatTableDto getFlatDto();

  //method declaration
  IDatabase getParentDatabase();

  //method declarations
  IContainer<IColumn> getStoredColumns();

  //method declaration
  ITableDto toDto();
}
