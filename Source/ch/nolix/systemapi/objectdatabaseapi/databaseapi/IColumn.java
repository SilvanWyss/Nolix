//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn extends IDatabaseObject, Identified, Named {

  //method declaration
  IParameterizedPropertyType getParameterizedPropertyType();

  //method declaration
  ITable<IEntity> getStoredParentTable();

  //method
  boolean technicalContainsGivenValueInPersistedData(String value);
}
