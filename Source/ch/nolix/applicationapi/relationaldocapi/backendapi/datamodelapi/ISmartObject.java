package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelbasepi.CATEGORIZABLE;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ISmartObject
extends CATEGORIZABLE<ISmartObject>, IFluentMutableNameHolder<ISmartObject> {

  ISmartObject addBaseType(ISmartObject baseType);

  ISmartObject addField(ISmartField field);

  IContainer<? extends ISmartObject> getStoredBaseTypes();

  IContainer<? extends ISmartObject> getStoredConcreteSubTypes();

  IContainer<? extends ISmartField> getStoredDeclaredFields();

  IContainer<? extends ISmartObject> getStoredDirectBaseTypes();

  IContainer<? extends ISmartObject> getStoredDirectSubTypes();

  IContainer<? extends ISmartField> getStoredFields();

  IContainer<? extends ISmartObject> getStoredSubTypes();

  boolean isSubTypeOfObject(ISmartObject object);

  void removeDirectBaseType(ISmartObject directBaseType);

  void removeNonInheritedField(ISmartField nonInheritedField);
}
