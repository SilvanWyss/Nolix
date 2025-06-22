package ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.creationapi.copierapi.EmptyCopyable;
import ch.nolix.coreapi.resourcecontrolapi.savecontrolapi.IChangeSaver;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  ISmartObject createObject();

  IContainer<? extends ISmartObject> getStoredTopLevelObjects();
}
