package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datatool.AbstractableObjectTool;
import ch.nolix.application.relationaldoc.backend.datavalidator.AbstractableFieldValidator;
import ch.nolix.application.relationaldoc.backend.datavalidator.AbstractableObjectValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.IAbstractableObject;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.MultiBackReference;
import ch.nolix.system.objectdata.data.MultiReference;
import ch.nolix.system.objectdata.data.Value;

public final class AbstractableObject extends Entity implements IAbstractableObject {

  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalogue.FIELD;

  public static final boolean DEFAULT_ABSTRACT_FLAG = false;

  private static final AbstractableObjectTool ABSTRACTABLE_OBJECT_TOOL = new AbstractableObjectTool();

  private static final AbstractableObjectValidator ABSTRACTABLE_OBJECT_VALIDATOR = new AbstractableObjectValidator();

  private static final AbstractableFieldValidator ABSTRACTABLE_FIELD_VALIDATOR = new AbstractableFieldValidator();

  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);

  private final MultiReference<AbstractableObject> directBaseTypes = //
  MultiReference.forReferencedEntityType(AbstractableObject.class);

  private final MultiBackReference<AbstractableObject> directSubTypes = //
  MultiBackReference.forBackReferencedEntityTypeAndBaseReference(AbstractableObject.class, "directBaseTypes");

  private final MultiReference<AbstractableField> declaredFields = //
  MultiReference.forReferencedEntityType(AbstractableField.class);

  public AbstractableObject() {
    initialize();
  }

  @Override
  public IAbstractableObject addBaseType(final IAbstractableObject baseType) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddBaseType(this, baseType);

    directBaseTypes.addEntity(baseType);

    return this;
  }

  @Override
  public IAbstractableObject addField(final IAbstractableField field) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddField(this, field);

    declaredFields.addEntity(field);

    addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(field);

    return this;
  }

  @Override
  public String getName() {
    return name.getStoredValue();
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredBaseTypes() {
    return ContainerView
      .forIterable(
        getStoredDirectBaseTypes(),
        getStoredDirectBaseTypes().toMultiple(IAbstractableObject::getStoredBaseTypes));
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredConcreteSubTypes() {
    return getStoredSubTypes().getStoredSelected(IAbstractableObject::isConcrete);
  }

  @Override
  public IContainer<? extends IAbstractableField> getStoredDeclaredFields() {
    return declaredFields.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes() {
    return directBaseTypes.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectSubTypes() {
    return directSubTypes.getAllStoredBackReferencedEntities();
  }

  @Override
  public IContainer<? extends IAbstractableField> getStoredFields() {
    return ContainerView.forIterable(
      getStoredDeclaredFields().getStoredOthers(IAbstractableField::inheritsFromBaseField),
      getStoredDirectBaseTypes().toMultiple(IAbstractableObject::getStoredFields));
  }

  @Override
  public IContainer<? extends IAbstractableObject> getStoredSubTypes() {
    return ABSTRACTABLE_OBJECT_TOOL.getStoredSubTypesUsingSimplerMethods(this);
  }

  @Override
  public boolean isAbstract() {
    return abstractFlag.getStoredValue();
  }

  @Override
  public boolean isSubTypeOfObject(final IAbstractableObject object) {
    return object != null
    && isSubTypeOfObjectWhenObjectIsNotNull(object);
  }

  @Override
  public void removeDirectBaseType(final IAbstractableObject directBaseType) {
    directBaseTypes.removeEntity(directBaseType);
  }

  @Override
  public void removeNonInheritedField(final IAbstractableField nonInheritedField) {

    ABSTRACTABLE_FIELD_VALIDATOR.assertDoesNotInheritFromAnotherField(nonInheritedField);

    declaredFields.removeEntity(nonInheritedField);

    final var fieldName = nonInheritedField.getName();

    for (final var cst : getStoredConcreteSubTypes()) {
      ((AbstractableObject) cst).removeFieldByName(fieldName);
    }
  }

  @Override
  public IAbstractableObject setAsAbstract() {

    abstractFlag.setValue(true);

    return this;
  }

  @Override
  public IAbstractableObject setAsConcrete() {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanBeSetAsConcrete(this);

    abstractFlag.setValue(false);

    return this;
  }

  @Override
  public IAbstractableObject setName(final String name) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanSetName(this, name);

    this.name.setValue(name);

    return this;
  }

  private void addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(final IAbstractableField field) {
    if (field.isAbstract()) {
      addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(field);
    }
  }

  private void addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(final IAbstractableField field) {
    for (final var cst : getStoredConcreteSubTypes()) {

      final var realisation = new AbstractableField().setAsConcrete();

      switch (field.getContentType()) { //NOSONAR: Use switch statement to restrict criteria to content type.
        case VALUE:
          realisation.setForValues();
          break;
        case REFERENCE:
          realisation.setForReferences();
          break;
      }

      cst.addField(realisation);
    }
  }

  private boolean isSubTypeOfObjectWhenObjectIsNotNull(final IAbstractableObject object) {

    for (final var dbt : getStoredDirectBaseTypes()) {
      if (dbt == object || dbt.getStoredDirectBaseTypes().containsAny(dbt2 -> dbt2.isSubTypeOfObject(object))) {
        return true;
      }
    }

    return false;
  }

  private void removeFieldByName(final String name) {
    declaredFields.removeFirstEntity(f -> f.hasName(name));
  }
}
