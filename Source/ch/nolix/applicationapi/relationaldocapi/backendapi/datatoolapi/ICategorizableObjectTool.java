package ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ICategorizableObjectTool {

  IContainer<? extends ICategorizableObject> getStoredSubTypesUsingSimplerMethods(
    ICategorizableObject categorizableObject);
}
