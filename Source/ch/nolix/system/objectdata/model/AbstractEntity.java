package ch.nolix.system.objectdata.model;

import ch.nolix.core.datamodel.id.IdCreator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.DeletedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.databaseobject.modelvalidator.DatabaseObjectValidator;
import ch.nolix.system.objectdata.modelflyweight.EntityFlyWeight;
import ch.nolix.system.objectdata.modelflyweight.VoidEntityFlyWeight;
import ch.nolix.system.objectdata.modelvalidator.EntityValidator;
import ch.nolix.systemapi.databaseobject.modelvalidator.IDatabaseObjectValidator;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.model.IDatabase;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelflyweight.IEntityFlyWeight;
import ch.nolix.systemapi.objectdata.modelvalidator.IEntityValidator;

public abstract class AbstractEntity implements IEntity {
  private static final VoidEntityFlyWeight VOID_ENTITY_FLY_WEIGHT = new VoidEntityFlyWeight();

  private static final IDatabaseObjectValidator DATABASE_OBJECT_VALIDATOR = new DatabaseObjectValidator();

  private static final IEntityValidator ENTITY_VALIDATOR = new EntityValidator();

  private ITable<? extends IEntity> memberParentTable;

  private String memberId = IdCreator.createIdOf10HexadecimalCharacters();

  private DatabaseObjectState state = DatabaseObjectState.NEW;

  private String memberSaveStamp;

  private IEntityFlyWeight entityFlyweight = VOID_ENTITY_FLY_WEIGHT;

  private IContainer<AbstractField> fields;

  @Override
  public final boolean belongsToDatabase() {
    return //
    memberParentTable != null
    && memberParentTable.belongsToDatabase();
  }

  @Override
  public final boolean belongsToTable() {
    return (memberParentTable != null);
  }

  @Override
  public final void delete() {
    ENTITY_VALIDATOR.assertCanBeDeleted(this);

    /*
     * An Entity must not be referenced on deletion. This will be validated on
     * saving. But the delete method of an Entity must update all base back
     * references that references back the Entity.
     */
    BaseBackReferenceUpdater.updateBaseBackReferencesThatReferencesBackEntityForDeleteEntity(this);

    state = DatabaseObjectState.DELETED;
  }

  @Override
  public final String getId() {
    return memberId;
  }

  @Override
  public final IDatabase getStoredParentDatabase() {
    return getStoredParentTable().getStoredParentDatabase();
  }

  @Override
  public final ITable<? extends IEntity> getStoredParentTable() {
    ENTITY_VALIDATOR.assertBelongsToTable(this);

    return memberParentTable;
  }

  @Override
  public final String getSaveStamp() {
    ENTITY_VALIDATOR.assertHasSaveStamp(this);

    return memberSaveStamp;
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
    return (memberSaveStamp != null);
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

    this.state = DatabaseObjectState.UNEDITED;
    memberId = id;
    memberSaveStamp = saveStamp;
  }

  @Override
  public final void internalSetParentTable(final ITable<? extends IEntity> parentTable) {
    ENTITY_VALIDATOR.assertCanSetParentTable(this, parentTable);

    memberParentTable = parentTable;

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
    return (getState() == DatabaseObjectState.UNEDITED);
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
    return ((Table<?>) getStoredParentTable()).getStoredMidDataDataAdapterAndSchemaReader();
  }

  final void noteInsertIntoDatabase() {
    getStoredFields().forEach(AbstractField::noteInsertIntoDatabase);

    entityFlyweight.noteInsertIntoDatabase();
  }

  final void setEdited() {
    final var localState = getState();

    switch (state) {
      case NEW:
        //Does nothing.
        break;
      case UNEDITED:
        state = DatabaseObjectState.EDITED;
        break;
      case EDITED:
        //Does nothing.
        break;
      case DELETED:
        throw DeletedArgumentException.forArgument(this);
      case CLOSED:
        throw ClosedArgumentException.forArgument(this);
      default:
        throw InvalidArgumentException.forArgument(localState);
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
      .containsAny(c -> c.containsValueInPersistedData(localId));
  }

  private boolean isReferencedInPersistedDataIgnoringGivenEntitiesWhenBelongsToTable(
    final IContainer<String> entitiesToIgnoreIds) {
    final var localId = getId();

    return //
    ((Table<?>) getStoredParentTable())
      .internalGetColumnsThatReferencesCurrentTable()
      .containsAny(c -> c.containsValueInPersistedDataIgnoringEntities(localId, entitiesToIgnoreIds));
  }
}
