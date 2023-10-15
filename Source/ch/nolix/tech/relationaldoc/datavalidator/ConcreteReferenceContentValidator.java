//package declaration
package ch.nolix.tech.relationaldoc.datavalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.tech.relationaldoc.dataevaluator.ConcreteReferenceContentEvaluator;
import ch.nolix.tech.relationaldoc.datamodel.ConcreteReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

//class
public final class ConcreteReferenceContentValidator {

  // constant
  private static final ConcreteReferenceContentEvaluator CONCRETE_REFERENCE_CONTENT_EVALUATOR = //
      new ConcreteReferenceContentEvaluator();

  public void assertCanAddObject(ConcreteReferenceContent concreteReferenceContent, IAbstractableObject object) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canAddObject(concreteReferenceContent, object)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          concreteReferenceContent.getStoredParentField(),
          "cannot add the given object");
    }
  }

  // method
  public void assertCanRemoveObjects(final IConcreteReferenceContent concreteReferenceContent) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canRemoveObjects(concreteReferenceContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          concreteReferenceContent.getStoredParentField(),
          "cannot remove its objects");
    }
  }

  // method
  public void assertCanRemoveOneObject(final IConcreteReferenceContent concreteReferenceContent) {
    if (!CONCRETE_REFERENCE_CONTENT_EVALUATOR.canRemoveOneObject(concreteReferenceContent)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
          concreteReferenceContent.getStoredParentField(),
          "cannot remove an object");
    }
  }
}
