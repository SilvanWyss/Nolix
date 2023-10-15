//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {

  // method declaration
  void noteUpdate();

  // method declaration
  void setUpdateAction(IAction updateAction);
}
