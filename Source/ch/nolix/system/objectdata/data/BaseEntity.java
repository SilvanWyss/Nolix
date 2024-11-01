package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.databaseobject.databaseobjectvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.system.objectdata.datavalidator.EntityValidator;
import ch.nolix.system.objectdata.entityflyweight.EntityFlyWeight;
import ch.nolix.system.objectdata.entityflyweight.VoidEntityFlyWeight;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IEntityFlyWeight;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public abstract class BaseEntity implements IEntity {

  private static final VoidEntityFlyWeight VOID_ENTITY_FLY_WEIGHT = new VoidEntityFlyWeight();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final EntityValidator ENTITY_VALIDATOR = new EntityValidator();

  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();

  private DatabaseObjectState state = DatabaseObjectState.NEW;

  private IEntityFlyWeight entityFlyweight = VOID_ENTITY_FLY_WEIGHT;

  private ITable<? extends IEntity> parentTable;

  private String saveStamp;

  private IContainer<Field> fields;

  @Override
  public final boolean belongsToTable() {
    return (parentTable != null);
  }

  @Override
  public final void delete() {

    ENTITY_VALIDATOR.assertCanBeDeleted(this);

    /*
     * An Entity must not be referenced on deletion. This is validated. But an
     * Entity must update all back referencing fields to itself on deletion.
     */
    updateBackReferencingFieldsForDeletion();

    updateStateForDelete();
  }

  public final void deleteWithoutReferenceCheck() {

    updateBackReferencingFieldsForDeletion();

    updateStateForDelete();
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public final IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public final ITable<? extends IEntity> getStoredParentTable() {

    ENTITY_VALIDATOR.assertBelongsToTable(this);

    return parentTable;
  }

  @Override
  public final String getSaveStamp() {

    ENTITY_VALIDATOR.assertHasSaveStamp(this);

    return saveStamp;
  }

  @Override
  public final DatabaseObjectState getState() {
    return state;
  }

  @Override
  public final String getShortDescription() {
    return (getClass().getSimpleName() + " " + getId());
  }

  @Override
  public final boolean hasSaveStamp() {
    return (saveStamp != null);
  }

  @Override
  public final IContainer<? extends IField> internalGetStoredFields() {
    return getStoredFields();
  }

  @Override
  public final boolean isClosed() {
    return (getState() == DatabaseObjectState.CLOSED);
  }

  @Override
  public final boolean isDeleted() {
    return (getState() == DatabaseObjectState.DELETED);
  }

  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public final boolean isLinkedWithRealDatabase() {
    return belongsToTable()
    && getStoredParentTable().isLinkedWithRealDatabase();
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public final boolean isNew() {
    return (getState() == DatabaseObjectState.NEW);
  }

  @Override
  public final boolean isReferencedInPersistedData() {
    return //
    belongsToTable()
    && isReferencedInPersistedDataWhenBelongsToTable();
  }

  @Override
  public final boolean isReferencedInPersistedDataIgnoringGivenEntities(final IContainer<String> entitiesToIgnoreIds) {
    return //
    belongsToTable()
    && isReferencedInPersistedDataIgnoringGivenEntitiesWhenBelongsToTable(entitiesToIgnoreIds);
  }

  @Override
  public String toString() {
    return (getClass().getSimpleName() + " (id: " + getId() + ")");
  }

  protected final void initialize() {
    extractFieldsIfNotExtracted();
  }

  protected final void setInsertAction(final Runnable insertAction) {

    setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid();

    entityFlyweight.setInsertAction(insertAction);
  }

  final void internalClose() {
    state = DatabaseObjectState.CLOSED;
  }

  final IDataAndSchemaAdapter internalGetStoredDataAndSchemaAdapter() {
    return ((Table<?>) getStoredParentTable()).internalGetStoredDataAndSchemaAdapter();
  }

  final Field internalGetStoredFieldByName(final String name) {
    return getStoredFields().getStoredFirst(p -> p.hasName(name));
  }

  abstract IContainer<Field> internalLoadFields();

  final void internalNoteInsertIntoDatabase() {

    updateBaseBackReferencesWhenIsInsertedIntoDatabase();

    entityFlyweight.noteInsert();
  }

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

  final void internalSetId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();

    this.id = id;
  }

  final void internalSetLoaded() {

    DATABASE_OBJECT_VALIDATOR.assertIsNew(this);

    state = DatabaseObjectState.LOADED;
  }

  final void internalSetParentTable(final ITable<? extends IEntity> parentTable) {

    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    this.parentTable = parentTable;

    getStoredFields().forEach(Field::internalSetParentColumnFromParentTable);
  }

  final void internalSetSaveStamp(final String saveStamp) {

    GlobalValidator.assertThat(saveStamp).thatIsNamed(LowerCaseVariableCatalogue.SAVE_STAMP).isNotNull();

    this.saveStamp = saveStamp;
  }

  private boolean extractedFields() {
    return (fields != null);
  }

  private void extractFieldsIfNotExtracted() {
    if (!extractedFields()) {
      extractFieldsWhenNotExtracted();
    }
  }

  private void extractFieldsWhenNotExtracted() {

    fields = internalLoadFields();

    fields.forEach(p -> p.internalSetParentEntity(this));
  }

  private IContainer<Field> getStoredFields() {

    extractFieldsIfNotExtracted();

    return fields;
  }

  private boolean isReferencedInPersistedDataWhenBelongsToTable() {

    final var localId = getId();

    return //
    ((Table<?>) getStoredParentTable())
      .internalGetColumnsThatReferencesCurrentTable()
      .containsAny(c -> c.internalContainsGivenValueInPersistedData(localId));
  }

  private boolean isReferencedInPersistedDataIgnoringGivenEntitiesWhenBelongsToTable(
    final IContainer<String> entitiesToIgnoreIds) {

    final var localId = getId();

    return //
    ((Table<?>) getStoredParentTable())
      .internalGetColumnsThatReferencesCurrentTable()
      .containsAny(c -> c.internalContainsGivenValueInPersistedDataIgnoringGivenEntities(localId, entitiesToIgnoreIds));
  }

  private void setEffectiveEntityFlyWeightIfEntityFlyWeightIsVoid() {
    if (entityFlyweight.isVoid()) {
      setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid();
    }
  }

  private void setEffectiveEntityFlyWeightWhenEntityFlyWeightIsVoid() {
    entityFlyweight = new EntityFlyWeight();
  }

  private void updateBackReferenceForDeletion(final BackReference<?> backReference) {
    backReference.internalClear();
    backReference.setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateBackReferencingFieldsForDeletion() {
    ENTITY_TOOL.getStoredBaseBackReferences(this).forEach(this::updateBackReferencingFieldsForDeletion);
  }

  private void updateBackReferencingFieldsForDeletion(
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

  private void updateBaseBackReferencesWhenIsInsertedIntoDatabase() {
    getStoredFields().forEach(Field::internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase);
  }

  private void updateOptionalBackReferenceForDeletion(final OptionalBackReference<?> optionalBackReference) {
    optionalBackReference.internalClear();
    optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateStateForDelete() {
    state = DatabaseObjectState.DELETED;
  }
}
