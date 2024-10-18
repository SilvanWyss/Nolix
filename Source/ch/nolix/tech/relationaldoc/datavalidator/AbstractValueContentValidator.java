package ch.nolix.tech.relationaldoc.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractValueContentEvaluator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;

public final class AbstractValueContentValidator {

  private static final AbstractValueContentEvaluator ABSTRACT_VALUE_CONTENT_EVALUATOR = //
  new AbstractValueContentEvaluator();

  public void assertCanSetDataType(final IAbstractValueContent abstractValueContent, final DataType dataType) {
    if (!ABSTRACT_VALUE_CONTENT_EVALUATOR.canSetDataType(abstractValueContent, dataType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.DATA_TYPE, dataType);
    }
  }
}
