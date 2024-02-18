//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.PluralPascalCaseVariableCatalogue;
import ch.nolix.system.objectdata.data.Entity;
import ch.nolix.system.objectdata.data.MultiBackReference;
import ch.nolix.system.objectdata.data.MultiReference;
import ch.nolix.system.objectdata.data.Value;
import ch.nolix.tech.relationaldoc.datatool.AbstractableObjectTool;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableFieldValidator;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableObjectValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObject extends Entity implements IAbstractableObject {

  //constant
  public static final String DEFAULT_NAME = PluralPascalCaseVariableCatalogue.FIELD;

  //constant
  public static final boolean DEFAULT_ABSTRACT_FLAG = false;

  //constant
  private static final AbstractableObjectTool ABSTRACTABLE_OBJECT_TOOL = new AbstractableObjectTool();

  //constant
  private static final AbstractableObjectValidator ABSTRACTABLE_OBJECT_VALIDATOR = new AbstractableObjectValidator();

  //constant
  private static final AbstractableFieldValidator ABSTRACTABLE_FIELD_VALIDATOR = new AbstractableFieldValidator();

  //attribute
  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  //attribute
  private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);

  //multi-attribute
  private final MultiReference<AbstractableObject> directBaseTypes = MultiReference.forReferencedEntityType(AbstractableObject.class);

  //multi-attribute
  private final MultiBackReference<AbstractableObject> directSubTypes = //
  MultiBackReference.forBackReferencedEntityTypeAndBaseReference(AbstractableObject.class, "directBaseTypes");

  //multi-attribute
  private final MultiReference<AbstractableField> declaredFields = MultiReference.forReferencedEntityType(AbstractableField.class);

  //constructor
  public AbstractableObject() {
    initialize();
  }

  //method
  @Override
  public IAbstractableObject addBaseType(final IAbstractableObject baseType) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddBaseType(this, baseType);

    directBaseTypes.addEntity(baseType);

    return this;
  }

  //method
  @Override
  public IAbstractableObject addField(final IAbstractableField field) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanAddField(this, field);

    declaredFields.addEntity(field);

    addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(field);

    return this;
  }

  //method
  @Override
  public String getName() {
    return name.getStoredValue();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredBaseTypes() {
    return ReadContainer
      .forIterable(
        getStoredDirectBaseTypes(),
        getStoredDirectBaseTypes().toFromGroups(IAbstractableObject::getStoredBaseTypes));
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredConcreteSubTypes() {
    return getStoredSubTypes().getStoredSelected(IAbstractableObject::isConcrete);
  }

  //method
  @Override
  public IContainer<? extends IAbstractableField> getStoredDeclaredFields() {
    return declaredFields.getAllStoredReferencedEntities();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes() {
    return directBaseTypes.getAllStoredReferencedEntities();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectSubTypes() {
    return directSubTypes.getStoredBackReferencedEntities();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableField> getStoredFields() {
    return ReadContainer.forIterable(
      getStoredDeclaredFields().getStoredOther(IAbstractableField::inheritsFromBaseField),
      getStoredDirectBaseTypes().toFromGroups(IAbstractableObject::getStoredFields));
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredSubTypes() {
    return ABSTRACTABLE_OBJECT_TOOL.getStoredSubTypesUsingSimplerMethods(this);
  }

  //method
  @Override
  public boolean isAbstract() {
    return abstractFlag.getStoredValue();
  }

  //method
  @Override
  public boolean isSubTypeOfObject(final IAbstractableObject object) {
    return object != null
    && isSubTypeOfObjectWhenObjectIsNotNull(object);
  }

  //method
  @Override
  public void removeDirectBaseType(final IAbstractableObject directBaseType) {
    directBaseTypes.removeEntity(directBaseType);
  }

  //method
  @Override
  public void removeNonInheritedField(final IAbstractableField nonInheritedField) {

    ABSTRACTABLE_FIELD_VALIDATOR.assertDoesNotInheritFromAnotherField(nonInheritedField);

    declaredFields.removeEntity(nonInheritedField);

    final var fieldName = nonInheritedField.getName();

    for (final var cst : getStoredConcreteSubTypes()) {
      ((AbstractableObject) cst).removeFieldByName(fieldName);
    }
  }

  //method
  @Override
  public IAbstractableObject setAsAbstract() {

    abstractFlag.setValue(true);

    return this;
  }

  //method
  @Override
  public IAbstractableObject setAsConcrete() {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanBeSetAsConcrete(this);

    abstractFlag.setValue(false);

    return this;
  }

  //method
  @Override
  public IAbstractableObject setName(final String name) {

    ABSTRACTABLE_OBJECT_VALIDATOR.assertCanSetName(this, name);

    this.name.setValue(name);

    return this;
  }

  //method
  private void addRealisationOfFieldToAllConcreteSubTypesIfFieldIsAbstract(final IAbstractableField field) {
    if (field.isAbstract()) {
      addRealisationOfFieldToAllConcreteSubTypesWhenFieldIsAbstract(field);
    }
  }

  //method
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

  //method
  private boolean isSubTypeOfObjectWhenObjectIsNotNull(final IAbstractableObject object) {

    for (final var dbt : getStoredDirectBaseTypes()) {
      if (dbt == object || dbt.getStoredDirectBaseTypes().containsAny(dbt2 -> dbt2.isSubTypeOfObject(object))) {
        return true;
      }
    }

    return false;
  }

  //method
  private void removeFieldByName(final String name) {
    declaredFields.removeFirstEntity(f -> f.hasName(name));
  }
}
