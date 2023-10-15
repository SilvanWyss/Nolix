//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.requestapi.VoidnessRequestable;

//interface
public interface IEntityFlyWeight extends VoidnessRequestable {

  // method declaration
  void noteInsert();

  // method declaration
  void setInsertAction(IAction insertAction);
}
