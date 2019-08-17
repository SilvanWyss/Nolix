//package declaration
package ch.nolix.element.containerWidgets;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.List;
import ch.nolix.core.containers.Matrix;
import ch.nolix.core.containers.MatrixRow;
import ch.nolix.core.node.Node;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widgets.Label;

//class
public final class Grid extends ContainerWidget<Grid, GridLook> {
		
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<>();
	
	//constructor
	public Grid() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.CELL:
				
				final var attributes = attribute.getRefAttributes();
				
				setWidget(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt(),
					LayerGUI.createWidgetFrom(attributes.getRefAt(3))
				);
				
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public List<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		for (final GridCell c : cells) {
			if (c.containsAny()) {
				attributes.addAtEnd(c.getSpecificationAs(PascalCaseNameCatalogue.CELL));
			}
		}
		
		return attributes;
	}
	
	//method
	public int getColumnCount() {
		return cells.getColumnCount();
	}
	
	//method
	@Override
	public int getContentAreaHeight() {
		
		var contentHeight =
		cells
		.getRows()
		.getSumByInt(r -> r.getMaxByInt(c -> c.getHeight()));

		if (hasLines()) {
			switch (getRefLook().getRecursiveLineTypeOrDefault()) {
				case InnerLines:
					contentHeight += (getRowCount() - 1) * getLineThickness();
					break;
				case InnerAndOuterLines:
					contentHeight += (getRowCount() + 1) * getLineThickness();
					break;
			}
		}
		
		contentHeight += getRowCount() * 2 * getElementMargin();
		
		return contentHeight;
	}
	
	//method
	@Override
	public int getContentAreaWidth() {
		
		var contentWidth =
		cells
		.getColumns()
		.getSumByInt(c -> c.getMaxByInt(cell -> cell.getWidth()));
		
		if (hasLines()) {
			switch (getRefLook().getRecursiveLineTypeOrDefault()) {
				case InnerLines:
					contentWidth += (getColumnCount() - 1) * getLineThickness();
					break;
				case InnerAndOuterLines:
					contentWidth += (getColumnCount() + 1) * getLineThickness();
					break;
			}
		}
		
		contentWidth += getColumnCount() * 2 * getElementMargin();
		
		return contentWidth;
	}
	
	//method
	public int getElementMargin() {
		
		final var currentStructure = getRefLook();
		
		if (!currentStructure.hasRecursiveElementMargin()) {
			return 0;
		}
		
		return currentStructure.getRecursiveElementMarginOrDefault();
	}
	
	//method
	public int getLineThickness() {
		
		final var currentStructure = getRefLook();
		
		if (!hasLines()) {
			return 0;
		}
		
		return currentStructure.getRecursiveLineThicknessOrDefault();
	}
	
	//method
	public int getRowCount() {
		return cells.getRowCount();
	}
	
	//method
	public boolean hasInnerAndOuterLines() {
		
		final var currentStructure = getRefLook();
		
		return (
			currentStructure.hasRecursiveLineType()
			&& currentStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines
		);
	}
	
	//method
	public boolean hasLines() {
		return getRefLook().hasRecursiveLineType();
	}
	
	//method
	@Override
	public Grid reset() {
		
		super.reset();
		
		cells.clear();
		
		return this;
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final String text) {
		return setWidget(rowIndex, columnIndex, new Label(text));
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final Widget<?, ?> widget) {
		
		expandTo(rowIndex, columnIndex);
		addChildWidget(widget);
		cells.setAt(rowIndex, columnIndex, new GridCell(rowIndex, columnIndex, widget));
				
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		
		setMaxWidth(1000);
		setMaxHeight(500);
		
		getRefBaseLook()
		.setLineType(GridLineType.InnerLines)
		.setElementMargin(10);
	}
	
	//method
	@Override
	protected GridLook createLook() {
		return new GridLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}
	
	//method
	@Override
	protected void fillUpPaintableWidgets(final List<Widget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}
	
	//method
	@Override
	protected void paintContentArea(
		final GridLook gridStructure,
		final IPainter painter
	) {
		//Paints the lines of the current grid.
		if (gridStructure.hasRecursiveLineType()) {
			
			painter.setColor(gridStructure.getRecursiveLineColorOrDefault());
			
			final var outerLinesDefined =
			gridStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines;
			
			final var contentAreaWidth = getContentAreaWidth();
			final var contentAreaHeight = getContentAreaHeight();
			final var lineThickness = getLineThickness();
			final var elementMargin = getElementMargin();
			
			//Paints the horizontal lines of the current grid.
				var y = 0;
				
				if (outerLinesDefined) {
					painter.paintFilledRectangle(contentAreaWidth, lineThickness);
					y += lineThickness;
				}
				
				for (final var r : cells.getRows()) {
					if (r.getRowIndex() < cells.getRowCount()) {
						y += elementMargin;
						y += r.getMaxByInt(c -> c.getHeight());
						y += elementMargin;
						painter.paintFilledRectangle(0, y, contentAreaWidth, lineThickness);
						y += lineThickness;
					}
				}
			
			//Paints the vertical lines of the current grid.
				var x = 0;
				
				if (gridStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines) {
					painter.paintFilledRectangle(lineThickness, contentAreaHeight);
					x += lineThickness;
				}
				
				for (final var c : cells.getColumns()) {
					if (c.getColumnIndex() < cells.getColumnCount()) {
						x += elementMargin;
						x += c.getMaxByInt(c2 -> c2.getWidth());
						x += elementMargin;
						painter.paintFilledRectangle(x, 0, lineThickness, contentAreaHeight);
						x += lineThickness;
					}
				}
		}
		
		//Paints the widgets of the current grid.
		getChildWidgets().forEach(w -> w.paintRecursively(painter));
	}
	
	//method
	/**
	 * Sets the relative position of the current {@link Grid}.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	@Override
	public void recalculate () {
		
		//Calls method of the base class.
		super.recalculate();
				
		var y = 0;
		
		if (hasInnerAndOuterLines()) {
			y += getLineThickness();
		}
		
		for (final MatrixRow<GridCell> r : cells.getRows()) {
							
			y += getElementMargin();
			
			var x = 0;
			
			if (hasInnerAndOuterLines()) {
				x += getLineThickness();
			}
			
			for (final GridCell c : r) {
				
				x += getElementMargin();
				
				if (c.containsAny()) {
					c.getRefWidget().setPositionOnParent(x, y);
				}
				
				x += cells.getColumn(c.getColumnIndex()).getMaxByInt(c2 -> c2.getWidth());
				x += getElementMargin();
				x += getLineThickness();
			}
			
			y += r.getMaxByInt(c2 -> c2.getHeight());
			y += getElementMargin();
			y += getLineThickness();
		}
	}

	//method
	private void expandColumnsTo(final int columnIndex) {
		
		Validator
		.suppose(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(new GridCell(1, 1));
		}
		
		for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {
			
			final var column = new List<GridCell>();
			
			for (var ri = 1; ri <= getRowCount(); ri++) {
				column.addAtEnd(new GridCell(ri, ci));
			}
			
			cells.addColumn(column);
		}
	}
	
	//method
	private void expandRowsTo(final int rowIndex) {
		
		Validator
		.suppose(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(new GridCell(1, 1));
		}
		
		for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {
			
			final var row = new List<GridCell>();
			
			for (var ci = 1; ci <= getColumnCount(); ci++) {
				row.addAtEnd(new GridCell(ri, ci));
			}
			
			cells.addRow(row);
		}
	}
	
	//method
	private void expandTo(final int rowCount, final int columnCount) {
		expandRowsTo(rowCount);
		expandColumnsTo(columnCount);
	}
}
