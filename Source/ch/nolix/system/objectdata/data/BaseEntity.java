//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.datavalidator.EntityValidator;
import ch.nolix.system.objectdata.entityflyweight.EntityFlyWeight;
import ch.nolix.system.objectdata.entityflyweight.VoidEntityFlyWeight;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IEntityFlyWeight;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class BaseEntity implements IEntity {

  //constant
  private static final VoidEntityFlyWeight VOID_ENTITY_FLY_WEIGHT = new VoidEntityFlyWeight();

  //constant
  private static final EntityValidator ENTITY_VALIDATOR = new EntityValidator();

  //constant
  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  //attribute
  private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();

  //attribute
  private DatabaseObjectState state = DatabaseObjectState.NEW;

  //attribute
  private IEntityFlyWeight entityFlyweight = VOID_ENTITY_FLY_WEIGHT;

  //optional attribute
  private ITable<? extends IEntity> parentTable;

  //optional attribute
  private String saveStamp;

  //multi-attribute
  private IContainer<Property> properties;

  //method
  @Override
  public final boolean belongsToTable() {
    return (parentTable != null);
  }

  //method
  @Override
  public final void delete() {

    ENTITY_VALIDATOR.assertCanBeDeleted(this);

    /*
     * An Entity must not be referenced on deletion. This is validated. But an
     * Entity must update all back referencing properties to itself on deletion.
     */
    updateBackReferencingPropertiesForDeletion();

    updateStateForDelete();
  }

  //method
  public final void deleteWithoutReferenceCheck() {

    updateBackReferencingPropertiesForDeletion();

    updateStateForDelete();
  }

  //method
  @Override
  public final String getId() {
    return id;
  }

  //method
  @Override
  public final IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  //method
  @Override
  public final ITable<? extends IEntity> getStoredParentTable() {

    ENTITY_VALIDATOR.assertBelongsToTable(this);

    return parentTable;
  }

  //method
  @Override
  public final String getSaveStamp() {

    ENTITY_VALIDATOR.assertHasSaveStamp(this);

    return saveStamp;
  }

  //method
  @Override
  public final DatabaseObjectState getState() {
    return state;
  }

  //method
  @Override
  public final String getShortDescription() {
    return (getClass().getSimpleName() + " " + getId());
  }

  //method
  @Override
  public final boolean hasSaveStamp() {
    return (saveStamp != null);
  }

  //method
  @Override
  public final boolean isClosed() {
    return (getState() == DatabaseObjectState.CLOSED);
  }

  //method
  @Override
  public final boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  //method
  @Override
  public final boolean isLinkedWithRealDatabase() {
    return belongsToTable()
    && getStoredParentTable().isLinkedWithRealDatabase();
  }

  //method
  @Override
  public final boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  //method
  @Override
  public final boolean isReferencedInPersistedData() {

    if (!belongsToTable()) {
      return false;
    }

    return isReferencedInPersistedDataWhenBelongsToTable();
  }

  //method
  @Override
  public final IContainer<? extends IProperty> technicalGetStoredProperties() {
    return getStoredProperties();
  }

  //method
  @Override
  public String toString() {
    return (getClass().getSimpleName() + " (id: " + getId() + ")");
  }

  //method
  protected final void initialize() {
    extractPropertiesIfNotExtracted();
  }

  //method
  protected final void setInsertAction(final Runnable insertAction) {

    setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid();

    entityFlyweight.setInsertAction(insertAction);
  }

  //method
  final void internalClose() {
    state = DatabaseObjectState.CLOSED;
  }

  //method
  final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
    return ((Table<?>) getStoredParentTable()).internalGetRefDataAndSchemaAdapter();
  }

  //method
  final Property internalGetRefPropertyByName(final String name) {
    return getStoredProperties().getStoredFirst(p -> p.hasName(name));
  }

  //method declaration
  abstract IContainer<Property> internalLoadProperties();

  //method
  final void internalNoteInsertIntoDatabase() {

    updateBaseBackReferencesWhenIsInsertedIntoDatabase();

    entityFlyweight.noteInsert();
  }

  //method
  final void internalSetEdited() {
    switch (getState()) {
      case NEW:
        break;
      case LOADED:
        state = DatabaseObjectState.EDITED;
        break;
      case EDITED:
        break;
      case DELETED:
        throw DeletedArgumentException.forArgument(this);
      case CLOSED:
        throw ClosedArgumentException.forArgument(this);
    }
  }

  //method
  final void internalSetId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
  }

  //method
  final void internalSetLoaded() {

    ENTITY_TOOL.assertIsNew(this);

    state = DatabaseObjectState.LOADED;
  }

  //method
  final void internalSetParentTable(final ITable<? extends IEntity> parentTable) {

    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    this.parentTable = parentTable;

    getStoredProperties().forEach(Property::internalSetParentColumnFromParentTable);
  }

  //method
  final void internalSetSaveStamp(final String saveStamp) {

    GlobalValidator.assertThat(saveStamp).thatIsNamed(LowerCaseVariableCatalogue.SAVE_STAMP).isNotNull();

    this.saveStamp = saveStamp;
  }

  //method
  private boolean extractedProperties() {
    return (properties != null);
  }

  //method
  private void extractPropertiesIfNotExtracted() {
    if (!extractedProperties()) {
      extractPropertiesWhenNotExtracted();
    }
  }

  //method
  private void extractPropertiesWhenNotExtracted() {

    properties = internalLoadProperties();

    properties.forEach(p -> p.internalSetParentEntity(this));
  }

  //method
  private IContainer<Property> getStoredProperties() {

    extractPropertiesIfNotExtracted();

    return properties;
  }

  //method
  private boolean isReferencedInPersistedDataWhenBelongsToTable() {

    final var lId = getId();

    return ((Table<?>) getStoredParentTable())
      .internalGetColumnsThatReferencesCurrentTable()
      .containsAny(c -> c.technicalContainsGivenValueInPersistedData(lId));
  }

  //method
  private void setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid() {
    if (entityFlyweight.isVoid()) {
      setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid();
    }
  }

  //method
  private void setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid() {
    entityFlyweight = new EntityFlyWeight();
  }

  //method
  private void updateBackReferenceForDeletion(final BackReference<?> backReference) {
    backReference.internalClear();
    backReference.setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private void updateBackReferencingPropertiesForDeletion() {
    ENTITY_TOOL.getStoredBackReferencingProperties(this).forEach(this::updateBackReferencingPropertyForDeletion);
  }

  //method
  private void updateBackReferencingPropertyForDeletion(
    final IProperty backReferencingProperty) {
    updateBackReferencingPropertyForDeletion((IBaseBackReference<?>) backReferencingProperty);
  }

  //method
  private void updateBackReferencingPropertyForDeletion(
    final IBaseBackReference<?> baseBackReference) {
    switch (baseBackReference.getType()) {
      case BACK_REFERENCE:
        updateBackReferenceForDeletion((BackReference<?>) baseBackReference);
        break;
      case OPTIONAL_BACK_REFERENCE:
        updateOptionalBackReferenceForDeletion((OptionalBackReference<?>) baseBackReference);
        break;
      case MULTI_BACK_REFERENCE:
        /*
         * Does nothing. MultiBackReferences do not need to be updated, because
         * MultiBackReferences do not have redundancies.
         */
        break;
      default:
        throw InvalidArgumentException.forArgument(baseBackReference.getType());
    }
  }

  //method
  private void updateBaseBackReferencesWhenIsInsertedIntoDatabase() {
    getStoredProperties().forEach(Property::internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase);
  }

  //method
  private void updateOptionalBackReferenceForDeletion(final OptionalBackReference<?> optionalBackReference) {
    optionalBackReference.internalClear();
    optionalBackReference.setAsEditedAndRunProbableUpdateAction();
  }

  //method
  private void updateStateForDelete() {
    state = DatabaseObjectState.DELETED;
  }
}
