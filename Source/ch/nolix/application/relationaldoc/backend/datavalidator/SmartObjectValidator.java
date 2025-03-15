package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.SmartObjectExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datavalidatorapi.ISmartObjectValidator;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class SmartObjectValidator implements ISmartObjectValidator {

  private static final SmartObjectExaminer CATEGORIZABLE_OBJECT_EVALUATOR = new SmartObjectExaminer();

  @Override
  public void assertCanAddBaseType(
    final ISmartObject smartObject,
    final ISmartObject baseType) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canAddBaseType(smartObject, baseType)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalog.BASE_TYPE,
        baseType,
        "cannot be added to the SmartObject");
    }
  }

  @Override
  public void assertCanAddField(
    final ISmartObject smartObject,
    final ISmartField smartField) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canAddField(smartObject, smartField)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalog.FIELD,
        smartField,
        "cannot be added to the SmartObject");
    }
  }

  @Override
  public void assertCanBeSetAsConcrete(final ISmartObject smartObject) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canBeSetAsConcrete(smartObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(smartObject, "cannot be set as concrete");
    }
  }

  @Override
  public void assertCanSetName(final ISmartObject smartObject, final String name) {
    if (!CATEGORIZABLE_OBJECT_EVALUATOR.canSetName(smartObject, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.NAME, name);
    }
  }
}
