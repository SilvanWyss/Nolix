package ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.CATEGORIZABLE;
import ch.nolix.coreapi.attributeapi.fluentmutablemandatoryattributeapi.IFluentMutableNameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ICategorizableObject
extends CATEGORIZABLE<ICategorizableObject>, IFluentMutableNameHolder<ICategorizableObject> {

  ICategorizableObject addBaseType(ICategorizableObject baseType);

  ICategorizableObject addField(ICategorizableField field);

  IContainer<? extends ICategorizableObject> getStoredBaseTypes();

  IContainer<? extends ICategorizableObject> getStoredConcreteSubTypes();

  IContainer<? extends ICategorizableField> getStoredDeclaredFields();

  IContainer<? extends ICategorizableObject> getStoredDirectBaseTypes();

  IContainer<? extends ICategorizableObject> getStoredDirectSubTypes();

  IContainer<? extends ICategorizableField> getStoredFields();

  IContainer<? extends ICategorizableObject> getStoredSubTypes();

  boolean isSubTypeOfObject(ICategorizableObject object);

  void removeDirectBaseType(ICategorizableObject directBaseType);

  void removeNonInheritedField(ICategorizableField nonInheritedField);
}
