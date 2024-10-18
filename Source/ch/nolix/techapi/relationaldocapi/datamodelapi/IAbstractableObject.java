package ch.nolix.techapi.relationaldocapi.datamodelapi;

import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.techapi.relationaldocapi.baseapi.Abstractable;

public interface IAbstractableObject
extends Abstractable<IAbstractableObject>, IFluentMutableNameHolder<IAbstractableObject> {

  IAbstractableObject addBaseType(IAbstractableObject baseType);

  IAbstractableObject addField(IAbstractableField field);

  IContainer<? extends IAbstractableObject> getStoredBaseTypes();

  IContainer<? extends IAbstractableObject> getStoredConcreteSubTypes();

  IContainer<? extends IAbstractableField> getStoredDeclaredFields();

  IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes();

  IContainer<? extends IAbstractableObject> getStoredDirectSubTypes();

  IContainer<? extends IAbstractableField> getStoredFields();

  IContainer<? extends IAbstractableObject> getStoredSubTypes();

  boolean isSubTypeOfObject(IAbstractableObject object);

  void removeDirectBaseType(IAbstractableObject directBaseType);

  void removeNonInheritedField(IAbstractableField nonInheritedField);
}
