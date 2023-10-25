//package declaration
package ch.nolix.techapi.relationaldocapi.datamodelapi;

//own imports
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

//interface
public interface IAbstractableObject
    extends Abstractable<IAbstractableObject>, IFluentMutableNameHolder<IAbstractableObject> {

  //method declaration
  IAbstractableObject addBaseType(IAbstractableObject baseType);

  //method declaration
  IAbstractableObject addField(IAbstractableField field);

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredBaseTypes();

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredConcreteSubTypes();

  //method declaration
  IContainer<? extends IAbstractableField> getStoredDeclaredFields();

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes();

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredDirectSubTypes();

  //method declaration
  IContainer<? extends IAbstractableField> getStoredFields();

  //method declaration
  IContainer<? extends IAbstractableObject> getStoredSubTypes();

  //method declaration
  boolean isSubTypeOfObject(IAbstractableObject object);

  //method declaration
  void removeDirectBaseType(IAbstractableObject directBaseType);

  //method declaration
  void removeNonInheritedField(IAbstractableField nonInheritedField);
}
