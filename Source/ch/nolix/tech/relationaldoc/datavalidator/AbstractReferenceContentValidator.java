package ch.nolix.tech.relationaldoc.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractReferenceContentEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.AbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

public final class AbstractReferenceContentValidator {

  private static final AbstractReferenceContentEvaluator ABSTRACT_REFERENCE_CONTENT_EVALUATOR = //
  new AbstractReferenceContentEvaluator();

  public void assertCanSetReferenceType(
    final AbstractReferenceContent abstractReferenceContent,
    final IAbstractableObject referenceType) {
    if (!ABSTRACT_REFERENCE_CONTENT_EVALUATOR.canSetReferenceType(abstractReferenceContent, referenceType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument("reference type", referenceType);
    }
  }
}
