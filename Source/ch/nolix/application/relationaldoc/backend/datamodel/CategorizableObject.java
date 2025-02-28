package ch.nolix.application.relationaldoc.backend.datamodel;

import ch.nolix.application.relationaldoc.backend.datatool.CategorizableObjectTool;
import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableFieldValidator;
import ch.nolix.application.relationaldoc.backend.datavalidator.CategorizableObjectValidator;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableField;
import ch.nolix.applicationapi.relationaldocapi.backendapi.datamodelapi.ICategorizableObject;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalog;
import ch.nolix.system.objectdata.model.Entity;
import ch.nolix.system.objectdata.model.MultiBackReference;
import ch.nolix.system.objectdata.model.MultiReference;
import ch.nolix.system.objectdata.model.Value;

public final class CategorizableObject extends Entity implements ICategorizableObject {

  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalog.FIELD;

  public static final boolean DEFAULT_ABSTRACT_FLAG = false;

  private static final CategorizableObjectTool CATEGORIZABLE_OBJECT_TOOL = new CategorizableObjectTool();

  private static final CategorizableObjectValidator CATEGORIZABLE_OBJECT_VALIDATOR = new CategorizableObjectValidator();

  private static final CategorizableFieldValidator CATEGORIZABLE_FIELD_VALIDATOR = new CategorizableFieldValidator();

  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);

  private final MultiReference<CategorizableObject> directBaseTypes = //
  MultiReference.forEntity(CategorizableObject.class);

  private final MultiBackReference<CategorizableObject> directSubTypes = //
  MultiBackReference.forBackReferencedEntityTypeAndBaseReference(CategorizableObject.class, "directBaseTypes");

  private final MultiReference<CategorizableField> declaredFields = //
  MultiReference.forEntity(CategorizableField.class);

  public CategorizableObject() {
    initialize();
  }

  @Override
  public ICategorizableObject addBaseType(final ICategorizableObject baseType) {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanAddBaseType(this, baseType);

    directBaseTypes.addEntity(baseType);

    return this;
  }

  @Override
  public ICategorizableObject addField(final ICategorizableField field) {

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
  public IContainer<? extends ICategorizableObject> getStoredBaseTypes() {
    return ContainerView
      .forIterable(
        getStoredDirectBaseTypes(),
        getStoredDirectBaseTypes().toMultiple(ICategorizableObject::getStoredBaseTypes));
  }

  @Override
  public IContainer<? extends ICategorizableObject> getStoredConcreteSubTypes() {
    return getStoredSubTypes().getStoredSelected(ICategorizableObject::isConcrete);
  }

  @Override
  public IContainer<? extends ICategorizableField> getStoredDeclaredFields() {
    return declaredFields.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends ICategorizableObject> getStoredDirectBaseTypes() {
    return directBaseTypes.getAllStoredReferencedEntities();
  }

  @Override
  public IContainer<? extends ICategorizableObject> getStoredDirectSubTypes() {
    return directSubTypes.getAllStoredBackReferencedEntities();
  }

  @Override
  public IContainer<? extends ICategorizableField> getStoredFields() {
    return ContainerView.forIterable(
      getStoredDeclaredFields().getStoredOthers(ICategorizableField::inheritsFromBaseField),
      getStoredDirectBaseTypes().toMultiple(ICategorizableObject::getStoredFields));
  }

  @Override
  public IContainer<? extends ICategorizableObject> getStoredSubTypes() {
    return CATEGORIZABLE_OBJECT_TOOL.getStoredSubTypesUsingSimplerMethods(this);
  }

  @Override
  public boolean isAbstract() {
    return abstractFlag.getStoredValue();
  }

  @Override
  public boolean isSubTypeOfObject(final ICategorizableObject object) {
    return object != null
    && isSubTypeOfObjectWhenObjectIsNotNull(object);
  }

  @Override
  public void removeDirectBaseType(final ICategorizableObject directBaseType) {
    directBaseTypes.removeEntity(directBaseType);
  }

  @Override
  public void removeNonInheritedField(final ICategorizableField nonInheritedField) {

    CATEGORIZABLE_FIELD_VALIDATOR.assertDoesNotInheritFromAnotherField(nonInheritedField);

    declaredFields.removeEntity(nonInheritedField);

    final var fieldName = nonInheritedField.getName();

    for (final var cst : getStoredConcreteSubTypes()) {
      ((CategorizableObject) cst).removeFieldByName(fieldName);
    }
  }

  @Override
  public ICategorizableObject setAsAbstract() {

    abstractFlag.setValue(true);

    return this;
  }

  @Override
  public ICategorizableObject setAsConcrete() {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanBeSetAsConcrete(this);

    abstractFlag.setValue(false);

    return this;
  }

  @Override
  public ICategorizableObject setName(final String name) {

    CATEGORIZABLE_OBJECT_VALIDATOR.assertCanSetName(this, name);

    this.name.setValue(name);

    return this;
  }

  private void addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(final ICategorizableField field) {
    if (field.isAbstract()) {
      addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(field);
    }
  }

  private void addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(final ICategorizableField field) {
    for (final var cst : getStoredConcreteSubTypes()) {

      final var realisation = new CategorizableField().setAsConcrete();

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

  private boolean isSubTypeOfObjectWhenObjectIsNotNull(final ICategorizableObject object) {

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
