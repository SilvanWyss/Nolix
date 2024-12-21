package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.CategorizableReferenceContentExaminer;
import ch.nolix.application.relationaldoc.backend.datamodel.CategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

public final class CategorizableReferenceContentValidator {

  private static final CategorizableReferenceContentExaminer ABSTRACT_REFERENCE_CONTENT_EVALUATOR = //
  new CategorizableReferenceContentExaminer();

  public void assertCanSetReferenceType(
    final CategorizableReferenceContent categorizableReferenceContent,
    final ICategorizableObject referenceType) {
    if (!ABSTRACT_REFERENCE_CONTENT_EVALUATOR.canSetReferenceType(categorizableReferenceContent, referenceType)) {
      throw InvalidArgumentException.forArgumentNameAndArgument("reference type", referenceType);
    }
  }
}
