package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractableFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class AbstractableFieldValidator {

  private static final AbstractableFieldExaminer ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldExaminer();

  public void assertCanBeSetAsAbstract(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsAbstract(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as abstract");
    }
  }

  public void assertCanBeSetAsConcrete(IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetAsConcrete(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set as concrete");
    }
  }

  public void assertCanBeSetForReferences(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetForReferences(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set for references");
    }
  }

  public void assertCanBeSetForValues(final IAbstractableField abstractableField) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canBeSetForValues(abstractableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "cannot be set for values");
    }
  }

  public void assertCanSetCardinality(final IAbstractableField abstractableField, final Cardinality cardinality) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetCardinality(abstractableField, cardinality)) {
      throw InvalidArgumentException.forArgument(cardinality);
    }
  }

  public void assertCanSetName(final IAbstractableField abstractableField, final String name) {
    if (!ABSTRACTABLE_FIELD_EVALUATOR.canSetName(abstractableField, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.NAME, name);
    }
  }

  public void assertDoesNotInheritFromAnotherField(final IAbstractableField abstractableField) {
    if (abstractableField.inheritsFromBaseField()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableField, "inherits from another field");
    }
  }
}
