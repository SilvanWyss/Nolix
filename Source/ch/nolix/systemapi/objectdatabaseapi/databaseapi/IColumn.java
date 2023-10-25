//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn extends IDatabaseObject, IIdHolder, Named {

  //method declaration
  IParameterizedPropertyType getParameterizedPropertyType();

  //method declaration
  ITable<IEntity> getStoredParentTable();

  //method
  boolean technicalContainsGivenValueInPersistedData(String value);
}
