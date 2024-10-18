package ch.nolix.tech.relationaldoc.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.ConcreteValueContentEvaluator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteValueContent;

public final class ConcreteValueContentValidator {

  private static final ConcreteValueContentEvaluator CONCRETE_VALUE_CONTENT_EVALUATOR = //
  new ConcreteValueContentEvaluator();

  public void assertCanAddValue(final IConcreteValueContent concreteValueContent, final String value) {
    if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canAddValue(concreteValueContent, value)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.VALUE, value);
    }
  }

  public void assertCanRemoveValue(final IConcreteValueContent concreteValueContent) {
    if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canRemoveValue(concreteValueContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(concreteValueContent, "cannot remove value");
    }
  }

  public void assertCanRemoveValues(final IConcreteValueContent concreteValueContent) {
    if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canRemoveValues(concreteValueContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(concreteValueContent, "cannot remove values");
    }
  }

  public void assertCanSetDataType(final IConcreteValueContent concreteValueContent, final DataType dataType) {
    if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canSetDataType(concreteValueContent, dataType)) {
      throw InvalidArgumentException.forArgument(dataType);
    }
  }
}
