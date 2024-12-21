package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface ICategorizableFieldValidator {

  void assertCanBeSetAsAbstract(ICategorizableField categorizableField);

  void assertCanBeSetAsConcrete(ICategorizableField categorizableField);

  void assertCanBeSetForReferences(ICategorizableField categorizableField);

  void assertCanBeSetForValues(ICategorizableField categorizableField);

  void assertCanSetCardinality(ICategorizableField categorizableField, Cardinality cardinality);

  void assertCanSetName(ICategorizableField categorizableField, String name);

  void assertDoesNotInheritFromAnotherField(ICategorizableField categorizableField);
}
