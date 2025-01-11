package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.CategorizableFieldExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi.ICategorizableFieldValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class CategorizableFieldValidator implements ICategorizableFieldValidator {

  private static final CategorizableFieldExaminer CATEGORIZABLE_FIELD_EXAMINER = new CategorizableFieldExaminer();

  @Override
  public void assertCanBeSetAsAbstract(final ICategorizableField categorizableField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetAsAbstract(categorizableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableField, "cannot be set as abstract");
    }
  }

  @Override
  public void assertCanBeSetAsConcrete(ICategorizableField categorizableField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetAsConcrete(categorizableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableField, "cannot be set as concrete");
    }
  }

  @Override
  public void assertCanBeSetForReferences(final ICategorizableField categorizableField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetForReferences(categorizableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableField, "cannot be set for references");
    }
  }

  @Override
  public void assertCanBeSetForValues(final ICategorizableField categorizableField) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canBeSetForValues(categorizableField)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableField, "cannot be set for values");
    }
  }

  @Override
  public void assertCanSetCardinality(final ICategorizableField categorizableField, final Cardinality cardinality) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canSetCardinality(categorizableField, cardinality)) {
      throw InvalidArgumentException.forArgument(cardinality);
    }
  }

  @Override
  public void assertCanSetName(final ICategorizableField categorizableField, final String name) {
    if (!CATEGORIZABLE_FIELD_EXAMINER.canSetName(categorizableField, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.NAME, name);
    }
  }

  @Override
  public void assertDoesNotInheritFromAnotherField(final ICategorizableField categorizableField) {
    if (categorizableField.inheritsFromBaseField()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableField, "inherits from another field");
    }
  }
}
