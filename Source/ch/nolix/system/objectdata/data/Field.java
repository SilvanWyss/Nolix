//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.reflection.GlobalObjectTool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.system.objectdata.fieldflyweight.FieldFlyWeight;
import ch.nolix.system.objectdata.fieldflyweight.VoidFieldFlyWeight;
import ch.nolix.system.objectdata.propertyvalidator.PropertyValidator;
import ch.nolix.systemapi.databaseobjectapi.databaseobjectproperty.DatabaseObjectState;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataflyweightapi.IFieldFlyWeight;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class Field implements IField {

  //constant
  private static final PropertyValidator PROPERTY_VALIDATOR = new PropertyValidator();

  //constant
  private static final VoidFieldFlyWeight VOID_PROPERTY_FLY_WEIGHT = new VoidFieldFlyWeight();

  //attribute
  private IFieldFlyWeight fieldFlyWeight = VOID_PROPERTY_FLY_WEIGHT;

  //attribute
  private boolean edited;

  //optional attribute
  private IEntity parentEntity;

  //optional attribute
  private IColumn parentColumn;

  //method
  @Override
  public final boolean belongsToEntity() {
    return (parentEntity != null);
  }

  //method
  @Override
  public final String getName() {

    if (knowsParentColumn()) {
      return getStoredParentColumn().getName();
    }

    if (belongsToEntity()) {
      return GlobalObjectTool.getNameOfFirstFieldOfObjectThatStoresValue(getStoredParentEntity(), this);
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "cannot evaluate own name");
  }

  //method
  @Override
  public IColumn getStoredParentColumn() {

    PROPERTY_VALIDATOR.assertKnowsParentColumn(this);

    return parentColumn;
  }

  //method
  @Override
  public final IEntity getStoredParentEntity() {

    PROPERTY_VALIDATOR.assertBelongsToEntity(this);

    return parentEntity;
  }

  //method
  @Override
  public final DatabaseObjectState getState() {

    if (!belongsToEntity()) {
      return DatabaseObjectState.NEW;
    }

    return getStateWhenBelongsToEntity();
  }

  //method
  @Override
  public final boolean isClosed() {
    return //
    belongsToEntity()
    && getStoredParentEntity().isClosed();
  }

  //method
  @Override
  public final boolean isDeleted() {

    if (!belongsToEntity()) {
      return false;
    }

    return getStoredParentEntity().isDeleted();
  }

  //method
  @Override
  public final boolean isEdited() {
    return (getState() == DatabaseObjectState.EDITED);
  }

  //method
  @Override
  public final boolean isLinkedWithRealDatabase() {
    return (belongsToEntity() && getStoredParentEntity().isLinkedWithRealDatabase());
  }

  //method
  @Override
  public final boolean isLoaded() {
    return (getState() == DatabaseObjectState.LOADED);
  }

  //method
  @Override
  public final boolean isNew() {

    if (!belongsToEntity()) {
      return true;
    }

    return getStoredParentEntity().isNew();
  }

  //method
  @Override
  public final boolean knowsParentColumn() {
    return (parentColumn != null);
  }

  //method
  @Override
  public final void setUpdateAction(final Runnable updateAction) {

    setEffectivePropertyFlyWeightIfPropertyFlyWeightIsVoid();

    fieldFlyWeight.setUpdateAction(updateAction);
  }

  //method
  protected final void setAsEditedAndRunPotentialUpdateAction() {

    if (belongsToEntity()) {
      ((BaseEntity) getStoredParentEntity()).internalSetEdited();
    }

    edited = true;

    fieldFlyWeight.noteUpdate();
  }

  //method
  final IDataAndSchemaAdapter internalGetStoredDataAndSchemaAdapter() {
    return ((BaseEntity) parentEntity).internalGetStoredDataAndSchemaAdapter();
  }

  //method declaration
  abstract void internalSetOrClearFromContent(Object content);

  //method
  final void internalSetParentColumn(final IColumn parentColumn) {

    GlobalValidator.assertThat(parentColumn).thatIsNamed("parent column").isNotNull();

    this.parentColumn = parentColumn;
  }

  //method
  final void internalSetParentColumnFromParentTable() {
    final var name = getName();
    parentColumn = getStoredParentEntity().getStoredParentTable().getStoredColumns()
      .getStoredFirst(c -> c.hasName(name));
  }

  //method
  final void internalSetParentEntity(final BaseEntity parentEntity) {

    GlobalValidator.assertThat(parentEntity).thatIsNamed("parent entity").isNotNull();
    PROPERTY_VALIDATOR.assertDoesNotBelongToEntity(this);

    this.parentEntity = parentEntity;
    setParentColumnFromParentTableIfParentEntityBelongsToTable(parentEntity);
  }

  //method declaration
  abstract void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase();

  //method
  private DatabaseObjectState getStateWhenBelongsToEntity() {

    final var parentEntityState = getStoredParentEntity().getState();

    return switch (parentEntityState) {
      case NEW ->
        DatabaseObjectState.NEW;
      case LOADED ->
        DatabaseObjectState.LOADED;
      case EDITED ->
        getStateWhenParentPropertyIsEdited();
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

  //method
  private DatabaseObjectState getStateWhenParentPropertyIsEdited() {

    if (!edited) {
      return DatabaseObjectState.LOADED;
    }

    return DatabaseObjectState.EDITED;
  }

  //method
  private void setEffectivePropertyFlyWeightIfPropertyFlyWeightIsVoid() {
    if (fieldFlyWeight.isVoid()) {
      setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid();
    }
  }

  //method
  private void setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid() {
    fieldFlyWeight = new FieldFlyWeight();
  }

  //method
  private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final BaseEntity parentEntity) {
    if (parentEntity.belongsToTable()) {
      internalSetParentColumnFromParentTable();
    }
  }
}
