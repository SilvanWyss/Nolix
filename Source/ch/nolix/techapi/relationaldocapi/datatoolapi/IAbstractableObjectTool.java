//package declaration
package ch.nolix.techapi.relationaldocapi.datatoolapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//interface
public interface IAbstractableObjectTool {

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredSubTypesUsingSimplerMethods(
    IAbstractableObject abstractableObject);
}
