//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalogue;
import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.system.element.property.Value;
import ch.nolix.system.webgui.main.GlobalControlFactory;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class GridCell extends MutableElement implements Clearable {

  //constant
  private static final String ROW_INDEX_HEADER = PascalCaseVariableCatalogue.ROW_INDEX;

  //constant
  private static final String COLUMN_INDEX_HEADER = PascalCaseVariableCatalogue.COLUMN_INDEX;

  //constant
  private static final String CONTROL_HEADER = "Control";

  //attribute
  private final Value<Integer> rowIndex = Value.forInt(ROW_INDEX_HEADER, this::setRowIndex);

  //attribute
  private final Value<Integer> columnIndex = Value.forInt(COLUMN_INDEX_HEADER, this::setColumnIndex);

  //attribute
  private final MutableOptionalValue<IControl<?, ?>> control = new MutableOptionalValue<>(
    CONTROL_HEADER,
    this::setControl,
    GlobalControlFactory::createControlFromSpecification,
    IControl::getSpecification);

  //constructor
  private GridCell() {
  }

  //static method
  public static GridCell fromSpecification(final INode<?> specification) {

    final var cell = new GridCell();
    cell.resetFromSpecification(specification);

    return cell;
  }

  //static method
  public static GridCell with1BasedRowIndexAndColumnIndex(final int rowIndex, final int columnIndex) {

    final var cell = new GridCell();
    cell.setRowIndex(rowIndex);
    cell.setColumnIndex(columnIndex);

    return cell;
  }

  //method
  @Override
  public void clear() {
    control.clear();
  }

  //method
  public int getColumnIndex() {
    return columnIndex.getValue();
  }

  //method
  public IControl<?, ?> getStoredControl() {
    return control.getValue();
  }

  //method
  public int getRowIndex() {
    return rowIndex.getValue();
  }

  //method
  @Override
  public boolean isEmpty() {
    return control.isEmpty();
  }

  //method
  @Override
  public void reset() {
    clear();
  }

  //method
  public void setControl(final IControl<?, ?> control) {
    this.control.setValue(control);
  }

  //method
  private void setColumnIndex(final int columnIndex) {

    GlobalValidator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableCatalogue.COLUMN_INDEX).isPositive();

    this.columnIndex.setValue(columnIndex);
  }

  //method
  private void setRowIndex(final int rowIndex) {

    GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableCatalogue.ROW_INDEX).isPositive();

    this.rowIndex.setValue(rowIndex);
  }
}
