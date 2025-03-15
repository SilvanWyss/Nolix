package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IConcreteReferenceContent extends IReferenceContent {

  IConcreteReferenceContent addObject(ISmartObject object);

  IContainer<? extends ISmartObject> getStoredReferencedObjects();

  void removeObject(ISmartObject object);

  void removeObjects();
}
