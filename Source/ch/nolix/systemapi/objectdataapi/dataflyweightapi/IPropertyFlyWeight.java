//package declaration
package ch.nolix.systemapi.objectdataapi.dataflyweightapi;

//own imports
import ch.nolix.coreapi.generalstateapi.staterequestapi.VoidnessRequestable;

//class
public interface IPropertyFlyWeight extends VoidnessRequestable {

  //method declaration
  void noteUpdate();

  //method declaration
  void setUpdateAction(Runnable updateAction);
}
