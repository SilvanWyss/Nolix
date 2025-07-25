package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.datavalidator.EntityValidator;
import ch.nolix.system.objectdata.modelflyweight.EntityFlyWeight;
import ch.nolix.system.objectdata.modelflyweight.VoidEntityFlyWeight;
import ch.nolix.system.objectdata.modelsearcher.EntitySearcher;
import ch.nolix.systemapi.databaseobject.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.datavalidator.IEntityValidator;
import ch.nolix.systemapi.objectdata.model.IAbstractBackReference;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelflyweight.IEntityFlyWeight;
import ch.nolix.systemapi.objectdata.modelsearcher.IEntitySearcher;

public abstract class AbstractEntity implements IEntity {

  private static final VoidEntityFlyWeight VOID_ENTITY_FLY_WEIGHT = new VoidEntityFlyWeight();

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IEntitySearcher ENTITY_SEARCHER = new EntitySearcher();

  private static final IEntityValidator ENTITY_VALIDATOR = new EntityValidator();

  private ITable<? extends IEntity> parentTable;

  private String id = IdCreator.createIdOf10HexadecimalCharacters();

  private DatabaseObjectState state = DatabaseObjectState.NEW;

  private String saveStamp;

  private IEntityFlyWeight entityFlyweight = VOID_ENTITY_FLY_WEIGHT;

  private IContainer<AbstractField> fields;

  @Override
  public final boolean belongsToTable() {
    return (parentTable != null);
  }

  @Override
  public final void delete() {

    ENTITY_VALIDATOR.assertCanBeDeleted(this);

    /*
     * An Entity must not be referenced on deletion. This will be validated. But the
     * delete method of an Entity must update all abstract back references that
     * references back the Entity.
     */
    updateAbstractBackReferencesThatReferencesBackThisForDeleteThis();

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
  public final String getShortDescription() {
    return (getClass().getSimpleName() + " (id: " + getId() + ")");
  }

  @Override
  public final DatabaseObjectState getState() {
    return state;
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
  public final void internalSetLoadedAndIdAndSaveStamp(final String id, final String saveStamp) {

    DATABASE_OBJECT_VALIDATOR.assertIsNew(this);

    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    Validator.assertThat(saveStamp).thatIsNamed(LowerCaseVariableCatalog.SAVE_STAMP).isNotBlank();

    this.state = DatabaseObjectState.LOADED;
    this.id = id;
    this.saveStamp = saveStamp;
  }

  @Override
  public final void internalSetParentTable(final ITable<? extends IEntity> parentTable) {

    Validator.assertThat(parentTable).thatIsNamed("parent table").isNotNull();

    if (belongsToTable()) {
      throw ArgumentBelongsToParentException.forArgumentAndParent(this, getStoredParentTable());
    }

    this.parentTable = parentTable;
    getStoredFields().forEach(AbstractField::setParentColumnFromParentTable);
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
    return getShortDescription();
  }

  protected final void initialize() {
    extractFieldsIfNotExtracted();
  }

  protected final void setInsertAction(final Runnable insertAction) {
    entityFlyweight = EntityFlyWeight.withInsertAction(insertAction);
  }

  final void close() {
    if (isOpen()) {
      state = DatabaseObjectState.CLOSED;
    }
  }

  abstract IContainer<AbstractField> findFields();

  final IDataAdapterAndSchemaReader getStoredMidDataAdapterAndSchemaReader() {
    return ((Table<?>) getStoredParentTable()).getStoredMidDataAdapterAndSchemaReader();
  }

  final void noteInsertIntoDatabase() {

    updateBaseBackReferencesWhenIsInsertedIntoDatabase();

    entityFlyweight.noteInsert();
  }

  final void setEdited() {
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

    fields.forEach(f -> f.setParentEntity(this));
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

  private void updateAbstractBackReferencesThatReferencesBackThisForDeleteThis() {
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
    getStoredFields().forEach(AbstractField::internalUpdateBackReferencingFieldsWhenIsInsertedIntoDatabase);
  }

  private void updateOptionalBackReferenceForDeletion(final OptionalBackReference<?> optionalBackReference) {
    optionalBackReference.internalClear();
    optionalBackReference.setAsEditedAndRunPotentialUpdateAction();
  }

  private void updateStateForDeletion() {
    state = DatabaseObjectState.DELETED;
  }
}
