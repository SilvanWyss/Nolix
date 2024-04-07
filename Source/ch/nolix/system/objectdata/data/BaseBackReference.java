//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public abstract class BaseBackReference<E extends IEntity> extends Field implements IBaseBackReference<E> {

  //constant
  private static final FieldTool FIELD_TOOL = new FieldTool();

  //attribute
  private final String backReferencedTableName;

  //attribute
  private final String backReferencedFieldName;

  //optional attribute
  private Table<E> backReferencedTable;

  //constructor
  protected BaseBackReference(final String backReferencedTableName, final String backReferencedFieldName) {

    GlobalValidator.assertThat(backReferencedTableName).thatIsNamed("back referenced table name").isNotBlank();

    GlobalValidator
      .assertThat(backReferencedFieldName)
      .thatIsNamed("back referenced field name")
      .isNotBlank();

    this.backReferencedTableName = backReferencedTableName;
    this.backReferencedFieldName = backReferencedFieldName;
  }

  //method
  @Override
  public final String getBackReferencedFieldName() {
    return backReferencedFieldName;
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
  public final boolean referencesBackField(final IField field) {
    return //
    canReferenceBackFieldBecauseOfName(field)
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
  private boolean canReferenceBackFieldBecauseOfName(final IField field) {
    return //
    belongsToEntity()
    && FIELD_TOOL.belongsToEntity(field)
    && getBackReferencedTableName().equals(field.getStoredParentEntity().getParentTableName())
    && getBackReferencedFieldName().equals(field.getName());
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
