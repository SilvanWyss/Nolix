//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.propertytool.PropertyTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseBackReference<E extends IEntity> extends Field implements IBaseBackReference<E> {

  //constant
  private static final PropertyTool PROPERTY_TOOL = new PropertyTool();

  //attribute
  private final String backReferencedTableName;

  //attribute
  private final String backReferencedPropertyName;

  //optional attribute
  private Table<E> backReferencedTable;

  //constructor
  protected BaseBackReference(final String backReferencedTableName, final String backReferencedPropertyName) {

    GlobalValidator.assertThat(backReferencedTableName).thatIsNamed("back referenced table name").isNotBlank();

    GlobalValidator
      .assertThat(backReferencedPropertyName)
      .thatIsNamed("back referenced property name")
      .isNotBlank();

    this.backReferencedTableName = backReferencedTableName;
    this.backReferencedPropertyName = backReferencedPropertyName;
  }

  //method
  @Override
  public final String getBackReferencedPropertyName() {
    return backReferencedPropertyName;
  }

  //method
  @Override
  public final ITable<E> getStoredBackReferencedTable() {

    extractBackReferencedTableIfNotExtracted();

    return backReferencedTable;
  }

  //method
  @Override
  public final String getBackReferencedTableName() {
    return backReferencedTableName;
  }

  //method
  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {
    return new ImmutableList<>();
  }

  //method
  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
  }

  //method
  @Override
  public final boolean referencesBackProperty(final IField field) {
    return //
    canReferenceBackPropertyBecauseOfName(field)
    && referencesBackEntityWithId(field.getStoredParentEntity().getId());
  }

  //method
  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  //method declaration
  protected abstract boolean referencesBackEntityWithId(String id);

  //method
  @Override
  final void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    //Does nothing.
  }

  //method
  private boolean canReferenceBackPropertyBecauseOfName(final IField field) {
    return //
    belongsToEntity()
    && PROPERTY_TOOL.belongsToEntity(field)
    && getBackReferencedTableName().equals(field.getStoredParentEntity().getParentTableName())
    && getBackReferencedPropertyName().equals(field.getName());
  }

  //method
  private boolean extractedBackReferencedTable() {
    return (backReferencedTable != null);
  }

  //method
  private void extractBackReferencedTable() {
    backReferencedTable = loadBackReferencedTable();
  }

  //method
  private void extractBackReferencedTableIfNotExtracted() {
    if (!extractedBackReferencedTable()) {
      extractBackReferencedTable();
    }
  }

  //method
  @SuppressWarnings("unchecked")
  private Table<E> loadBackReferencedTable() {
    return (Table<E>) getStoredParentEntity()
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTableByName(getBackReferencedTableName());
  }
}
