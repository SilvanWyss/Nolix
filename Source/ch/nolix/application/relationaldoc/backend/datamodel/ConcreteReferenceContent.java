package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.ConcreteReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.model.BackReference;
import ch.nolix.system.objectdata.model.MultiReference;

public final class ConcreteReferenceContent extends AbstractReferenceContent implements IConcreteReferenceContent {

  private static final ConcreteReferenceContentValidator CONCRETE_REFERENCE_CONTENT_VALIDATOR = //
  new ConcreteReferenceContentValidator();

  private final BackReference<SmartField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(SmartField.class, "concreteReferenceContent");

  private final MultiReference<SmartObject> referencedObjects = //
  MultiReference.forEntity(SmartObject.class);

  public ConcreteReferenceContent() {
    initialize();
  }

  @Override
  public IConcreteReferenceContent addObject(final ISmartObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanAddObject(this, object);

    referencedObjects.addEntity(object);

    return this;
  }

  @Override
  public ISmartField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredReferencedObjects() {
    return referencedObjects.getAllStoredReferencedEntities();
  }

  @Override
  public ISmartObject getStoredReferencedType() {

    final var baseField = getStoredParentField().getStoredBaseField();

    final var abstractReferenceContent = (ICategorizableReferenceContent) baseField.getStoredContent();

    return abstractReferenceContent.getStoredReferencedType();
  }

  @Override
  public boolean isAbstract() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return referencedObjects.isEmpty();
  }

  @Override
  public void removeObject(final ISmartObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveOneObject(this);

    referencedObjects.removeEntity(null);
  }

  @Override
  public void removeObjects() {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveObjects(this);

    referencedObjects.clear();
  }
}
