//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.stateapi.statemutationapi.Resettable;

//interface
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  //method declaration
  int getSaveCount();
}
