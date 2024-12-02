package ch.nolix.application.relationaldoc.backend.datavalidator;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.ConcreteReferenceContentExaminer;
import ch.nolix.application.relationaldoc.backend.datamodel.ConcreteReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

public final class ConcreteReferenceContentValidator {

  private static final ConcreteReferenceContentExaminer CONCRETE_REFERENCE_CONTENT_EVALUATOR = //
  new ConcreteReferenceContentExaminer();

  public void assertCanAddObject(ConcreteReferenceContent concreteReferenceContent, IAbstractableObject object) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canAddObject(concreteReferenceContent, object)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        concreteReferenceContent.getStoredParentField(),
        "cannot add the given object");
    }
  }

  public void assertCanRemoveObjects(final IConcreteReferenceContent concreteReferenceContent) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canRemoveObjects(concreteReferenceContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        concreteReferenceContent.getStoredParentField(),
        "cannot remove its objects");
    }
  }

  public void assertCanRemoveOneObject(final IConcreteReferenceContent concreteReferenceContent) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canRemoveOneObject(concreteReferenceContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        concreteReferenceContent.getStoredParentField(),
        "cannot remove an object");
    }
  }
}
