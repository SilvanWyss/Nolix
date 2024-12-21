package ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.coreapi.structurecontrolapi.copierapi.EmptyCopyable;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  ICategorizableObject createObject();

  IContainer<? extends ICategorizableObject> getStoredTopLevelObjects();
}
