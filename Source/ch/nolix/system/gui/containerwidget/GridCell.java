//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.skillapi.Clearable;
import ch.nolix.system.element.MutableElement;
import ch.nolix.system.element.Value;
import ch.nolix.system.gui.base.MutableOptionalWidgetProperty;
import ch.nolix.system.gui.widget.Widget;

//class
final class GridCell extends MutableElement<GridCell> implements Clearable {
	
	//constants
	private static final String ROW_INDEX_HEADER = PascalCaseCatalogue.ROW_INDEX;
	private static final String COLUMN_INDEX_HEADER = PascalCaseCatalogue.COLUMN_INDEX;
	
	//static method
	public static GridCell fromSpecification(final BaseNode specification) {
		
		final var cell = new GridCell();
		cell.resetFrom(specification);
		
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
	
		Validator.assertThat(columnIndex).thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX).isPositive();
		
		this.columnIndex.setValue(columnIndex);
	}
	
	//method
	private void setRowIndex(final int rowIndex) {
		
		Validator.assertThat(rowIndex).thatIsNamed(LowerCaseCatalogue.ROW_INDEX).isPositive();
		
		this.rowIndex.setValue(rowIndex);
	}
}
