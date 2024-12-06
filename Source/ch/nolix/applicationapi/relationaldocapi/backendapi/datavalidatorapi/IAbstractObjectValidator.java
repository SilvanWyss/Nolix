package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;

public interface IAbstractObjectValidator {

  void assertCanAddBaseType(IAbstractableObject abstractableObject, IAbstractableObject baseType);

  void assertCanAddField(IAbstractableObject abstractableObject, IAbstractableField abstractableField);

  void assertCanBeSetAsConcrete(IAbstractableObject abstractableObject);

  void assertCanSetName(IAbstractableObject abstractableObject, String name);
}
