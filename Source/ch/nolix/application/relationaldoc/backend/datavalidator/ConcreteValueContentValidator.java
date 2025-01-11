package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.ConcreteValueContentExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteValueContent;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class ConcreteValueContentValidator {

  private static final ConcreteValueContentExaminer CONCRETE_VALUE_CONTENT_EVALUATOR = //
  new ConcreteValueContentExaminer();

  public void assertCanAddValue(final IConcreteValueContent concreteValueContent, final String value) {
    if (!CONCRETE_VALUE_CONTENT_EVALUATOR.canAddValue(concreteValueContent, value)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalog.VALUE, value);
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
