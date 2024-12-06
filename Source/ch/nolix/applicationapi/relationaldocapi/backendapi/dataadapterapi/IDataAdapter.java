package ch.nolix.applicationapi.relationaldocapi.backendapi.dataadapterapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.coreapi.programstructureapi.builderapi.EmptyCopyable;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  IAbstractableObject createObject();

  IContainer<? extends IAbstractableObject> getStoredTopLevelObjects();
}
