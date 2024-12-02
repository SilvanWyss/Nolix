package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractReferenceContentExaminer;
import ch.nolix.application.relationaldoc.backend.datamodel.AbstractReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

public final class AbstractReferenceContentValidator {

  private static final AbstractReferenceContentExaminer ABSTRACT_REFERENCE_CONTENT_EVALUATOR = //
  new AbstractReferenceContentExaminer();

  public void assertCanSetReferenceType(
    final AbstractReferenceContent abstractReferenceContent,
    final IAbstractableObject referenceType) {
    if (!ABSTRACT_REFERENCE_CONTENT_EVALUATOR.canSetReferenceType(abstractReferenceContent, referenceType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument("reference type", referenceType);
    }
  }
}
