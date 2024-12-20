package ch.nolix.system.objectdata.data;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.fieldflyweight.FieldFlyWeight;
import ch.nolix.system.objectdata.fieldflyweight.VoidFieldFlyWeight;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

public abstract class Field implements IField {

  private static final FieldValidator FIELD_VALIDATOR = new FieldValidator();

  private static final VoidFieldFlyWeight VOID_FIELD_FLY_WEIGHT = new VoidFieldFlyWeight();

  private IFieldFlyWeight fieldFlyWeight = VOID_FIELD_FLY_WEIGHT;

  private boolean edited;

  private IEntity parentEntity;

  private IColumn parentColumn;

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

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate own name");
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

    if (!belongsToEntity()) {
      return false;
    }

    return getStoredParentEntity().isDeleted();
  }

  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  @Override
  public final boolean isLinkedWithRealDatabase() {
    return (belongsToEntity() && getStoredParentEntity().isLinkedWithRealDatabase());
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  @Override
  public final boolean isNew() {

    if (!belongsToEntity()) {
      return true;
    }

    return getStoredParentEntity().isNew();
  }

  @Override
  public final boolean knowsParentColumn() {
    return (parentColumn != null);
  }

  @Override
  public final void setUpdateAction(final Runnable updateAction) {

    setEffectiveFieldFlyWeightIfFieldFlyWeightIsVoid();

    fieldFlyWeight.setUpdateAction(updateAction);
  }

  protected final void setAsEditedAndRunPotentialUpdateAction() {

    if (belongsToEntity()) {
      ((BaseEntity) getStoredParentEntity()).internalSetEdited();
    }

    edited = true;

    fieldFlyWeight.noteUpdate();
  }

  final IDataAndSchemaAdapter internalGetStoredDataAndSchemaAdapter() {
    return ((BaseEntity) parentEntity).internalGetStoredDataAndSchemaAdapter();
  }

  abstract void internalSetOrClearFromContent(Object content);

  final void internalSetParentColumn(final IColumn parentColumn) {

    GlobalValidator.assertThat(parentColumn).thatIsNamed("parent column").isNotNull();

    this.parentColumn = parentColumn;
  }

  final void internalSetParentColumnFromParentTable() {
    final var name = getName();
    parentColumn = getStoredParentEntity().getStoredParentTable().getStoredColumns()
      .getStoredFirst(c -> c.hasName(name));
  }

  final void internalSetParentEntity(final BaseEntity parentEntity) {

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
          LowerCaseVariableCatalogue.STATE,
          getStoredParentEntity().getState());
    };
  }

  private DatabaseObjectState getStateWhenParentFieldIsEdited() {

    if (!edited) {
      return DatabaseObjectState.LOADED;
    }

    return DatabaseObjectState.EDITED;
  }

  private void setEffectiveFieldFlyWeightIfFieldFlyWeightIsVoid() {
    if (fieldFlyWeight.isVoid()) {
      setEffectiveFieldFlyWeightWhenFieldFlyWeightIsVoid();
    }
  }

  private void setEffectiveFieldFlyWeightWhenFieldFlyWeightIsVoid() {
    fieldFlyWeight = new FieldFlyWeight();
  }

  private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final BaseEntity parentEntity) {
    if (parentEntity.belongsToTable()) {
      internalSetParentColumnFromParentTable();
    }
  }
}
