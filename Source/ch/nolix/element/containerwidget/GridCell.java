//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.elementapi.IMutableElement;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.gui.Widget;

//class
final class GridCell extends Element<GridCell> implements Clearable, IMutableElement<GridCell> {

	//optional attribute
	private Widget<?, ?> widget;
	private final int rowIndex;
	private final int columnIndex;
	
	//constructor
	public GridCell(final int rowIndex, final int columnIndex) {
		
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		
		reset();
	}
	
	//constructor
	public GridCell(
		final int rowIndex,
		final int columnIndex,
		final Widget<?, ?> widget
	) {
		
		this(rowIndex, columnIndex);
		
		setWidget(widget);
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setWidget(WidgetGUI.createWidgetFrom(attribute));
			return;
		}
		
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	@Override
	public void clear() {
		widget = null;
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes =
		LinkedList.withElements(
			Node.withHeader(getRowIndex()),
			Node.withHeader(getColumnIndex())
		);
		
		if (containsAny()) {
			attributes.addAtEnd(getRefWidget().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	public int getColumnIndex() {
		return columnIndex;
	}

	//method
	public int getRowIndex() {
		return rowIndex;
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
		
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		return widget;
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {	
		clear();
	}
	
	//method
	public void resetConfiguration() {
		if (containsAny()) {
			getRefWidget().resetConfiguration();
		}
	}
	
	//method
	public GridCell setWidget(final Widget<?, ?> widget) {
		
		Validator.assertThat(widget).isOfType(Widget.class);
		
		this.widget = widget;
		
		return this;
	}
}
