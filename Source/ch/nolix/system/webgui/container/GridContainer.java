//package declaration
package ch.nolix.system.webgui.container;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.system.element.mutableelement.MultiValueExtractor;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.systemapi.webguiapi.containerapi.IGridContainer;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlCSSRuleBuilder;
import ch.nolix.systemapi.webguiapi.controlcomponentapi.IControlHTMLBuilder;
import ch.nolix.systemapi.webguiapi.mainapi.IControl;

//class
public final class GridContainer
extends Container<GridContainer, GridContainerStyle>
implements IGridContainer<GridContainer, GridContainerStyle> {
	
	//constant
	private static final String CELL_HEADER = PascalCaseCatalogue.CELL;
	
	//multi-attribute
	private Matrix<GridContainerCell> cells = new Matrix<>();
	
	//attribute
	@SuppressWarnings("unused")
	private final MultiValueExtractor<GridContainerCell> cellExtractor =
	new MultiValueExtractor<>(
		CELL_HEADER,
		this::addCell,
		() -> cells.getRefSelected(GridContainerCell::containsAny),
		GridContainerCell::fromSpecification,
		GridContainerCell::getSpecification
	);
	
	//method
	@Override
	public void clear() {
		cells.clear();
	}
	
	//method
	@Override
	public boolean containsControlAtRowAndColumn(final int rowIndex, final int columnIndex) {
		return cells.getRefAt(rowIndex, columnIndex).containsAny();
	}
	
	//method
	@Override
	public int getColumnCount() {
		return cells.getColumnCount();
	}
	
	//method
	@Override
	public IControl<?, ?> getRefChildControlAtRowAndColumn(final int rowIndex, final int columnIndex) {
		return cells.getRefAt(rowIndex, columnIndex).getRefControl();
	}
	
	//method
	@Override
	public int getRowCount() {
		return cells.getRowCount();
	}
	
	//method
	@Override
	public IContainer<IControl<?, ?>> getRefChildControls() {
		
		final var childControls = new LinkedList<IControl<?, ?>>();
		for (final var c : cells) {
			if (c.containsAny()) {
				childControls.addAtEnd(c.getRefControl());
			}
		}
		
		return childControls;
	}
	
	//method
	@Override
	public GridContainer insertControlAtRowAndColumn(
		final int rowIndex,
		final int columnIndex,
		final IControl<?, ?> control
	) {
		
		final var cell = GridContainerCell.withRowIndexAndColumnIndex(rowIndex, columnIndex);
		cell.setControl(control);
		addCell(cell);
		
		cell.getRefControl().technicalSetParentControl(this);
		
		return this;
	}
	
	//method
	@Override
	public GridContainer insertTextAtRowAndColumn(final int rowIndex, final int columnIndex, final String text) {
		
		final var textControl = new Text().setText(text);
		
		return insertControlAtRowAndColumn(rowIndex, columnIndex, textControl);
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return cells.isEmpty();
	}
	
	//method
	@Override
	protected GridContainerStyle createStyle() {
		return new GridContainerStyle();
	}
	
	//method
	@Override
	protected IControlCSSRuleBuilder<GridContainer, GridContainerStyle> getCSSRuleCreator() {
		return GridContainerCSSRuleBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected IControlHTMLBuilder<GridContainer> getHTMLBuilder() {
		return GridContainerHTMLBuilder.INSTANCE;
	}
	
	//method
	@Override
	protected void resetContainer() {
		clear();
	}
	
	//method
	private void addCell(final GridContainerCell cell) {
		
		expandTo(cell.getRowIndex(), cell.getColumnIndex());
		
		cells.setAt(cell.getRowIndex(), cell.getColumnIndex(), cell);
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
			cells.addRow(GridContainerCell.withRowIndexAndColumnIndex(1, 1));
		}
		
		for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {
			
			final var column = new LinkedList<GridContainerCell>();
			
			for (var ri = 1; ri <= getRowCount(); ri++) {
				column.addAtEnd(GridContainerCell.withRowIndexAndColumnIndex(ri, ci));
			}
			
			cells.addColumn(column);
		}
	}
	
	//method
	private void expandRowsTo(final int rowIndex) {
		
		GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(GridContainerCell.withRowIndexAndColumnIndex(1, 1));
		}
		
		for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {
			
			final var row = new LinkedList<GridContainerCell>();
			
			for (var ci = 1; ci <= getColumnCount(); ci++) {
				row.addAtEnd(GridContainerCell.withRowIndexAndColumnIndex(ri, ci));
			}
			
			cells.addRow(row);
		}
	}
}
