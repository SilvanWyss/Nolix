package ch.nolix.system.webcontainercontrol.grid;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.coreapi.state.statemutation.Clearable;
import ch.nolix.system.element.mutableelement.AbstractMutableElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.Value;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webgui.main.IControl;

/**
 * @author Silvan Wyss
 */
public final class GridCell extends AbstractMutableElement implements Clearable {
  private static final String ROW_INDEX_HEADER = PascalCaseVariableCatalog.ROW_INDEX;

  private static final String COLUMN_INDEX_HEADER = PascalCaseVariableCatalog.COLUMN_INDEX;

  private static final String CONTROL_HEADER = "Control";

  private final Value<Integer> rowIndex = Value.forInt(ROW_INDEX_HEADER, this::setRowIndex);

  private final Value<Integer> columnIndex = Value.forInt(COLUMN_INDEX_HEADER, this::setColumnIndex);

  private final MutableOptionalValue<IControl<?, ?>> control = new MutableOptionalValue<>(
    CONTROL_HEADER,
    this::setControl,
    ControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  private GridCell() {
  }

  public static GridCell fromSpecification(final INode<?> specification) {
    final var cell = new GridCell();
    cell.resetFromSpecification(specification);

    return cell;
  }

  public static GridCell withOneBasedRowIndexAndColumnIndex(final int rowIndex, final int columnIndex) {
    final var cell = new GridCell();
    cell.setRowIndex(rowIndex);
    cell.setColumnIndex(columnIndex);

    return cell;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    control.clear();
  }

  public int getColumnIndex() {
    return columnIndex.getValue();
  }

  public IControl<?, ?> getStoredControl() {
    return control.getValue();
  }

  public int getRowIndex() {
    return rowIndex.getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return control.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void reset() {
    clear();
  }

  public void setControl(final IControl<?, ?> control) {
    this.control.setValue(control);
  }

  private void setColumnIndex(final int columnIndex) {
    Validator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableCatalog.COLUMN_INDEX).isPositive();

    this.columnIndex.setValue(columnIndex);
  }

  private void setRowIndex(final int rowIndex) {
    Validator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableCatalog.ROW_INDEX).isPositive();

    this.rowIndex.setValue(rowIndex);
  }
}
