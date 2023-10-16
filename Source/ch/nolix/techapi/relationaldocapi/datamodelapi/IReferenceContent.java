//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;

//interface
public interface IReferenceContent extends IContent {

  //method declaration
  IContainer<? extends IConstraint<IAbstractableObject>> getConstraints();

  //method declaration
  IAbstractableObject getStoredReferencedType();
}
