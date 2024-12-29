package ch.nolix.system.objectdata.modelexaminer;

import ch.nolix.system.objectdata.datatool.EntityTool;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.modelexaminerapi.ITableExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-29
 */
public final class TableExaminer implements ITableExaminer {

  private static final IEntityTool ENTITY_TOOL = new EntityTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean allNewAndEditedMandatoryFieldsAreSet(final ITable<?> table) {
    return table
      .internalGetStoredEntitiesInLocalData()
      .containsOnly(
        e -> ENTITY_TOOL.allNewAndEditedMandatoryFieldsAreSet(e) //NOSONAR: A method reference will rise a BootstrapMethodError.
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
    && ENTITY_TOOL.canBeInsertedIntoTable(entity)
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
