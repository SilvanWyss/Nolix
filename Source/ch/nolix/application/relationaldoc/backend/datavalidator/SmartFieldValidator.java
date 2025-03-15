package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.SmartFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi.ISmartFieldValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class SmartFieldValidator implements ISmartFieldValidator {

  private static final SmartFieldExaminer CATEGORIZABLE_FIELD_EXAMINER = new SmartFieldExaminer();

  @Override
  public void assertCanBeSetAsAbstract(final ISmartField smartField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetAsAbstract(smartField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartField, "cannot be set as abstract");
    }
  }

  @Override
  public void assertCanBeSetAsConcrete(ISmartField smartField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetAsConcrete(smartField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartField, "cannot be set as concrete");
    }
  }

  @Override
  public void assertCanBeSetForReferences(final ISmartField smartField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetForReferences(smartField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartField, "cannot be set for references");
    }
  }

  @Override
  public void assertCanBeSetForValues(final ISmartField smartField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetForValues(smartField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartField, "cannot be set for values");
    }
  }

  @Override
  public void assertCanSetCardinality(final ISmartField smartField, final Cardinality cardinality) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canSetCardinality(smartField, cardinality)) {
      throw InvalidArgumentException.forArgument(cardinality);
    }
  }

  @Override
  public void assertCanSetName(final ISmartField smartField, final String name) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canSetName(smartField, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.NAME, name);
    }
  }

  @Override
  public void assertDoesNotInheritFromAnotherField(final ISmartField smartField) {
    if (smartField.inheritsFromBaseField()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartField, "inherits from another field");
    }
  }
}
