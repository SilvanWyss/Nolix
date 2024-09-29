//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.system.element.property.MultiValueExtractor;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.basecontainer.Container;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.containerapi.IGridStyle;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlCssBuilder;
import ch.nolix.systemapi.webguiapi.controltoolapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Grid extends Container<IGrid, IGridStyle> implements IGrid {

  //constant
  private static final String CELL_HEADER = PascalCaseVariableCatalogue.CELL;

  //constant
  private static final GridHtmlBuilder HTML_BUILDER = new GridHtmlBuilder();

  //constant
  private static final GridCssBuilder CSS_BUILDER = new GridCssBuilder();

  //multi-attribute
  private Matrix<GridCell> cells = Matrix.createEmpty();

  //attribute
  @SuppressWarnings("unused")
  private final MultiValueExtractor<GridCell> cellExtractor = new MultiValueExtractor<>(
    CELL_HEADER,
    this::addCell,
    () -> cells.getStoredSelected(GridCell::containsAny),
    GridCell::fromSpecification,
    GridCell::getSpecification);

  //constructor
  public Grid() {
    getStoredStyle()
      .setGridThicknessForState(ControlState.BASE, 1)
      .setChildControlMarginForState(ControlState.BASE, 10);
  }

  //method
  @Override
  public void clear() {
    cells.clear();
  }

  //method
  @Override
  public boolean containsControlAt1BasedRowAndColumnIndex(final int p1BasedRowIndex, final int p1BasedColumnIndex) {
    return cells.getStoredAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex).containsAny();
  }

  //method
  @Override
  public int getColumnCount() {
    return cells.getColumnCount();
  }

  //method
  @Override
  public IControl<?, ?> getStoredChildControlAt1BasedRowAndColumnIndex(
    final int p1BasedRowIndex,
    final int p1BasedColumnIndex) {
    return cells.getStoredAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex).getStoredControl();
  }

  //method
  @Override
  public int getRowCount() {
    return cells.getRowCount();
  }

  //method
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

  //method
  @Override
  public IGrid insertControlAtRowAndColumn(
    final int param1BasedRowIndex,
    final int param1BasedColumnIndex,
    final IControl<?, ?> control) {

    final var cell = GridCell.with1BasedRowIndexAndColumnIndex(param1BasedRowIndex, param1BasedColumnIndex);
    cell.setControl(control);
    addCell(cell);

    cell.getStoredControl().internalSetParentControl(this);

    return this;
  }

  //method
  @Override
  public IGrid insertTextAtRowAndColumn(final int rowIndex, final int columnIndex, final String text) {

    final var textControl = new Label().setText(text);

    return insertControlAtRowAndColumn(rowIndex, columnIndex, textControl);
  }

  //method
  @Override
  public boolean isEmpty() {
    return cells.isEmpty();
  }

  //method
  @Override
  public void registerHtmlElementEventsAt(final ILinkedList<IHtmlElementEvent> list) {
    //Does nothing.
  }

  //method
  @Override
  protected GridStyle createStyle() {
    return new GridStyle();
  }

  //method
  @Override
  protected IControlCssBuilder<IGrid, IGridStyle> getCssBuilder() {
    return CSS_BUILDER;
  }

  //method
  @Override
  protected IControlHtmlBuilder<IGrid> getHtmlBuilder() {
    return HTML_BUILDER;
  }

  //method
  @Override
  protected void resetContainer() {
    clear();
  }

  //method
  private void addCell(final GridCell cell) {

    expandTo(cell.getRowIndex(), cell.getColumnIndex());

    cells.setAt1BasedRowIndexAndColumnIndex(cell.getRowIndex(), cell.getColumnIndex(), cell);
  }

  //method
  private void expandTo(final int rowCount, final int columnCount) {
    expandRowsTo(rowCount);
    expandColumnsTo(columnCount);
  }

  //method
  private void expandColumnsTo(final int columnIndex) {

    GlobalValidator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableCatalogue.COLUMN_INDEX).isPositive();

    if (cells.isEmpty()) {
      cells.addRow(GridCell.with1BasedRowIndexAndColumnIndex(1, 1));
    }

    for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {

      final ILinkedList<GridCell> column = LinkedList.createEmpty();

      for (var ri = 1; ri <= getRowCount(); ri++) {
        column.addAtEnd(GridCell.with1BasedRowIndexAndColumnIndex(ri, ci));
      }

      cells.addColumn(column);
    }
  }

  //method
  private void expandRowsTo(final int rowIndex) {

    GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableCatalogue.ROW_INDEX).isPositive();

    if (cells.isEmpty()) {
      cells.addRow(GridCell.with1BasedRowIndexAndColumnIndex(1, 1));
    }

    for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {

      final ILinkedList<GridCell> row = LinkedList.createEmpty();

      for (var ci = 1; ci <= getColumnCount(); ci++) {
        row.addAtEnd(GridCell.with1BasedRowIndexAndColumnIndex(ri, ci));
      }

      cells.addRow(row);
    }
  }
}
