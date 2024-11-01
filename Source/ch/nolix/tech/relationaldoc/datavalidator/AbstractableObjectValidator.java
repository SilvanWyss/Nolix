package ch.nolix.tech.relationaldoc.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableObjectEvaluator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public final class AbstractableObjectValidator {

  private static final AbstractableObjectEvaluator ABSTRACTABLE_OBJECT_EVALUATOR = new AbstractableObjectEvaluator();

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
