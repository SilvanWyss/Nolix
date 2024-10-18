package ch.nolix.techapi.relationaldocapi.datatoolapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public interface IAbstractableObjectTool {

  IContainer<? extends IAbstractableObject> getStoredSubTypesUsingSimplerMethods(
    IAbstractableObject abstractableObject);
}
