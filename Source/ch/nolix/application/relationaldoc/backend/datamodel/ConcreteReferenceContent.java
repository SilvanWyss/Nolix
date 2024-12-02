package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.ConcreteReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.MultiReference;

public final class ConcreteReferenceContent extends ReferenceContent implements IConcreteReferenceContent {

  private static final ConcreteReferenceContentValidator CONCRETE_REFERENCE_CONTENT_VALIDATOR = //
  new ConcreteReferenceContentValidator();

  private final BackReference<AbstractableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(AbstractableField.class, "concreteReferenceContent");

  private final MultiReference<AbstractableObject> referencedObjects = MultiReference
    .forReferencedEntityType(AbstractableObject.class);

  public ConcreteReferenceContent() {
    initialize();
  }

  @Override
  public IConcreteReferenceContent addObject(final IAbstractableObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanAddObject(this, object);

    referencedObjects.addEntity(object);

    return this;
  }

  @Override
  public IAbstractableField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredReferencedObjects() {
    return referencedObjects.getAllStoredReferencedEntities();
  }

  @Override
  public IAbstractableObject getStoredReferencedType() {

    final var baseField = getStoredParentField().getStoredBaseField();

    final var abstractReferenceContent = (IAbstractReferenceContent) baseField.getStoredContent();

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
  public void removeObject(final IAbstractableObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveOneObject(this);

    referencedObjects.removeEntity(null);
  }

  @Override
  public void removeObjects() {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveObjects(this);

    referencedObjects.clear();
  }
}
