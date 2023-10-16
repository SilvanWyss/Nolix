//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
//own imports
import ch.nolix.coreapi.datamodelapi.constraintapi.IConstraint;
import ch.nolix.system.objectdatabase.database.BackReference;
import ch.nolix.system.objectdatabase.database.Reference;
import ch.nolix.tech.relationaldoc.dataevaluator.AbstractableFieldEvaluator;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractReferenceContentValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractReferenceContent;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IConcreteReferenceContent;

//class
public final class AbstractReferenceContent extends ReferenceContent implements IAbstractReferenceContent {

  //constant
  private static final AbstractableFieldEvaluator ABSTRACTABLE_FIELD_EVALUATOR = new AbstractableFieldEvaluator();

  //constant
  private static final AbstractReferenceContentValidator ABSTRACT_REFERENCE_CONTENT_VALIDATOR = //
      new AbstractReferenceContentValidator();

  //attribute
  private final BackReference<AbstractableField> parentField = BackReference
      .forEntityAndBackReferencedPropertyName(AbstractableField.class, "abstractReferenceContent");

  //attribute
  private final Reference<AbstractableObject> referencedType = Reference.forEntity(AbstractableObject.class);

  //constructor
  public AbstractReferenceContent() {
    initialize();
  }

  //method
  @Override
  public IAbstractReferenceContent addConstraint(final IConstraint<IAbstractableObject> constraint) {

    //TODO: Implement.
    return this;
  }

  @Override
  public IContainer<? extends IConstraint<IAbstractableObject>> getConstraints() {

    //TODO: Implement.
    return new ImmutableList<>();
  }

  //method
  @Override
  public IAbstractableField getStoredParentField() {
    return parentField.getBackReferencedEntity();
  }

  //method
  @Override
  public IAbstractableObject getStoredReferencedType() {
    return referencedType.getReferencedEntity();
  }

  //method
  @Override
  public boolean isAbstract() {
    return true;
  }

  //method
  @Override
  public boolean isEmpty() {
    return true;
  }

  //method
  @Override
  public void removeConstraint(final IConstraint<IAbstractableObject> constraint) {
    //TODO: Implement.
  }

  //method
  @Override
  public void removeConstraints() {
    //TODO: Implement.
  }

  //method
  @Override
  public IAbstractReferenceContent setReferencedType(final IAbstractableObject referenceType) {

    setReferenceTypeIfWillChange(referenceType);

    return this;
  }

  //method
  private void setReferenceTypeIfWillChange(final IAbstractableObject referenceType) {
    if (getStoredReferencedType() != referenceType) {

      ABSTRACT_REFERENCE_CONTENT_VALIDATOR.assertCanSetReferenceType(this, referenceType);

      setReferenceTypeWhenWillChange(referenceType);
    }
  }

  //method
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

    //TODO: Avoid casting here.
    this.referencedType.setEntity((AbstractableObject) referenceType);
  }
}
