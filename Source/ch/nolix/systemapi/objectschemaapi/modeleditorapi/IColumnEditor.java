package ch.nolix.systemapi.objectschemaapi.modeleditorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
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
   * Lets the current {@link IColumnEditor} set the given contentModels to the
   * given column.
   * 
   * @param column
   * @param contentModels
   * @throws RuntimeException if the current {@link IColumnEditor} cannot set one
   *                          of the given contentModels to the given column
   */
  void setContentModelsToColumn(C column, IContainer<IContentModel> contentModels);

  /**
   * Lets the current {@link IColumnEditor} set the given name to the given
   * column.
   * 
   * @param column
   * @param name
   * @throws RuntimeException if the current {@link IColumnEditor} cannot set the
   *                          given name to the given column.
   */
  void setNameToColumn(C column, String name);
}
