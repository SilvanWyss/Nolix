//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

//own imports
import ch.nolix.coreapi.generalstateapi.statemutationapi.Resettable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

//interface
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  //method declaration
  int getSaveCount();
}
