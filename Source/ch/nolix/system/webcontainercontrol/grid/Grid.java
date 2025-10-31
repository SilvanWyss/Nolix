package ch.nolix.system.webcontainercontrol.grid;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MultiValueExtractor;
import ch.nolix.system.webatomiccontrol.label.Label;
import ch.nolix.system.webgui.basecontainer.AbstractContainer;
import ch.nolix.systemapi.webcontainercontrol.grid.IGrid;
import ch.nolix.systemapi.webcontainercontrol.grid.IGridStyle;
import ch.nolix.systemapi.webgui.controltool.IControlCssBuilder;
import ch.nolix.systemapi.webgui.controltool.IControlHtmlBuilder;
import ch.nolix.systemapi.webgui.main.ControlState;
import ch.nolix.systemapi.webgui.main.IControl;
import ch.nolix.systemapi.webgui.main.IHtmlElementEvent;

public final class Grid extends AbstractContainer<IGrid, IGridStyle> implements IGrid {
  private static final String CELL_HEADER = PascalCaseVariableCatalog.CELL;

  private static final GridHtmlBuilder HTML_BUILDER = new GridHtmlBuilder();

  private static final GridCssBuilder CSS_BUILDER = new GridCssBuilder();

  private Matrix<GridCell> cells = Matrix.createEmpty();

  @SuppressWarnings("unused")
  private final MultiValueExtractor<GridCell> cellExtractor = new MultiValueExtractor<>(
    CELL_HEADER,
    this::addCell,
    () -> cells.getStoredSelected(GridCell::containsAny),
    GridCell::fromSpecification,
    GridCell::getSpecification);

  public Grid() {
    getStoredStyle()
      .setGridThicknessForState(ControlState.BASE, 1)
      .setChildControlMarginForState(ControlState.BASE, 10);
  }

  @Override
  public void clear() {
    cells.clear();
  }

  @Override
  public boolean containsControlAtOneBasedRowAndColumnIndex(final int oneBasedRowIndex,
    final int oneBasedColumnIndex) {
    return cells.getStoredAtOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex).containsAny();
  }

  @Override
  public int getColumnCount() {
    return cells.getColumnCount();
  }

  @Override
  public IControl<?, ?> getStoredChildControlAtOneBasedRowAndColumnIndex(
    final int oneBasedRowIndex,
    final int oneBasedColumnIndex) {
    return cells.getStoredAtOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex)
      .getStoredControl();
  }

  @Override
  public int getRowCount() {
    return cells.getRowCount();
  }

  @Override
  public IContainer<IControl<?, ?>> getStoredChildControls() {
    final ILinkedList<IControl<?, ?>> childControls = LinkedList.createEmpty();
    for (final var c : cells) {
      if (c.containsAny()) {
        childControls.addAtEnd(c.getStoredControl());
      }
    }

    return childControls;
  }

  @Override
  public IGrid insertControlAtRowAndColumn(
    final int oneBasedRowIndex,
    final int oneBasedColumnIndex,
    final IControl<?, ?> control) {
    final var cell = GridCell.withOneBasedRowIndexAndColumnIndex(oneBasedRowIndex, oneBasedColumnIndex);
    cell.setControl(control);
    addCell(cell);

    cell.getStoredControl().internalSetParentControl(this);

    return this;
  }

  @Override
  public IGrid insertTextAtRowAndColumn(final int rowIndex, final int columnIndex, final String text) {
    final var textControl = new Label().setText(text);

    return insertControlAtRowAndColumn(rowIndex, columnIndex, textControl);
  }

  @Override
  public boolean isEmpty() {
    return cells.isEmpty();
  }

  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  @Override
  protected GridStyle createStyle() {
    return new GridStyle();
  }

  @Override
  protected IControlCssBuilder<IGrid, IGridStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  @Override
  protected IControlHtmlBuilder<IGrid> getHtmlBuilder() {
    return HTML_BUILDER;
  }

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
    Validator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableCatalog.COLUMN_INDEX).isPositive();

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
    Validator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableCatalog.ROW_INDEX).isPositive();

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
