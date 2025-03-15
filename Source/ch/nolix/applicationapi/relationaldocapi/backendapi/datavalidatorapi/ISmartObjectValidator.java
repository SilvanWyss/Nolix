package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;

public interface ISmartObjectValidator {

  void assertCanAddBaseType(ISmartObject smartObject, ISmartObject baseType);

  void assertCanAddField(ISmartObject smartObject, ISmartField smartField);

  void assertCanBeSetAsConcrete(ISmartObject smartObject);

  void assertCanSetName(ISmartObject smartObject, String name);
}
