//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IReferenceContent extends IContent {

  //method declaration
  IContainer<? extends Predicate<IAbstractableObject>> getConstraints();

  //method declaration
  IAbstractableObject getStoredReferencedType();
}
