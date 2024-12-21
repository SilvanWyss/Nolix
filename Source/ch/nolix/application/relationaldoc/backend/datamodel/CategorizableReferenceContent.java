package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.CategorizableFieldExaminer;
import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Reference;

public final class CategorizableReferenceContent extends ReferenceContent implements ICategorizableReferenceContent {

  private static final CategorizableFieldExaminer CATEGORIZABLE_FIELD_EVALUATOR = new CategorizableFieldExaminer();

  private static final CategorizableReferenceContentValidator ABSTRACT_REFERENCE_CONTENT_VALIDATOR = //
  new CategorizableReferenceContentValidator();

  private final BackReference<CategorizableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(CategorizableField.class, "abstractReferenceContent");

  private final Reference<CategorizableObject> referencedType = Reference.forEntity(CategorizableObject.class);

  public CategorizableReferenceContent() {
    initialize();
  }

  @Override
  public ICategorizableField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public ICategorizableObject getStoredReferencedType() {
    return referencedType.getStoredReferencedEntity();
  }

  @Override
  public boolean isAbstract() {
    return true;
  }

  @Override
  public boolean isEmpty() {
    return true;
  }

  @Override
  public ICategorizableReferenceContent setReferencedType(final ICategorizableObject referenceType) {

    setReferenceTypeIfWillChange(referenceType);

    return this;
  }

  private void setReferenceTypeIfWillChange(final ICategorizableObject referenceType) {
    if (getStoredReferencedType() != referenceType) {

      ABSTRACT_REFERENCE_CONTENT_VALIDATOR.assertCanSetReferenceType(this, referenceType);

      setReferenceTypeWhenWillChange(referenceType);
    }
  }

  private void setReferenceTypeWhenWillChange(final ICategorizableObject referenceType) {

    final var realisingFields = CATEGORIZABLE_FIELD_EVALUATOR.getStoredRealisingFields(getStoredParentField());

    for (final var rf : realisingFields) {

      final var concreteReferenceContent = (IConcreteReferenceContent) rf.getStoredContent();
      final var referencedObjects = concreteReferenceContent.getStoredReferencedObjects();

      for (final var ro : referencedObjects) {
        if (!ro.isSubTypeOfObject(referenceType)) {
          concreteReferenceContent.removeObject(ro);
        }
      }
    }

    this.referencedType.setEntity(referenceType);
  }
}
