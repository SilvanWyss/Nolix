//package declaration
package ch.nolix.techapi.relationaldocapi.dataaapterapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IChangeSaver;
import ch.nolix.coreapi.programstructureapi.factoryapi.EmptyCopyable;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//interface
public interface IDataAdapter extends EmptyCopyable<IDataAdapter>, IChangeSaver {

  // method declaration
  IAbstractableObject createObject();

  // method declaration
  void deleteObject(IAbstractableObject object);

  // method declaration
  IContainer<? extends IAbstractableObject> getStoredTopLevelObjects();
}
