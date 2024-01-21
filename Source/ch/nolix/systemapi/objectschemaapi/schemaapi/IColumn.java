//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//interface
public interface IColumn
extends Deletable, EmptinessRequestable, IDatabaseObject, IIdHolder, IFluentMutableNameHolder<IColumn> {

  //method declaration
  boolean belongsToTable();

  //method declaration
  IParameterizedPropertyType getParameterizedPropertyType();

  //method declaration
  ITable getParentTable();

  //method declaration
  IColumn setParameterizedPropertyType(IParameterizedPropertyType parameterizedPropertyType);

  //method declaration
  IColumnDto toDto();
}
