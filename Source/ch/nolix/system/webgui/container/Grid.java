//package declaration
package ch.nolix.system.webgui.container;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.system.element.mutableelement.MultiValueExtractor;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.webguiapi.containerapi.IGrid;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCssRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHtmlBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;
import ch.nolix.systemapi.webguiapi.mainapi.IHtmlElementEvent;

//class
public final class Grid
extends Container<Grid, GridStyle>
implements IGrid<Grid, GridStyle> {
	
	//constant
	private static final String CELL_HEADER = PascalCaseCatalogue.CELL;
	
	//constant
	private static final GridHtmlBuilder HTML_BUILDER = new GridHtmlBuilder();
	
	//constant
	private static final GridCssRuleBuilder CSS_RULE_BUILDER = new GridCssRuleBuilder();
	
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<>();
	
	//attribute
	@SuppressWarnings("unused")
	private final MultiValueExtractor<GridCell> cellExtractor =
	new MultiValueExtractor<>(
		CELL_HEADER,
		this::addCell,
		() -> cells.getOriSelected(GridCell::containsAny),
		GridCell::fromSpecification,
		GridCell::getSpecification
	);
	
	//constructor
	public Grid() {
		getOriStyle()
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
		return cells.getOriAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex).containsAny();
	}
	
	//method
	@Override
	public int getColumnCount() {
		return cells.getColumnCount();
	}
	
	//method
	@Override
	public IControl<?, ?> getOriChildControlAt1BasedRowAndColumnIndex(
		final int p1BasedRowIndex,
		final int p1BasedColumnIndex
	) {
		return cells.getOriAt1BasedRowIndexAndColumnIndex(p1BasedRowIndex, p1BasedColumnIndex).getOriControl();
	}
	
	//method
	@Override
	public int getRowCount() {
		return cells.getRowCount();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getOriChildControls() {
		
		final var childControls = new LinkedList<IControl<?, ?>>();
		for (final var c : cells) {
			if (c.containsAny()) {
				childControls.addAtEnd(c.getOriControl());
			}
		}
		
		return childControls;
	}
	
	//method
	@Override
	public Grid insertControlAtRowAndColumn(
		final int rowIndex,
		final int columnIndex,
		final IControl<?, ?> control
	) {
		
		final var cell = GridCell.withRowIndexAndColumnIndex(rowIndex, columnIndex);
		cell.setControl(control);
		addCell(cell);
		
		cell.getOriControl().technicalSetParentControl(this);
		
		return this;
	}
	
	//method
	@Override
	public Grid insertTextAtRowAndColumn(final int rowIndex, final int columnIndex, final String text) {
		
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
	protected IControlCssRuleBuilder<Grid, GridStyle> getCssRuleCreator() {
		return CSS_RULE_BUILDER;
	}
	
	//method
	@Override
	protected IControlHtmlBuilder<Grid> getHtmlBuilder() {
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
		
		GlobalValidator.assertThat(columnIndex).thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX).isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(GridCell.withRowIndexAndColumnIndex(1, 1));
		}
		
		for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {
			
			final var column = new LinkedList<GridCell>();
			
			for (var ri = 1; ri <= getRowCount(); ri++) {
				column.addAtEnd(GridCell.withRowIndexAndColumnIndex(ri, ci));
			}
			
			cells.addColumn(column);
		}
	}
	
	//method
	private void expandRowsTo(final int rowIndex) {
		
		GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(GridCell.withRowIndexAndColumnIndex(1, 1));
		}
		
		for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {
			
			final var row = new LinkedList<GridCell>();
			
			for (var ci = 1; ci <= getColumnCount(); ci++) {
				row.addAtEnd(GridCell.withRowIndexAndColumnIndex(ri, ci));
			}
			
			cells.addRow(row);
		}
	}
}