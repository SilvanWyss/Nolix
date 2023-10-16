//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.reflection.GlobalReflectionHelper;
import ch.nolix.system.objectdatabase.propertyflyweight.PropertyFlyWeight;
import ch.nolix.system.objectdatabase.propertyflyweight.VoidPropertyFlyWeight;
import ch.nolix.system.objectdatabase.propertyvalidator.PropertyValidator;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.DatabaseObjectState;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IColumn;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseflyweightapi.IPropertyFlyWeight;
import ch.nolix.systemapi.rawdatabaseapi.databaseandschemaadapterapi.IDataAndSchemaAdapter;

//class
public abstract class Property implements IProperty {

  //constant
  private static final PropertyValidator PROPERTY_VALIDATOR = new PropertyValidator();

  //constant
  private static final VoidPropertyFlyWeight VOID_PROPERTY_FLY_WEIGHT = new VoidPropertyFlyWeight();

  //attribute
  private IPropertyFlyWeight propertyFlyWeight = VOID_PROPERTY_FLY_WEIGHT;

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
      return GlobalReflectionHelper.getFieldName(getStoredParentEntity(), this);
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

    if (!belongsToEntity()) {
      return false;
    }

    return getStoredParentEntity().isClosed();
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
  public final boolean isLinkedWithRealDatabase() {
    return (belongsToEntity() && getStoredParentEntity().isLinkedWithRealDatabase());
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

    propertyFlyWeight.setUpdateAction(updateAction);
  }

  //method
  protected final void setAsEditedAndRunProbableUpdateAction() {

    if (belongsToEntity()) {
      ((BaseEntity) getStoredParentEntity()).internalSetEdited();
    }

    edited = true;

    propertyFlyWeight.noteUpdate();
  }

  //method
  final IDataAndSchemaAdapter internalGetRefDataAndSchemaAdapter() {
    return ((BaseEntity) parentEntity).internalGetRefDataAndSchemaAdapter();
  }

  //method declaration
  abstract void internalSetOrClearDirectlyFromContent(Object content);

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
  abstract void internalUpdateProbableBackReferencesWhenIsNew();

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
            LowerCaseCatalogue.STATE,
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
    if (propertyFlyWeight.isVoid()) {
      setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid();
    }
  }

  //method
  private void setEffectivePropertyFlyWeightWhenPropertyFlyWeightIsVoid() {
    propertyFlyWeight = new PropertyFlyWeight();
  }

  //method
  private void setParentColumnFromParentTableIfParentEntityBelongsToTable(final BaseEntity parentEntity) {
    if (parentEntity.belongsToTable()) {
      internalSetParentColumnFromParentTable();
    }
  }
}
