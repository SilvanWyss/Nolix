package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.CategorizableValueContentExaminer;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelabasepi.DataType;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableValueContent;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class CategorizableValueContentValidator {

  private static final CategorizableValueContentExaminer ABSTRACT_VALUE_CONTENT_EVALUATOR = //
  new CategorizableValueContentExaminer();

  public void assertCanSetDataType(final ICategorizableValueContent categorizableValueContent, final DataType dataType) {
    if (!ABSTRACT_VALUE_CONTENT_EVALUATOR.canSetDataType(categorizableValueContent, dataType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.DATA_TYPE, dataType);
    }
  }
}
