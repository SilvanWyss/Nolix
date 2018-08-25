//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.skillInterfaces.Clearable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.core.MutableElement;
import ch.nolix.primitive.invalidStateException.EmptyStateException;
import ch.nolix.primitive.validator2.Validator;

//package-visible class
final class GridCell
extends MutableElement<GridCell>
implements Clearable<GridCell> {

	//optional attribute
	private Widget<?, ?> widget;
	private final int rowIndex;
	private final int columnIndex;
	
	//constructor
	public GridCell(final int rowIndex, final int columnIndex) {
		
		Validator
		.suppose(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		Validator
		.suppose(columnIndex)
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
	public void addOrChangeAttribute(final Specification attribute) {
		
		if (GUI.canCreateWidget(attribute)) {
			setWidget(GUI.createWidget(attribute));
			return;
		}
		
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	public GridCell clear() {
		
		widget = null;
		
		return this;
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
		final var attributes = new List<StandardSpecification>(
			StandardSpecification.createFromInt(getRowIndex()),
			StandardSpecification.createFromInt(getColumnIndex())
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
			throw new EmptyStateException(this);
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
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	/**
	 * Resets this grid cell.
	 * 
	 * @return this grid cell.
	 */
	public GridCell reset() {
		
		clear();
		
		return this;
	}
	
	//method
	public void resetConfiguration() {
		if (containsAny()) {
			getRefWidget().resetConfiguration();
		}
	}
	
	//method
	public GridCell setWidget(final Widget<?, ?> widget) {
		
		Validator
		.suppose(widget)
		.thatIsOfType(Widget.class)
		.isNotNull();
		
		this.widget = widget;
		
		return this;
	}
}
