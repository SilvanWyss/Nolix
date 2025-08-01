package ch.nolix.system.objectdata.model;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IAbstractReference;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IField;
import ch.nolix.systemapi.objectdata.model.ITable;

public abstract class AbstractReference<E extends IEntity> extends AbstractField implements IAbstractReference<E> {

  private final String referencedTableName;

  private Table<E> referencedTable;

  protected AbstractReference(final String referencedTableName) {

    Validator.assertThat(referencedTableName).thatIsNamed("referenced table name").isNotBlank();

    this.referencedTableName = referencedTableName;
  }

  @Override
  public final String getReferencedTableId() {
    return getStoredReferencedTable().getId();
  }

  @Override
  public final String getReferencedTableName() {
    return referencedTableName;
  }

  @Override
  public final IContainer<IAbstractReference<IEntity>> getStoredAbstractReferencesThatAreBackReferencedFromThis() {
    return ImmutableList.createEmpty();
  }

  @Override
  public final ITable<E> getStoredReferencedTable() {

    extractReferencedTableIfNotExtracted();

    return referencedTable;
  }

  @Override
  public final boolean referencesBackEntity(final IEntity entity) {
    return false;
  }

  @Override
  public final boolean referencesBackEntityWithId(final String id) {
    return false;
  }

  @Override
  public final boolean referencesBackField(final IField field) {
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
