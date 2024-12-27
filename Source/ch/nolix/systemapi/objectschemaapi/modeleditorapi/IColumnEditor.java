package ch.nolix.systemapi.objectschemaapi.modeleditorapi;

import ch.nolix.system.objectschema.model.Column;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 * @param <C> is the type of {@link IColumn}s a {@link IColumnEditor} can edit.
 */
public interface IColumnEditor<C extends IColumn> {

  /**
   * Lets the current {@link IColumnEditor} delete the given column.
   * 
   * @param column
   * @throws RuntimeException if the current {@link IColumnEditor} cannot delete
   *                          the given column.
   */
  void deleteColumn(C column);

  /**
   * Lets the current {@link IColumnEditor} set the given contentModel to the
   * given column.
   * 
   * @param column
   * @param contentModel
   * @throws RuntimeException if the current {@link IColumnEditor} cannot set the
   *                          given contentModel to the given column
   */
  void setContentModelToColumn(Column column, IContentModel contentModel);

  /**
   * Lets the current {@link IColumnEditor} set the given name to the given
   * column.
   * 
   * @param column
   * @param name
   * @throws RuntimeException if the current {@link IColumnEditor} cannot set the
   *                          given name to the given column.
   */
  void setNameToColumn(Column column, String name);
}
