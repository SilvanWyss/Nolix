package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datatool.SmartObjectTool;
import ch.nolix.application.relationaldoc.backend.datavalidator.SmartFieldValidator;
import ch.nolix.application.relationaldoc.backend.datavalidator.SmartObjectValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ISmartObject;
import ch.nolix.core.container.linkedlist.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalog;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.MultiBackReference;
import ch.nolix.system.objectdata.model.MultiReference;
import ch.nolix.system.objectdata.model.Value;

public final class SmartObject extends Entity implements ISmartObject {

  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalog.FIELD;

  public static final boolean DEFAULT_ABSTRACT_FLAG = false;

  private static final SmartObjectTool CATEGORIZABLE_OBJECT_TOOL = new SmartObjectTool();

  private static final SmartObjectValidator CATEGORIZABLE_OBJECT_VALIDATOR = new SmartObjectValidator();

  private static final SmartFieldValidator CATEGORIZABLE_FIELD_VALIDATOR = new SmartFieldValidator();

  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);

  private final MultiReference<SmartObject> directBaseTypes = //
  MultiReference.forEntity(SmartObject.class);

  private final MultiBackReference<SmartObject> directSubTypes = //
  MultiBackReference.forBackReferencedEntityTypeAndBaseReference(SmartObject.class, "directBaseTypes");

  private final MultiReference<SmartField> declaredFields = //
  MultiReference.forEntity(SmartField.class);

  public SmartObject() {
    initialize();
  }

  @Override
  public ISmartObject addBaseType(final ISmartObject baseType) {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanAddBaseType(this, baseType);

    directBaseTypes.addEntity(baseType);

    return this;
  }

  @Override
  public ISmartObject addField(final ISmartField field) {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanAddField(this, field);

    declaredFields.addEntity(field);

    addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(field);

    return this;
  }

  @Override
  public String getName() {
    return name.getStoredValue();
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredBaseTypes() {
    return ContainerView
      .forIterable(
        getStoredDirectBaseTypes(),
        getStoredDirectBaseTypes().toMultiples(ISmartObject::getStoredBaseTypes));
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredConcreteSubTypes() {
    return getStoredSubTypes().getStoredSelected(ISmartObject::isConcrete);
  }

  @Override
  public IContainer<? extends ISmartField> getStoredDeclaredFields() {
    return declaredFields.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredDirectBaseTypes() {
    return directBaseTypes.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredDirectSubTypes() {
    return directSubTypes.getAllStoredBackReferencedEntities();
  }

  @Override
  public IContainer<? extends ISmartField> getStoredFields() {
    return ContainerView.forIterable(
      getStoredDeclaredFields().getStoredOthers(ISmartField::inheritsFromBaseField),
      getStoredDirectBaseTypes().toMultiples(ISmartObject::getStoredFields));
  }

  @Override
  public IContainer<? extends ISmartObject> getStoredSubTypes() {
    return CATEGORIZABLE_OBJECT_TOOL.getStoredSubTypesUsingSimplerMethods(this);
  }

  @Override
  public boolean isAbstract() {
    return abstractFlag.getStoredValue();
  }

  @Override
  public boolean isSubTypeOfObject(final ISmartObject object) {
    return object != null
    && isSubTypeOfObjectWhenObjectIsNotNull(object);
  }

  @Override
  public void removeDirectBaseType(final ISmartObject directBaseType) {
    directBaseTypes.removeEntity(directBaseType);
  }

  @Override
  public void removeNonInheritedField(final ISmartField nonInheritedField) {

    CATEGORIZABLE_FIELD_VALIDATOR.assertDoesNotInheritFromAnotherField(nonInheritedField);

    declaredFields.removeEntity(nonInheritedField);

    final var fieldName = nonInheritedField.getName();

    for (final var cst : getStoredConcreteSubTypes()) {
      ((SmartObject) cst).removeFieldByName(fieldName);
    }
  }

  @Override
  public ISmartObject setAsAbstract() {

    abstractFlag.setValue(true);

    return this;
  }

  @Override
  public ISmartObject setAsConcrete() {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanBeSetAsConcrete(this);

    abstractFlag.setValue(false);

    return this;
  }

  @Override
  public ISmartObject setName(final String name) {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanSetName(this, name);

    this.name.setValue(name);

    return this;
  }

  private void addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(final ISmartField field) {
    if (field.isAbstract()) {
      addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(field);
    }
  }

  private void addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(final ISmartField field) {
    for (final var cst : getStoredConcreteSubTypes()) {

      final var realisation = new SmartField().setAsConcrete();

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

  private boolean isSubTypeOfObjectWhenObjectIsNotNull(final ISmartObject object) {

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
