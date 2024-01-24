//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.functionapi.mutationapi.Resettable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;

//interface
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  //method declaration
  int getSaveCount();
}
