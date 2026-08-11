/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.grid;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.datastructure.matrix.MutableMatrix;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.control.container.AbstractContainer;
import ch.nolix.system.control.label.Label;
import ch.nolix.system.element.proxyproperty.MultiValueProxyProperty;
import ch.nolix.system.webgui.main.ControlParent;
import ch.nolix.systemapi.control.grid.IGrid;
import ch.nolix.systemapi.control.grid.IGridStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.html.IHtmlElementEvent;
import ch.nolix.systemapi.webgui.main.Control;
import ch.nolix.systemapi.webgui.webguiproperty.ControlState;

/**
 * @author Silvan Wyss
 */
public final class Grid extends AbstractContainer<IGrid, IGridStyle> implements IGrid {
  private static final String CELL_HEADER = PascalCaseVariableNameCatalog.CELL;

  private static final GridHtmlBuilder HTML_BUILDER = new GridHtmlBuilder();

  private static final GridCssBuilder CSS_BUILDER = new GridCssBuilder();

  private MutableMatrix<GridCell> cells = MutableMatrix.createEmpty();

  @SuppressWarnings("unused")
  private final MultiValueProxyProperty<GridCell> cellExtractor = //
  MultiValueProxyProperty.withNameAndAdderAndGetterAndValueMapperAndSpecificationMapper(
    CELL_HEADER,
    this::addCell,
    () -> cells.getViewOfStoredSelected(GridCell::containsAny),
    GridCell::fromSpecification,
    GridCell::getSpecification);

  public Grid() {
    getStoredStyle()
      .setGridThicknessForState(ControlState.BASE, 1)
      .setChildControlMarginForState(ControlState.BASE, 10);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    cells.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean containsControlAtOneBasedRowAndColumnIndex(final int oneBasedRowIndex,
    final int oneBasedColumnIndex) {
    return cells.getStoredAtOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex).containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getColumnCount() {
    return cells.getColumnCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Control<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(
    final int oneBasedRowIndex,
    final int oneBasedColumnIndex) {
    return cells.getStoredAtOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex)
      .getStoredControl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getRowCount() {
    return cells.getRowCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredChildControls() {
    final ILinkedList<Control<?, ?>> childControls = LinkedList.createEmpty();
    for (final var c : cells) {
      if (c.containsAny()) {
        childControls.addAtEnd(c.getStoredControl());
      }
    }

    return childControls;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Control<?, ?>> getStoredStructureControls() {
    return getStoredChildControls();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGrid insertControlAtRowAndColumn(
    final int oneBasedRowIndex,
    final int oneBasedColumnIndex,
    final Control<?, ?> control) {
    final var controlParent = ControlParent.forControl(this);
    final var cell = GridCell.withOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex);

    cell.setControl(control);
    addCell(cell);
    cell.getStoredControl().internalSetControlParent(controlParent);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IGrid insertTextAtRowAndColumn(final int rowIndex, final int columnIndex, final String text) {
    final var textControl = new Label().setText(text);

    return insertControlAtRowAndColumn(rowIndex, columnIndex, textControl);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return cells.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected GridStyle createStyle() {
    return new GridStyle();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlCssBuilder<IGrid, IGridStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IControlHtmlBuilder<IGrid> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void resetContainer() {
    clear();
  }

  private void addCell(final GridCell cell) {
    expandTo(cell.getRowIndex(), cell.getColumnIndex());

    cells.setAtOneBasedRowIndexAndColumnIndex(cell.getRowIndex(), cell.getColumnIndex(), cell);
  }

  private void expandTo(final int rowCount, final int columnCount) {
    expandRowsTo(rowCount);
    expandColumnsTo(columnCount);
  }

  private void expandColumnsTo(final int columnIndex) {
    Validator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableNameCatalog.COLUMN_INDEX).isPositive();

    if (cells.isEmpty()) {
      cells.addRow(GridCell.withOneBasedRowIndexAndColumnIndex(1, 1));
    }

    for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {
      final ILinkedList<GridCell> column = LinkedList.createEmpty();

      for (var ri = 1; ri <= getRowCount(); ri++) {
        column.addAtEnd(GridCell.withOneBasedRowIndexAndColumnIndex(ri, ci));
      }

      cells.addColumn(column);
    }
  }

  private void expandRowsTo(final int rowIndex) {
    Validator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableNameCatalog.ROW_INDEX).isPositive();

    if (cells.isEmpty()) {
      cells.addRow(GridCell.withOneBasedRowIndexAndColumnIndex(1, 1));
    }

    for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {
      final ILinkedList<GridCell> row = LinkedList.createEmpty();

      for (var ci = 1; ci <= getColumnCount(); ci++) {
        row.addAtEnd(GridCell.withOneBasedRowIndexAndColumnIndex(ri, ci));
      }

      cells.addRow(row);
    }
  }
}
