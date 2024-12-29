package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IAbstractReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IField;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;

public abstract class AbstractReference<E extends IEntity> extends AbstractField implements IAbstractReference<E> {

  private final String referencedTableName;

  private Table<E> referencedTable;

  protected AbstractReference(final String referencedTableName) {

    GlobalValidator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();

    this.referencedTableName = referencedTableName;
  }

  @Override
  public final ITable<E> getReferencedTable() {

    extractReferencedTableIfNotExtracted();

    return referencedTable;
  }

  @Override
  public final String getReferencedTableName() {
    return referencedTableName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final IContainer<IField> getStoredBackReferencedFieldsFrom() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  @Override
  public boolean referencesBackField(final IField field) {
    return false;
  }

  protected final void updateProbableBackReferenceForSetOrAddedEntity(final E entity) {
    for (final var p : entity.internalGetStoredFields()) {
      switch (p.getType()) {
        case BACK_REFERENCE:

          final var backReference = (BackReference<?>) p;

          if (backReference.referencesBackField(this)) {
            backReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
          }

          break;

        case OPTIONAL_BACK_REFERENCE:

          final var optionalBackReference = (OptionalBackReference<?>) p;

          if (optionalBackReference.referencesBackField(this)) {
            optionalBackReference.internalSetDirectlyBackReferencedEntityId(getStoredParentEntity().getId());
          }

          break;
        default:
          //Does nothing.
      }
    }
  }

  private boolean extractedReferencedTable() {
    return (referencedTable != null);
  }

  private void extractReferencedTable() {
    referencedTable = loadReferencedTable();
  }

  private void extractReferencedTableIfNotExtracted() {
    if (!extractedReferencedTable()) {
      extractReferencedTable();
    }
  }

  @SuppressWarnings("unchecked")
  private Table<E> loadReferencedTable() {
    return (Table<E>) getStoredParentEntity()
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTableByName(getReferencedTableName());
  }
}
