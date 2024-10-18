package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.Reference;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractReferenceContentValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

public final class AbstractReferenceContent extends ReferenceContent implements IAbstractReferenceContent {

  private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();

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
