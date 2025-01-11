package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelflyweight.FieldFlyWeight;
import ch.nolix.system.objectdata.modelflyweight.VoidFieldFlyWeight;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.modelapi.IColumn;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelflyweightapi.IFieldFlyWeight;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;

public abstract class AbstractField implements IField {

  private static final FieldValidator FIELD_VALIDATOR = new FieldValidator();

  private static final VoidFieldFlyWeight VOID_FIELD_FLY_WEIGHT = new VoidFieldFlyWeight();

  private IEntity parentEntity;

  private IColumn parentColumn;

  private IFieldFlyWeight fieldFlyWeight = VOID_FIELD_FLY_WEIGHT;

  private boolean edited;

  @Override
  public final boolean belongsToEntity() {
    return (parentEntity != null);
  }

  @Override
  public final String getName() {

    if (knowsParentColumn()) {
      return getStoredParentColumn().getName();
    }

    if (belongsToEntity()) {
      return GlobalReflectionTool.getNameOfFirstFieldOfObjectThatStoresValue(getStoredParentEntity(), this);
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate name");
  }

  @Override
  public IColumn getStoredParentColumn() {

    FIELD_VALIDATOR.assertKnowsParentColumn(this);

    return parentColumn;
  }

  @Override
  public final IEntity getStoredParentEntity() {

    FIELD_VALIDATOR.assertBelongsToEntity(this);

    return parentEntity;
  }

  @Override
  public final DatabaseObjectState getState() {

    if (!belongsToEntity()) {
      return DatabaseObjectState.NEW;
    }

    return getStateWhenBelongsToEntity();
  }

  @Override
  public final boolean isClosed() {
    return //
    belongsToEntity()
    && getStoredParentEntity().isClosed();
  }

  @Override
  public final boolean isDeleted() {
    return //
    belongsToEntity()
    && getStoredParentEntity().isDeleted();
  }

  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public final boolean isConnectedWithRealDatabase() {
    return (belongsToEntity() && getStoredParentEntity().isConnectedWithRealDatabase());
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public final boolean isNew() {
    return //
    !belongsToEntity()
    || getStoredParentEntity().isDeleted();
  }

  @Override
  public final boolean knowsParentColumn() {
    return (parentColumn != null);
  }

  @Override
  public final void setUpdateAction(final Runnable updateAction) {
    fieldFlyWeight = FieldFlyWeight.wihUpdateAction(updateAction);
  }

  protected final void setAsEditedAndRunPotentialUpdateAction() {

    if (belongsToEntity()) {
      ((AbstractEntity) getStoredParentEntity()).internalSetEdited();
    }

    edited = true;

    fieldFlyWeight.noteUpdate();
  }

  final IDataAdapterAndSchemaReader internalGetStoredDataAndSchemaAdapter() {
    return ((AbstractEntity) parentEntity).internalGetStoredDataAndSchemaAdapter();
  }

  final void internalSetParentColumn(final IColumn parentColumn) {

    GlobalValidator.assertThat(parentColumn).thatIsNamed("parent column").isNotNull();

    this.parentColumn = parentColumn;
  }

  final void internalSetParentColumnFromParentTable() {
    final var name = getName();
    parentColumn = getStoredParentEntity().getStoredParentTable().getStoredColumns()
      .getStoredFirst(c -> c.hasName(name));
  }

  final void internalSetParentEntity(final AbstractEntity parentEntity) {

    GlobalValidator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
    FIELD_VALIDATOR.assertDoesNotBelongToEntity(this);

    this.parentEntity = parentEntity;
    setParentColumnFromParentTableIfParentEntityBelongsToTable(parentEntity);
  }

  abstract void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase();

  private DatabaseObjectState getStateWhenBelongsToEntity() {

    final var parentEntityState = getStoredParentEntity().getState();

    return switch (parentEntityState) {
      case NEW ->
        DatabaseObjectState.NEW;
      case LOADED ->
        DatabaseObjectState.LOADED;
      case EDITED ->
        getStateWhenParentFieldIsEdited();
      case DELETED ->
        DatabaseObjectState.DELETED;
      case CLOSED ->
        DatabaseObjectState.CLOSED;
      default ->
        throw InvalidArgumentException.forArgumentNameAndArgument(
          LowerCaseVariableCatalog.STATE,
          getStoredParentEntity().getState());
    };
  }

  private DatabaseObjectState getStateWhenParentFieldIsEdited() {

    if (!edited) {
      return DatabaseObjectState.LOADED;
    }

    return DatabaseObjectState.EDITED;
  }

  private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final AbstractEntity parentEntity) {
    if (parentEntity.belongsToTable()) {
      internalSetParentColumnFromParentTable();
    }
  }
}
