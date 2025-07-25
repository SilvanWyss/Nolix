package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelexaminer.IEntityExaminer;
import ch.nolix.systemapi.objectdata.modelexaminer.ITableExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class TableExaminer implements ITableExaminer {

  private static final IEntityExaminer ENTITY_EXAMINER = new EntityExaminer();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final ITable<?> table) {
    return table
      .internalGetStoredEntitiesInLocalData()
      .containsOnly(
        e -> ENTITY_EXAMINER.allNewAndEditedMandatoryFieldsAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
      );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canInsertEntity(final ITable<?> table) {
    return table.isOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canInsertGivenEntity(ITable<?> table, IEntity entity) {
    return canInsertEntity(table)
    && ENTITY_EXAMINER.canBeInsertedIntoTable(entity)
    && !hasInsertedGivenEntityInLocalData(table, entity);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsEntityWithGivenIdInLocalData(final ITable<?> table, final String id) {
    return table.internalGetStoredEntitiesInLocalData().containsAny(e -> e.hasId(id));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasInsertedGivenEntityInLocalData(final ITable<?> table, final IEntity entity) {
    return containsEntityWithGivenIdInLocalData(table, entity.getId());
  }
}
