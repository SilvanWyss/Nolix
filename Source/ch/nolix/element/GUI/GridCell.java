//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidStateException.EmptyStateException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.core.MutableElement;

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
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (GUI.canCreateWidget(attribute)) {
			setWidget(GUI.createWidget(attribute));
			return;
		}
		
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	@Override
	public GridCell clear() {
		
		widget = null;
		
		return this;
	}
	
	//method
	@Override
	public List<DocumentNode> getAttributes() {
		
		final var attributes = new List<DocumentNode>(
			DocumentNode.createFromLong(getRowIndex()),
			DocumentNode.createFromLong(getColumnIndex())
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
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	/**
	 * Resets this grid cell.
	 * 
	 * @return this grid cell.
	 */
	@Override
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
		
		Validator.suppose(widget).isOfType(Widget.class);
		
		this.widget = widget;
		
		return this;
	}
}
