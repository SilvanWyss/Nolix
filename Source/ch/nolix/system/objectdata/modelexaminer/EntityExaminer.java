package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.fieldexaminer.IFieldExaminer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class EntityExaminer extends DatabaseObjectExaminer implements IEntityExaminer {
  private static final IFieldExaminer FIELD_EXAMINER = new FieldExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final IEntity entity) {
    if (isNewOrEdited(entity)) {
      return entity.internalGetStoredFields().containsOnly(FIELD_EXAMINER::isSetForCaseWhenIsMandatoryAndNewOrEdited);
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeDeleted(final IEntity entity) {
    return //
    entity != null
    && entity.isLoaded()
    && !isReferencedIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeInsertedIntoTable(final IEntity entity) {
    return //
    entity != null
    && entity.isNew()
    && entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetParentTable(final IEntity entity) {
    return //
    entity != null
    && entity.isOpen()
    && !entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> boolean canSetParentTable(final IEntity entity, final ITable<E> table) {
    return //
    canSetParentTable(entity)
    && table != null
    && table.isOpen()
    && entity.getClass() == table.getEntityType();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferenced(final IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || entity.isReferencedInPersistedData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedIgnoringLocallyDeletedEntities(IEntity entity) {
    return //
    isReferencedInLocalData(entity)
    || isReferencedInPersistedDataIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedInLocalData(final IEntity entity) {
    if (!entity.belongsToTable()) {
      return false;
    }

    for (final var t : entity.getStoredParentTable().getStoredParentDatabase().getStoredTables()) {
      if (t.internalGetStoredEntitiesInLocalData().containsAny(e -> referencesGivenEntity(e, entity))) {
        return true;
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesGivenEntity(final IEntity sourceEntity, final IEntity entity) {
    return sourceEntity.internalGetStoredFields().containsAny(p -> p.referencesEntity(entity));
  }

  private IContainer<String> getLocallyDeletedEntities(final IEntity entity) {
    return //
    entity
      .getStoredParentTable()
      .getStoredParentDatabase()
      .getStoredTables()
      .toMultiples(ITable::internalGetStoredEntitiesInLocalData).getStoredSelected(IEntity::isDeleted)
      .to(IEntity::getId);
  }

  private boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(final IEntity entity) {
    if (entity.isReferencedInPersistedData()) {
      final var locallyDeletedEntities = getLocallyDeletedEntities(entity);

      return entity.isReferencedInPersistedDataIgnoringGivenEntities(locallyDeletedEntities);
    }

    return false;
  }
}
