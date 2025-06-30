package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.SmartFieldExaminer;
import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.system.objectdata.model.BackReference;
import ch.nolix.system.objectdata.model.Reference;

public final class CategorizableReferenceContent
extends AbstractReferenceContent
implements ICategorizableReferenceContent {

  private static final SmartFieldExaminer CATEGORIZABLE_FIELD_EVALUATOR = new SmartFieldExaminer();

  private static final CategorizableReferenceContentValidator ABSTRACT_REFERENCE_CONTENT_VALIDATOR = //
  new CategorizableReferenceContentValidator();

  private final BackReference<SmartField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(SmartField.class, "categorizableReferenceContent");

  private final Reference<SmartObject> referencedType = Reference.forEntity(SmartObject.class);

  public CategorizableReferenceContent() {
    initialize();
  }

  @Override
  public ISmartField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public ISmartObject getStoredReferencedType() {
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
  public ICategorizableReferenceContent setReferencedType(final ISmartObject referenceType) {

    setReferenceTypeIfWillChange(referenceType);

    return this;
  }

  private void setReferenceTypeIfWillChange(final ISmartObject referenceType) {
    if (getStoredReferencedType() != referenceType) {

      ABSTRACT_REFERENCE_CONTENT_VALIDATOR.assertCanSetReferenceType(this, referenceType);

      setReferenceTypeWhenWillChange(referenceType);
    }
  }

  private void setReferenceTypeWhenWillChange(final ISmartObject referenceType) {

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
