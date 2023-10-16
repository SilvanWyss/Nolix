//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IConcreteReferenceContent extends IReferenceContent {

  //method declaration
  IConcreteReferenceContent addObject(IAbstractableObject object);

  //method
  IContainer<? extends IAbstractableObject> getStoredReferencedObjects();

  //method declaration
  void removeObject(IAbstractableObject object);

  //method declaration
  void removeObjects();
}
