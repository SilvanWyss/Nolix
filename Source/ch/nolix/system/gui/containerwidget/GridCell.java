//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.system.element.mutableelement.MutableElement;
import ch.nolix.system.element.mutableelement.Value;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.MutableOptionalWidgetProperty;

//class
final class GridCell extends MutableElement<GridCell> implements Clearable {
	
	//constants
	private static final String ROW_INDEX_HEADER = PascalCaseCatalogue.ROW_INDEX;
	private static final String COLUMN_INDEX_HEADER = PascalCaseCatalogue.COLUMN_INDEX;
	
	//static method
	public static GridCell fromSpecification(final INode<?> specification) {
		
		final var cell = new GridCell();
		cell.resetFromSpecification(specification);
		
		return cell;
	}
	
	//attributes
	private final MutableOptionalWidgetProperty widget = new MutableOptionalWidgetProperty(this::setWidget);
	
	//attributes
	private final Value<Integer> rowIndex = Value.forInt(ROW_INDEX_HEADER, this::setRowIndex);
	private final Value<Integer> columnIndex = Value.forInt(COLUMN_INDEX_HEADER, this::setColumnIndex);
	
	//constructor
	public GridCell(final int rowIndex, final int columnIndex) {
		setRowIndex(rowIndex);
		setColumnIndex(columnIndex);
	}
	
	//constructor
	private GridCell() {}
	
	//method
	@Override
	public void clear() {
		widget.clear();
	}
	
	//method
	public int getColumnIndex() {
		return columnIndex.getValue();
	}

	//method
	public int getRowIndex() {
		return rowIndex.getValue();
	}
	
	//method
	public int getHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefWidget().getHeight();
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		return widget.getRefWidget();
	}
	
	//method
	public int getWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefWidget().getWidth();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	@Override
	public void reset() {
		clear();
	}
	
	//method
	public GridCell setWidget(final Widget<?, ?> widget) {
		
		this.widget.setWidget(widget);
		
		return this;
	}
	
	//method
	private void setColumnIndex(final int columnIndex) {
	
		GlobalValidator.assertThat(columnIndex).thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX).isPositive();
		
		this.columnIndex.setValue(columnIndex);
	}
	
	//method
	private void setRowIndex(final int rowIndex) {
		
		GlobalValidator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isPositive();
		
		this.rowIndex.setValue(rowIndex);
	}
}
