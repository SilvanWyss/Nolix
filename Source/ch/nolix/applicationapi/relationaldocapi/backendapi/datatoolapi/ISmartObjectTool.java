package ch.nolix.applicationapi.relationaldocapi.backendapi.datatoolapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ISmartObjectTool {

  IContainer<? extends ISmartObject> getStoredSubTypesUsingSimplerMethods(
    ISmartObject smartObject);
}
