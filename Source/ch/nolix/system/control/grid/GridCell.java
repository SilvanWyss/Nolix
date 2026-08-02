/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.control.grid;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;
import ch.nolix.system.element.propertieselement.AbstractPropertiesElement;
import ch.nolix.system.property.value.OptionalValue;
import ch.nolix.system.property.value.Value;
import ch.nolix.system.webgui.main.ControlFactory;
import ch.nolix.systemapi.webgui.main.Control;

/**
 * @author Silvan Wyss
 */
public final class GridCell extends AbstractPropertiesElement implements Clearable {
  private static final String ROW_INDEX_HEADER = PascalCaseVariableNameCatalog.ROW_INDEX;

  private static final String COLUMN_INDEX_HEADER = PascalCaseVariableNameCatalog.COLUMN_INDEX;

  private static final String CONTROL_HEADER = "Control";

  private final Value<Integer> rowIndex = //
  Value.forIntWithNameAndDefaultValueAndSetter(ROW_INDEX_HEADER, 1, this::setRowIndex);

  private final Value<Integer> columnIndex = //
  Value.forIntWithNameAndDefaultValueAndSetter(COLUMN_INDEX_HEADER, 1, this::setColumnIndex);

  private final OptionalValue<Control<?, ?>> control = //
  OptionalValue.withNameAndSetterAndValueMapperAndSpecificationMapper(
    CONTROL_HEADER,
    this::setControl,
    ControlFactory::createControlFromSpecification,
    Control::getSpecification);

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
    return columnIndex.getStoredValue();
  }

  public Control<?, ?> getStoredControl() {
    return control.getStoredValue();
  }

  public int getRowIndex() {
    return rowIndex.getStoredValue();
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

  public void setControl(final Control<?, ?> control) {
    this.control.setValue(control);
  }

  private void setColumnIndex(final int columnIndex) {
    Validator.assertThat(columnIndex).thatIsNamed(LowerCaseVariableNameCatalog.COLUMN_INDEX).isPositive();

    this.columnIndex.setValue(columnIndex);
  }

  private void setRowIndex(final int rowIndex) {
    Validator.assertThat(rowIndex).thatIsNamed(LowerCaseVariableNameCatalog.ROW_INDEX).isPositive();

    this.rowIndex.setValue(rowIndex);
  }
}
