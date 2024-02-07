//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//Java imports
import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IMultiReference<E extends IEntity> extends Clearable, IBaseReference<E> {

  //method declaration
  void addEntity(Object entity);

  //method declaration
  IContainer<E> getReferencedEntities();

  //method declaration
  IContainer<String> getReferencedEntityIds();

  //method declaration
  IContainer<? extends IMultiReferenceEntry<E>> getStoredLocalEntries();

  //method declaration
  void removeEntity(Object entity);

  //method declaration
  void removeFirstEntity(Predicate<E> selector);
}
