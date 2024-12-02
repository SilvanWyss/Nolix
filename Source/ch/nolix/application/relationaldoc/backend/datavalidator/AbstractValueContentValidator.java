package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractValueContentExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractValueContent;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class AbstractValueContentValidator {

  private static final AbstractValueContentExaminer ABSTRACT_VALUE_CONTENT_EVALUATOR = //
  new AbstractValueContentExaminer();

  public void assertCanSetDataType(final IAbstractValueContent abstractValueContent, final DataType dataType) {
    if (!ABSTRACT_VALUE_CONTENT_EVALUATOR.canSetDataType(abstractValueContent, dataType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.DATA_TYPE, dataType);
    }
  }
}
