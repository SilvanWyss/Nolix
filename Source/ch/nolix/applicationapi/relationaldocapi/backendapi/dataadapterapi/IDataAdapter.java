package ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.coreapi.structurecontrolapi.copierapi.EmptyCopyable;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  ISmartObject createObject();

  IContainer<? extends ISmartObject> getStoredTopLevelObjects();
}
