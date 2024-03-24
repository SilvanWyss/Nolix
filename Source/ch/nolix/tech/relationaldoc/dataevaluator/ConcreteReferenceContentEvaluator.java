//package declaration
package ch.nolix.tech.relationaldoc.dataevaluator;

//own imports
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

//class
public final class ConcreteReferenceContentEvaluator {

  //method
  public boolean canAddObject(
    final IConcreteReferenceContent concreteReferenceContent,
    final IAbstractableObject object) {
    return concreteReferenceContent != null
    && object != null
    && canAddObjectBecauseOfCardinality(concreteReferenceContent)
    && canAddObjectBecauseOfReferencedType(concreteReferenceContent, object);
  }

  //method
  public boolean canRemoveObjects(final IConcreteReferenceContent concreteReferenceContent) {
    return canRemoveObjectsBecauseOfCardinality(concreteReferenceContent);
  }

  //method
  public boolean canRemoveOneObject(final IConcreteReferenceContent concreteReferenceContent) {
    return canRemoveOneObjectBecauseOfCardinality(concreteReferenceContent);
  }

  //method
  private boolean canAddObjectBecauseOfCardinality(final IConcreteReferenceContent concreteReferenceContent) {
    return concreteReferenceContent != null
    && (concreteReferenceContent.getStoredParentField().getCardinality() == Cardinality.TO_MANY
    || concreteReferenceContent.getStoredReferencedObjects().isEmpty());
  }

  //mehtod
  private boolean canAddObjectBecauseOfReferencedType(
    final IConcreteReferenceContent concreteReferenceContent,
    final IAbstractableObject object) {

    if (concreteReferenceContent == null) {
      return false;
    }

    final var field = concreteReferenceContent.getStoredParentField();

    if (field.inheritsFromBaseField()) {
      final var baseField = field.getStoredBaseField();
      final var abstractReferenceContent = (IAbstractReferenceContent) baseField.getStoredContent();
      final var referencedType = abstractReferenceContent.getStoredReferencedType();
      if (!object.isSubTypeOfObject(referencedType)) {
        return false;
      }
    }

    return true;
  }

  //mehod
  private boolean canRemoveObjectsBecauseOfCardinality(final IConcreteReferenceContent concreteReferenceContent) {
    return concreteReferenceContent != null
    && concreteReferenceContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE;
  }

  //method
  private boolean canRemoveOneObjectBecauseOfCardinality(final IConcreteReferenceContent concreteReferenceContent) {
    return concreteReferenceContent != null
    && (concreteReferenceContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE
    || concreteReferenceContent.getStoredReferencedObjects().getCount() > 1);
  }
}
