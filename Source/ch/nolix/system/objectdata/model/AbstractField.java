package ch.nolix.system.objectdata.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.system.objectdata.fieldvalidator.FieldValidator;
import ch.nolix.system.objectdata.modelflyweight.FieldFlyWeight;
import ch.nolix.system.objectdata.modelflyweight.VoidFieldFlyWeight;
import ch.nolix.systemapi.databaseobject.property.DatabaseObjectState;
import ch.nolix.systemapi.middata.adapter.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.objectdata.fieldvalidator.IFieldValidator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelflyweight.IFieldFlyWeight;
import ch.nolix.systemapi.objectdata.schemaview.IColumnView;

public abstract class AbstractField implements IField {

  private static final IFieldValidator FIELD_VALIDATOR = new FieldValidator();

  private static final VoidFieldFlyWeight VOID_FIELD_FLY_WEIGHT = new VoidFieldFlyWeight();

  private AbstractEntity parentEntity;

  private IColumnView<ITable<IEntity>> parentColumn;

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
      return ReflectionTool.getNameOfFirstFieldThatHasValue(getStoredParentEntity(), this);
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate name");
  }

  @Override
  public final DatabaseObjectState getState() {

    if (!belongsToEntity()) {
      return DatabaseObjectState.NEW;
    }

    return getStateWhenBelongsToEntity();
  }

  @Override
  public final IColumnView<ITable<IEntity>> getStoredParentColumn() {

    FIELD_VALIDATOR.assertKnowsParentColumn(this);

    return parentColumn;
  }

  @Override
  public final AbstractEntity getStoredParentEntity() {

    FIELD_VALIDATOR.assertBelongsToEntity(this);

    return parentEntity;
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
    return //
    belongsToEntity()
    && getStoredParentEntity().isConnectedWithRealDatabase();
  }

  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.UNEDITED);
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

  protected final IDataAdapterAndSchemaReader getStoredDataAndSchemaAdapter() {
    return getStoredParentEntity().getStoredMidDataAdapterAndSchemaReader();
  }

  protected final void setAsEditedAndRunPotentialUpdateAction() {

    if (belongsToEntity()) {
      getStoredParentEntity().setEdited();
    }

    edited = true;

    fieldFlyWeight.noteUpdate();
  }

  protected abstract void internalUpdateBackReferencingFieldsWhenIsInsertedIntoDatabase();

  final void setParentColumn(final IColumnView<ITable<IEntity>> parentColumn) {

    Validator.assertThat(parentColumn).thatIsNamed("parent column").isNotNull();

    this.parentColumn = parentColumn;
  }

  final void setParentColumnFromParentTable() {

    final var name = getName();

    final var localParentColumn = //
    getStoredParentEntity().getStoredParentTable().getStoredColumns().getStoredFirst(c -> c.hasName(name));

    setParentColumn(localParentColumn);
  }

  final void setParentEntity(final AbstractEntity parentEntity) {

    Validator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
    FIELD_VALIDATOR.assertDoesNotBelongToEntity(this);

    this.parentEntity = parentEntity;
    setParentColumnFromParentTableIfParentEntityBelongsToTable(parentEntity);
  }

  private DatabaseObjectState getStateWhenBelongsToEntity() {

    final var parentEntityState = getStoredParentEntity().getState();

    return switch (parentEntityState) {
      case NEW ->
        DatabaseObjectState.NEW;
      case UNEDITED ->
        DatabaseObjectState.UNEDITED;
      case EDITED ->
        getStateWhenParentFieldIsEdited();
      case DELETED ->
        DatabaseObjectState.DELETED;
      case CLOSED ->
        DatabaseObjectState.CLOSED;
      default ->
        throw //
        InvalidArgumentException.forArgumentAndArgumentName(
          getStoredParentEntity().getState(),
          LowerCaseVariableCatalog.STATE);
    };
  }

  private DatabaseObjectState getStateWhenParentFieldIsEdited() {

    if (!edited) {
      return DatabaseObjectState.UNEDITED;
    }

    return DatabaseObjectState.EDITED;
  }

  private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final AbstractEntity parentEntity) {
    if (parentEntity.belongsToTable()) {
      setParentColumnFromParentTable();
    }
  }
}
