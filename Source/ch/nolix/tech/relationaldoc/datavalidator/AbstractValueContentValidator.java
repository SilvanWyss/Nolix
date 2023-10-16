//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractValueContentEvaluator;
import ch.nolix.techapi.relationaldocapi.baseapi.DataType;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractValueContent;

//class
public final class AbstractValueContentValidator {

  //constant
  private static final AbstractValueContentEvaluator ABSTRACT_VALUE_CONTENT_EVALUATOR = //
      new AbstractValueContentEvaluator();

  //method
  public void assertCanSetDataType(final IAbstractValueContent abstractValueContent, final DataType dataType) {
    if (!ABSTRACT_VALUE_CONTENT_EVALUATOR.canSetDataType(abstractValueContent, dataType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument(LowerCaseCatalogue.DATA_TYPE, dataType);
    }
  }
}
