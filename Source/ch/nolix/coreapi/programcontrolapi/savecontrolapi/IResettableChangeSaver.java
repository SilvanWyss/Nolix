//package declaration
package ch.nolix.coreapi.programcontrolapi.savecontrolapi;

//own imports
import ch.nolix.coreapi.functionapi.mutationapi.Resettable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//interface
public interface IResettableChangeSaver extends GroupCloseable, IChangeSaver, Resettable {

  //method declaration
  int getSaveCount();
}
