//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Matrix;
import ch.nolix.core.container.MatrixRow;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Grid extends Container<Grid, GridLook> {
		
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<GridCell>();
	
	//constructor
	public Grid() {
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.CELL:
				
				final var attributes = attribute.getRefAttributes();
				
				setWidget(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt(),
					GUI.createWidget(attributes.getRefAt(3))
				);
				
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the active cursor icon of the current {@link Area}.
	 */
	public CursorIcon getActiveCursorIcon() {
		
		final var widgetUnderCursor = getRefWidgets().getRefFirstOrNull(w -> w.isUnderCursor());
		
		if (widgetUnderCursor != null) {
			return widgetUnderCursor.getActiveCursorIcon();
		}
		
		return getCursorIcon();
	}
	
	//method
	public List<StandardSpecification> getAttributes() {
		
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
	public int getContentAreaHeight() {
		
		var contentHeight =
		cells
		.getRows()
		.getSumByInt(r -> r.getMaxInt(c -> c.getHeight()));

		if (hasLines()) {
			switch (getRefCurrentLook().getRecursiveLineTypeOrDefault()) {
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
	public int getContentAreaWidth() {
		
		var contentWidth =
		cells
		.getColumns()
		.getSumByInt(c -> c.getMaxInt(cell -> cell.getWidth()));
		
		if (hasLines()) {
			switch (getRefCurrentLook().getRecursiveLineTypeOrDefault()) {
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
		
		final var currentStructure = getRefCurrentLook();
		
		if (!currentStructure.hasRecursiveElementMargin()) {
			return 0;
		}
		
		return currentStructure.getRecursiveElementMarginOrDefault();
	}
	
	//method
	public int getLineThickness() {
		
		final var currentStructure = getRefCurrentLook();
		
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
		
		final var currentStructure = getRefCurrentLook();
		
		return (
			currentStructure.hasRecursiveLineType()
			&& currentStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines
		);
	}
	
	//method
	public boolean hasLines() {
		return getRefCurrentLook().hasRecursiveLineType();
	}
	
	//method
	public Grid reset() {
		
		super.reset();
		
		cells.clear();
		
		return this;
	}
	
	//method
	public Grid setWidget(
		final int rowIndex,
		final int columnIndex,
		final Widget<?, ?> widget
	) {
		expandTo(rowIndex, columnIndex);
		cells.set(rowIndex, columnIndex, new GridCell(rowIndex, columnIndex, widget));	
		widget.setParentWidget(this);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		
		setMaxWidth(1000);
		setMaxHeight(500);
		
		getRefBaseLook()
		.setLineType(GridLineType.InnerLines)
		.setElementMargin(10);		
	}
	
	//method
	protected GridLook createWidgetLook() {
		return new GridLook();
	}
	
	//method
	protected void fillUpWidgets(final List<Widget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}

	//method
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
						y += r.getMaxInt(c -> c.getHeight());
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
						x += c.getMaxInt(c2 -> c2.getWidth());
						x += elementMargin;
						painter.paintFilledRectangle(x, 0, lineThickness, contentAreaHeight);
						x += lineThickness;
					}
				}
		}
		
		//Paints the widgets of the current grid.
		getRefWidgets().forEach(w -> w.paintUsingPositionOnParent(painter));
	}
	
	//method
	protected void setCursorPositionOnContentArea(
		int cursorXPositionOnContent,
		int cursorYPositionOnContent
	) {
		for (final var w : getRefWidgets()) {
			w.setParentCursorPosition(
				cursorXPositionOnContent,
				cursorYPositionOnContent
			);
		}
	}
	
	//method
	/**
	 * Sets the relative position of the current {@link Grid}.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setPositionOnParent(
		final int relativeXPosition,
		final int relativeYPosition
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(relativeXPosition, relativeYPosition);
				
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
				
				x += cells.getColumn(c.getColumnIndex()).getMaxInt(c2 -> c2.getWidth());
				x += getElementMargin();
				x += getLineThickness();
			}
			
			y += r.getMaxInt(c2 -> c2.getHeight());
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
