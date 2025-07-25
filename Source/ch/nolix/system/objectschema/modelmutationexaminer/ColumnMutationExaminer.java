package ch.nolix.system.objectschema.modelmutationexaminer;

import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.modelmutationexaminer.IColumnMutationExaminer;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 */
public final class ColumnMutationExaminer implements IColumnMutationExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canBeDeleted(final IColumn column) {
    return //
    column != null
    && column.isOpen()
    && !column.isDeleted()
    && !column.isBackReferenced();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetContentModel(final IColumn column, final IContentModel contentModel) {
    return column != null
    && column.isOpen()
    && column.isEmpty()
    && !column.isBackReferenced();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean canSetName(final IColumn column, final String name) {
    return //
    canSetName(name)
    && column != null
    && column.isOpen()
    && canSetNameBecauseOfOptionalParentTable(column, name);
  }

  /**
   * @param column
   * @param name
   * @return true if the given name can be set to the given column because of the
   *         optional parent {@link ITable} of the given column, false otherwise.
   */
  private boolean canSetNameBecauseOfOptionalParentTable( //NOSONAR: This method is an instance method.
    final IColumn column,
    final String name) {
    return //
    column != null
    && (!column.belongsToTable() || column.getStoredParentTable().getStoredColumns().containsAny(c -> c.hasName(name)));
  }

  /**
   * @param name
   * @return true if the given name can be set to a {@link IColumn}, false
   *         otherwise.
   */
  private boolean canSetName(final String name) { //NOSONAR: This method is an instance method.
    return //
    name != null
    && !name.isBlank();
  }
}
