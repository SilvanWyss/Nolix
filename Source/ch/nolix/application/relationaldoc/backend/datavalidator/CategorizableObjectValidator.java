package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.CategorizableObjectExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi.ICategorizableObjectValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class CategorizableObjectValidator implements ICategorizableObjectValidator {

  private static final CategorizableObjectExaminer CATEGORIZABLE_OBJECT_EVALUATOR = new CategorizableObjectExaminer();

  @Override
  public void assertCanAddBaseType(
    final ICategorizableObject categorizableObject,
    final ICategorizableObject baseType) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canAddBaseType(categorizableObject, baseType)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.BASE_TYPE,
        baseType,
        "cannot be added to the CategorizableObject");
    }
  }

  @Override
  public void assertCanAddField(
    final ICategorizableObject categorizableObject,
    final ICategorizableField categorizableField) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canAddField(categorizableObject, categorizableField)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.FIELD,
        categorizableField,
        "cannot be added to the CategorizableObject");
    }
  }

  @Override
  public void assertCanBeSetAsConcrete(final ICategorizableObject categorizableObject) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canBeSetAsConcrete(categorizableObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(categorizableObject, "cannot be set as concrete");
    }
  }

  @Override
  public void assertCanSetName(final ICategorizableObject categorizableObject, final String name) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canSetName(categorizableObject, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.NAME, name);
    }
  }
}
