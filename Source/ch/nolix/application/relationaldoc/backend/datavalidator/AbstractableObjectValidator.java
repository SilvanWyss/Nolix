package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractableObjectExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class AbstractableObjectValidator {

  private static final AbstractableObjectExaminer ABSTRACTABLE_OBJECT_EVALUATOR = new AbstractableObjectExaminer();

  public void assertCanAddBaseType(final IAbstractableObject abstractableObject, final IAbstractableObject baseType) {
    if (!ABSTRACTABLE_OBJECT_EVALUATOR.canAddBaseType(abstractableObject, baseType)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.BASE_TYPE,
        baseType,
        "cannot be added to the AbstractableObject");
    }
  }

  public void assertCanAddField(final IAbstractableObject abstractableObject, final IAbstractableField field) {
    if (!ABSTRACTABLE_OBJECT_EVALUATOR.canAddField(abstractableObject, field)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.FIELD,
        field,
        "cannot be added to the AbstractableObject");
    }
  }

  public void assertCanBeSetAsConcrete(final IAbstractableObject abstractableObject) {
    if (!ABSTRACTABLE_OBJECT_EVALUATOR.canBeSetAsConcrete(abstractableObject)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(abstractableObject, "cannot be set as concrete");
    }
  }

  public void assertCanSetName(final IAbstractableObject abstractableObject, final String name) {
    if (!ABSTRACTABLE_OBJECT_EVALUATOR.canSetName(abstractableObject, name)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.NAME, name);
    }
  }
}
