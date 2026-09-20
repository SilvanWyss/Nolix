/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.database.databaseobjectexaminer.AbstractDatabaseObjectExaminer;
import ch.nolix.system.objectdata.fieldexaminer.FieldExaminer;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;

/**
 * @author Silvan Wyss
 */
public final class EntityExaminer extends AbstractDatabaseObjectExaminer<Entity> implements IEntityExaminer {
  private static final EntityExaminerHelper ENTITY_EXAMINER_HELPER = new EntityExaminerHelper();

  private static final FieldExaminer FIELD_EXAMINER = new FieldExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final Entity entity) {
    if (isNewOrEdited(entity)) {
      return //
      entity.internalGetStoredFields().containsMatchingOnly(FIELD_EXAMINER::isSetForCaseWhenIsMandatoryAndNewOrEdited);
    }

    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeDeleted(final Entity entity) {
    return //
    entity != null
    && entity.isLoaded()
    && !isReferencedIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeInsertedIntoTable(final Entity entity) {
    return //
    entity != null
    && entity.isNew()
    && entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetParentTable(final Entity entity) {
    return //
    entity != null
    && entity.isOpen()
    && !entity.belongsToTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetParentTable(final Entity entity, final ITable<? extends Entity> table) {
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
  public boolean isReferencedIgnoringLocallyDeletedEntities(Entity entity) {
    return //
    isReferencedInLocalDataIgnoringLocallyDeletedEntities(entity)
    || isReferencedInPersistedDataIgnoringLocallyDeletedEntities(entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedInLocalDataIgnoringLocallyDeletedEntities(final Entity entity) {
    if (entity.belongsToDatabase()) {
      final var tables = entity.getStoredParentDatabase().getStoredTables();

      for (final var t : tables) {
        if (t.internalGetStoredEntitiesInLocalData().containsMatching(e -> !e.isDeleted()
        && referencesGivenEntity(e, entity))) {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isReferencedInPersistedDataIgnoringLocallyDeletedEntities(final Entity entity) {
    if (entity.isReferencedInPersistedData()) {
      final var locallyDeletedEntities = //
      ENTITY_EXAMINER_HELPER.getLocallyDeletedEntitiesIds(entity.getStoredParentDatabase());

      return entity.isReferencedInPersistedDataIgnoringGivenEntities(locallyDeletedEntities);
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean referencesGivenEntity(final Entity sourceEntity, final Entity entity) {
    return sourceEntity.internalGetStoredFields().containsMatching(p -> p.referencesEntity(entity));
  }
}
