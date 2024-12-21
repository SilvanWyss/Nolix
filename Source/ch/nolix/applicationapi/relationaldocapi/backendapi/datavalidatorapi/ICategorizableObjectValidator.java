package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;

public interface ICategorizableObjectValidator {

  void assertCanAddBaseType(ICategorizableObject categorizableObject, ICategorizableObject baseType);

  void assertCanAddField(ICategorizableObject categorizableObject, ICategorizableField categorizableField);

  void assertCanBeSetAsConcrete(ICategorizableObject categorizableObject);

  void assertCanSetName(ICategorizableObject categorizableObject, String name);
}
