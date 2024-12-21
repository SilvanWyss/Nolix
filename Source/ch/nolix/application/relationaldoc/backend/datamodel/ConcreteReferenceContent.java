package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datavalidator.ConcreteReferenceContentValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableReferenceContent;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IConcreteReferenceContent;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.data.BackReference;
import ch.nolix.system.objectdata.data.MultiReference;

public final class ConcreteReferenceContent extends ReferenceContent implements IConcreteReferenceContent {

  private static final ConcreteReferenceContentValidator CONCRETE_REFERENCE_CONTENT_VALIDATOR = //
  new ConcreteReferenceContentValidator();

  private final BackReference<CategorizableField> parentField = BackReference
    .forEntityAndBackReferencedFieldName(CategorizableField.class, "concreteReferenceContent");

  private final MultiReference<CategorizableObject> referencedObjects = MultiReference
    .forReferencedEntityType(CategorizableObject.class);

  public ConcreteReferenceContent() {
    initialize();
  }

  @Override
  public IConcreteReferenceContent addObject(final ICategorizableObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanAddObject(this, object);

    referencedObjects.addEntity(object);

    return this;
  }

  @Override
  public ICategorizableField getStoredParentField() {
    return parentField.getStoredBackReferencedEntity();
  }

  @Override
  public IContainer<? extends ICategorizableObject> getStoredReferencedObjects() {
    return referencedObjects.getAllStoredReferencedEntities();
  }

  @Override
  public ICategorizableObject getStoredReferencedType() {

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
  public void removeObject(final ICategorizableObject object) {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveOneObject(this);

    referencedObjects.removeEntity(null);
  }

  @Override
  public void removeObjects() {

    CONCRETE_REFERENCE_CONTENT_VALIDATOR.assertCanRemoveObjects(this);

    referencedObjects.clear();
  }
}
