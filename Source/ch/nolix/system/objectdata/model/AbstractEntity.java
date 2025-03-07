package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programstructure.data.GlobalIdCreator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.datavalidator.EntityValidator;
import ch.nolix.system.objectdata.modelflyweight.EntityFlyWeight;
import ch.nolix.system.objectdata.modelflyweight.VoidEntityFlyWeight;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractBackReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IDatabase;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelflyweightapi.IEntityFlyWeight;
import ch.nolix.systemapi.objectdataapi.modelsearcherapi.IEntitySearcher;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public abstract class AbstractEntity implements IEntity {

  private static final VoidEntityFlyWeight VOID_ENTITY_FLY_WEIGHT = new VoidEntityFlyWeight();

  private static final DatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final EntityValidator ENTITY_VALIDATOR = new EntityValidator();

  private String id = GlobalIdCreator.createIdOf10HexadecimalCharacters();

  private boolean gotExternalId;

  private DatabaseObjectState state = DatabaseObjectState.NEW;

  private IEntityFlyWeight entityFlyweight = VOID_ENTITY_FLY_WEIGHT;

  private ITable<? extends IEntity> parentTable;

  private String saveStamp;

  private IContainer<AbstractField> fields;

  @Override
  public final boolean belongsToTable() {
    return (parentTable != null);
  }

  @Override
  public final void delete() {

    ENTITY_VALIDATOR.assertCanBeDeleted(this);

    /*
     * An Entity must not be referenced on deletion. This is validated. But an
     * Entity must update all back referencing fields on deletion.
     */
    updateBackReferencingFieldsForDeletion();

    updateStateForDeletion();
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

  public final void internalSetEdited() {
    switch (getState()) {
      case NEW:
        //Does nothing.
        break;
      case LOADED:
        state = DatabaseObjectState.EDITED;
        break;
      case EDITED:
        //Does nothing.
        break;
      case DELETED:
        throw DeletedArgumentException.forArgument(this);
      case CLOSED:
        throw ClosedArgumentException.forArgument(this);
    }
  }

  @Override
  public final void internalSetId(final String id) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();

    if (gotExternalId) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "got already an external id");
    }

    this.id = id;
    gotExternalId = true;
  }

  @Override
  public final void internalSetLoaded() {

    DATABASE_OBJECT_VALIDATOR.assertIsNew(this);

    state = DatabaseObjectState.LOADED;
  }

  @Override
  public final void internalSetParentTable(final ITable<? extends IEntity> parentTable) {

    GlobalValidator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    if (belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentTable());
    }

    this.parentTable = parentTable;
    getStoredFields().forEach(AbstractField::internalSetParentColumnFromParentTable);
  }

  @Override
  public final void internalSetSaveStamp(final String saveStamp) {

    GlobalValidator.assertThat(saveStamp).thatIsNamed(LowerCaseVariableCatalog.SAVE_STAMP).isNotBlank();

    this.saveStamp = saveStamp;
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
  public final boolean isConnectedWithRealDatabase() {
    return belongsToTable()
    && getStoredParentTable().isConnectedWithRealDatabase();
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

  protected abstract IContainer<AbstractField> findFields();

  protected final void initialize() {
    extractFieldsIfNotExtracted();
  }

  protected final void setInsertAction(final Runnable insertAction) {
    entityFlyweight = EntityFlyWeight.withInsertAction(insertAction);
  }

  final void internalClose() {
    state = DatabaseObjectState.CLOSED;
  }

  final IDataAdapterAndSchemaReader internalGetStoredDataAndSchemaAdapter() {
    return ((Table<?>) getStoredParentTable()).internalGetStoredDataAndSchemaAdapter();
  }

  final void internalNoteInsertIntoDatabase() {

    updateBaseBackReferencesWhenIsInsertedIntoDatabase();

    entityFlyweight.noteInsert();
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

    fields = findFields();

    fields.forEach(p -> p.internalSetParentEntity(this));
  }

  private IContainer<AbstractField> getStoredFields() {

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

  private void updateBackReferenceForDeletion(final BackReference<?> backReference) {
    backReference.internalClear();
    backReference.setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateBackReferencingFieldsForDeletion() {
    ENTITY_SEARCHER
      .getStoredAbstractBackReferencesThatReferencesBackEntity(this)
      .forEach(this::updateBackReferencingFieldsForDeletion);
  }

  private void updateBackReferencingFieldsForDeletion(
    final IAbstractBackReference<?> baseBackReference) {
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
    getStoredFields().forEach(AbstractField::internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase);
  }

  private void updateOptionalBackReferenceForDeletion(final OptionalBackReference<?> optionalBackReference) {
    optionalBackReference.internalClear();
    optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateStateForDeletion() {
    state = DatabaseObjectState.DELETED;
  }
}
