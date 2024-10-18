package ch.nolix.techapi.relationaldocapi.dataaapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.coreapi.programstructureapi.builderapi.EmptyCopyable;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  IAbstractableObject createObject();

  void deleteObject(IAbstractableObject object);

  IContainer<? extends IAbstractableObject> getStoredTopLevelObjects();
}
