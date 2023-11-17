//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

import ch.nolix.coreapi.methodapi.mutationapi.Resettable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  //method declaration
  int getSaveCount();
}
