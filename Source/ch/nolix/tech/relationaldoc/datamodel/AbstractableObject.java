//package declaration
package ch.nolix.tech.relationaldoc.datamodel;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.programatom.name.PluralPascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.system.objectdatabase.database.MultiReference;
import ch.nolix.system.objectdatabase.database.Value;
import ch.nolix.tech.relationaldoc.datavalidator.AbstractableObjectValidator;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableField;
import ch.nolix.techapi.relationaldocapi.datamodelapi.IAbstractableObject;

//class
public final class AbstractableObject extends Entity implements IAbstractableObject {

  //constant
  public static final String DEFAULT_NAME = PluralPascalCaseCatalogue.FIELD;

  //constant
  public static final boolean DEFAULT_ABSTRACT_FLAG = false;

  //constant
  private static final AbstractableObjectValidator ABSTRACTABLE_OBJECT_VALIDATOR = new AbstractableObjectValidator();

  //attribute
  private final Value<String> name = Value.withInitialValue(DEFAULT_NAME);

  //attribute
  private final Value<Boolean> abstractFlag = Value.withInitialValue(DEFAULT_ABSTRACT_FLAG);

  //multi-attribute
  private final MultiReference<AbstractableObject> directBaseTypes = MultiReference.forEntity(AbstractableObject.class);

  //multi-attribute
  private final MultiReference<AbstractableField> declaredFields = MultiReference.forEntity(AbstractableField.class);

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

    /*
     * TODO: Add realisations of the given field to the sub types of the current
     * AbstractableObject if the given field is abstract.
     */

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
    return declaredFields.getReferencedEntities();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectBaseTypes() {
    return directBaseTypes.getReferencedEntities();
  }

  //method
  @Override
  public IContainer<? extends IAbstractableObject> getStoredDirectSubTypes() {

    //TODO: Create MultiBackReference.
    return getStoredParentDatabase()
      .getStoredTableByEntityType(AbstractableObject.class)
      .getStoredEntities()
      .getStoredSelected(ao -> ao.getStoredDirectBaseTypes().contains(this));
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

    //TODO: Create MultiBackReference.
    return getStoredParentDatabase()
      .getStoredTableByEntityType(AbstractableObject.class)
      .getStoredEntities()
      .getStoredSelected(ao -> ao.getStoredBaseTypes().contains(this));
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
    directBaseTypes.removeEntity((AbstractableObject) directBaseType);
  }

  //method
  @Override
  public void removeNonInheritedField(final IAbstractableField nonInheritedField) {
    //TODO: Implement.
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
  private boolean isSubTypeOfObjectWhenObjectIsNotNull(final IAbstractableObject object) {

    for (final var dbt : getStoredDirectBaseTypes()) {
      if (dbt == object || dbt.getStoredDirectBaseTypes().containsAny(dbt2 -> dbt2.isSubTypeOfObject(object))) {
        return true;
      }
    }

    return false;
  }
}
