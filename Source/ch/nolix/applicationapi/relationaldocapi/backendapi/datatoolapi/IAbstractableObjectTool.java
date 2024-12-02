package ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IAbstractableObjectTool {

  IContainer<? extends IAbstractableObject> getStoredSubTypesUsingSimplerMethods(
    IAbstractableObject abstractableObject);
}
