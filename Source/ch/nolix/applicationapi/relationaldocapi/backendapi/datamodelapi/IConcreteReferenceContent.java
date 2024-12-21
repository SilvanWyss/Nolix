package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IConcreteReferenceContent extends IReferenceContent {

  IConcreteReferenceContent addObject(ICategorizableObject object);

  IContainer<? extends ICategorizableObject> getStoredReferencedObjects();

  void removeObject(ICategorizableObject object);

  void removeObjects();
}
