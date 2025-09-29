package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IBaseBackReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;

public abstract class AbstractBaseBackReference<E extends IEntity>
extends AbstractField
implements IBaseBackReference<E> {
  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  private final ImmutableList<String> backReferenceableTableNames;

  private final String backReferencedFieldName;

  private Table<E> backReferencedTable;

  protected AbstractBaseBackReference(
    final IContainer<String> backReferenceableTableNames,
    final String backReferencedFieldName) {

    Validator.assertThatTheStrings(backReferenceableTableNames).areNotBlank();

    Validator
      .assertThat(backReferencedFieldName)
      .thatIsNamed("back referenced field name")
      .isNotBlank();

    this.backReferenceableTableNames = ImmutableList.forIterable(backReferenceableTableNames);
    this.backReferencedFieldName = backReferencedFieldName;
  }

  @Override
  public final IContainer<String> getBackReferenceableTableNames() {
    return backReferenceableTableNames;
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
    //TODO: Update
    return backReferenceableTableNames.getStoredFirst();
  }

  @Override
  public final IContainer<IBaseBackReference<IEntity>> getStoredBaseBackReferencesWhoReferencesBackThis() {
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

  @Override
  protected final void noteInsertIntoDatabase() {
    //Does nothing.
  }

  private boolean canReferenceBackFieldBecauseOfName(final IField field) {
    return //
    belongsToEntity()
    && FIELD_EXAMINER.belongsToEntity(field)
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
