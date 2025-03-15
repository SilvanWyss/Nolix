package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface ISmartFieldValidator {

  void assertCanBeSetAsAbstract(ISmartField smartField);

  void assertCanBeSetAsConcrete(ISmartField smartField);

  void assertCanBeSetForReferences(ISmartField smartField);

  void assertCanBeSetForValues(ISmartField smartField);

  void assertCanSetCardinality(ISmartField smartField, Cardinality cardinality);

  void assertCanSetName(ISmartField smartField, String name);

  void assertDoesNotInheritFromAnotherField(ISmartField smartField);
}
