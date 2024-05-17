//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IColumn extends IDatabaseObject, IIdHolder, INameHolder {

  //method declaration
  IParameterizedFieldType getParameterizedFieldType();

  //method declaration
  ITable<IEntity> getStoredParentTable();

  //method declaration
  boolean internalContainsGivenValueInPersistedData(String value);
}
