package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IConcreteReferenceContent extends IReferenceContent {

  IConcreteReferenceContent addObject(IAbstractableObject object);

  IContainer<? extends IAbstractableObject> getStoredReferencedObjects();

  void removeObject(IAbstractableObject object);

  void removeObjects();
}
