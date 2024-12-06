package ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public interface IAbstractableFieldValidator {

  void assertCanBeSetAsAbstract(IAbstractableField abstractableField);

  void assertCanBeSetAsConcrete(IAbstractableField abstractableField);

  void assertCanBeSetForReferences(IAbstractableField abstractableField);

  void assertCanBeSetForValues(IAbstractableField abstractableField);

  void assertCanSetCardinality(IAbstractableField abstractableField, Cardinality cardinality);

  void assertCanSetName(IAbstractableField abstractableField, String name);

  void assertDoesNotInheritFromAnotherField(IAbstractableField abstractableField);
}
