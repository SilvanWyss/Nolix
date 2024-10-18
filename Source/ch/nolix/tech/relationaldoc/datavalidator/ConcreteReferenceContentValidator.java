package ch.nolix.tech.relationaldoc.datavalidator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.tech.relationaldoc.dataevaluator.ConcreteReferenceContentEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.ConcreteReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

public final class ConcreteReferenceContentValidator {

  private static final ConcreteReferenceContentEvaluator CONCRETE_REFERENCE_CONTENT_EVALUATOR = //
  new ConcreteReferenceContentEvaluator();

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
