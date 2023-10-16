//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.FluentNameable;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.functionapi.requestapi.EmptinessRequestable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.Deletable;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;

//interface
public interface IColumn
    extends Deletable, EmptinessRequestable, IDatabaseObject, Identified, FluentNameable<IColumn> {

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
