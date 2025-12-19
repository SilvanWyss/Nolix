package ch.nolix.systemapi.objectschema.modeleditor;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.objectschema.model.IColumn;
import ch.nolix.systemapi.objectschema.model.ITable;

/**
 * @author Silvan Wyss
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
   * @param fieldType
   * @param dataType
   * @param referenceableTables
   * @param backReferenceableColumns
   * @throws RuntimeException if the current {@link IColumnEditor} cannot set the
   *                          given contentModel to the given column
   */
  void setContentModelToColumn(
    C column,
    FieldType fieldType,
    DataType dataType,
    IContainer<? extends ITable> referenceableTables,
    IContainer<? extends IColumn> backReferenceableColumns);

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
