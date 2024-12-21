package ch.nolix.application.relationaldoc.backend.dataeexaminer;

import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.coreapi.datamodelapi.cardinalityapi.Cardinality;

public final class ConcreteReferenceContentExaminer {

  public boolean canAddObject(
    final IConcreteReferenceContent concreteReferenceContent,
    final ICategorizableObject object) {
    return concreteReferenceContent != null
    && object != null
    && canAddObjectBecauseOfCardinality(concreteReferenceContent)
    && canAddObjectBecauseOfReferencedType(concreteReferenceContent, object);
  }

  public boolean canRemoveObjects(final IConcreteReferenceContent concreteReferenceContent) {
    return canRemoveObjectsBecauseOfCardinality(concreteReferenceContent);
  }

  public boolean canRemoveOneObject(final IConcreteReferenceContent concreteReferenceContent) {
    return canRemoveOneObjectBecauseOfCardinality(concreteReferenceContent);
  }

  private boolean canAddObjectBecauseOfCardinality(final IConcreteReferenceContent concreteReferenceContent) {
    return concreteReferenceContent != null
    && (concreteReferenceContent.getStoredParentField().getCardinality() == Cardinality.TO_MANY
    || concreteReferenceContent.getStoredReferencedObjects().isEmpty());
  }

  //mehtod
  private boolean canAddObjectBecauseOfReferencedType(
    final IConcreteReferenceContent concreteReferenceContent,
    final ICategorizableObject object) {

    if (concreteReferenceContent == null) {
      return false;
    }

    final var field = concreteReferenceContent.getStoredParentField();

    if (field.inheritsFromBaseField()) {
      final var baseField = field.getStoredBaseField();
      final var abstractReferenceContent = (ICategorizableReferenceContent) baseField.getStoredContent();
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

  private boolean canRemoveOneObjectBecauseOfCardinality(final IConcreteReferenceContent concreteReferenceContent) {
    return concreteReferenceContent != null
    && (concreteReferenceContent.getStoredParentField().getCardinality() != Cardinality.TO_ONE
    || concreteReferenceContent.getStoredReferencedObjects().getCount() > 1);
  }
}
