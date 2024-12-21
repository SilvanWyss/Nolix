package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdata.fieldtool.FieldTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseBackReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public abstract class BaseBackReference<E extends IEntity> extends AbstractField implements IBaseBackReference<E> {

  private static final FieldTool FIELD_TOOL = new FieldTool();

  private final String backReferencedTableName;

  private final String backReferencedFieldName;

  private Table<E> backReferencedTable;

  protected BaseBackReference(final String backReferencedTableName, final String backReferencedFieldName) {

    GlobalValidator.assertThat(backReferencedTableName).thatIsNamed("back referenced table name").isNotBlank();

    GlobalValidator
      .assertThat(backReferencedFieldName)
      .thatIsNamed("back referenced field name")
      .isNotBlank();

    this.backReferencedTableName = backReferencedTableName;
    this.backReferencedFieldName = backReferencedFieldName;
  }

  @Override
  public final String getBackReferencedFieldName() {
    return backReferencedFieldName;
  }

  @Override
  public final ITable<E> getStoredBackReferencedTable() {

    extractBackReferencedTableIfNotExtracted();

    return backReferencedTable;
  }

  @Override
  public final String getBackReferencedTableName() {
    return backReferencedTableName;
  }

  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferences() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final boolean referencesEntity(final IEntity entity) {
    return false;
  }

  @Override
  public final boolean referencesBackField(final IField field) {
    return //
    canReferenceBackFieldBecauseOfName(field)
    && referencesBackEntityWithId(field.getStoredParentEntity().getId());
  }

  @Override
  public final boolean referencesUninsertedEntity() {
    return false;
  }

  protected abstract boolean referencesBackEntityWithId(String id);

  @Override
  final void internalUpdatePotentialBaseBackReferencesWhenIsInsertedIntoDatabase() {
    //Does nothing.
  }

  private boolean canReferenceBackFieldBecauseOfName(final IField field) {
    return //
    belongsToEntity()
    && FIELD_TOOL.belongsToEntity(field)
    && getBackReferencedTableName().equals(field.getStoredParentEntity().getParentTableName())
    && getBackReferencedFieldName().equals(field.getName());
  }

  private boolean extractedBackReferencedTable() {
    return (backReferencedTable != null);
  }

  private void extractBackReferencedTable() {
    backReferencedTable = loadBackReferencedTable();
  }

  private void extractBackReferencedTableIfNotExtracted() {
    if (!extractedBackReferencedTable()) {
      extractBackReferencedTable();
    }
  }

  @SuppressWarnings("unchecked")
  private Table<E> loadBackReferencedTable() {
    return (Table<E>) getStoredParentEntity()
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTableByName(getBackReferencedTableName());
  }
}
