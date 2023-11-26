//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi;

//own imports
import ch.nolix.coreapi.methodapi.requestapi.VoidnessRequestable;

//interface
public interface IEntityFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteInsert();

  //method declaration
  void setInsertAction(Runnable insertAction);
}
