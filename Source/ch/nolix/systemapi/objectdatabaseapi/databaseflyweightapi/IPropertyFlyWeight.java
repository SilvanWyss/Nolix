//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi;

//own imports
import ch.nolix.coreapi.methodapi.requestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteUpdate();

  //method declaration
  void setUpdateAction(Runnable updateAction);
}
