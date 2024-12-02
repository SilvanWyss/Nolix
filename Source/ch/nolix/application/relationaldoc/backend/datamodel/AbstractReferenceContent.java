package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.dataeexaminer.AbstractableFieldExaminer;
import ch.nolix.application.relationaldoc.backend.datavalidator.AbstractReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Reference;

public final class AbstractReferenceContent extends ReferenceContent implements IAbstractReferenceContent {

  private static final AbstractableFieldExaminer ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldExaminer();

  private static final AbstractReferenceContentValidator ABSTRACT_REFERENCE_CONTENT_VALIDATOR = //
  new AbstractReferenceContentValidator();

  private final BackReference<AbstractableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(AbstractableField.class, "abstractReferenceContent");

  private final Reference<AbstractableObject> referencedType = Reference.forEntity(AbstractableObject.class);

  public AbstractReferenceContent() {
    initialize();
  }

  @Override
  public IAbstractableField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public IAbstractableObject getStoredReferencedType() {
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
  public IAbstractReferenceContent setReferencedType(final IAbstractableObject referenceType) {

    setReferenceTypeIfWillChange(referenceType);

    return this;
  }

  private void setReferenceTypeIfWillChange(final IAbstractableObject referenceType) {
    if (getStoredReferencedType() != referenceType) {

      ABSTRACT_REFERENCE_CONTENT_VALIDATOR.assertCanSetReferenceType(this, referenceType);

      setReferenceTypeWhenWillChange(referenceType);
    }
  }

  private void setReferenceTypeWhenWillChange(final IAbstractableObject referenceType) {

    final var realisingFields = ABSTRACTABLE_FIELD_EVALUATOR.getStoredRealisingFields(getStoredParentField());

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
