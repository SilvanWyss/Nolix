//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Matrix;
import ch.nolix.core.container.MatrixColumn;
import ch.nolix.core.container.MatrixRow;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.validator2.Validator;

//class
public final class Grid extends Container<Grid, GridStructure> {
		
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<GridCell>();
	
	//constructor
	public Grid() {
		reset();
		approveProperties();
	}
	
	//method
	public void addOrChangeAttribute(final Specification attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseNameCatalogue.CELL:
				
				final var attributes = attribute.getRefAttributes().toArray();
				
				setWidget(
					attributes[0].toInt(),
					attributes[1].toInt(),
					GUI.createWidget(attributes[3])
				);
				
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
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
	public int getContentHeight() {
		
		var contentHeight =
		cells
		.getRows()
		.getSumByInt(r -> r.getMaxInt(c -> c.getHeight()));

		if (hasLines()) {
			switch (getRefCurrentStructure().getRecursiveLineTypeOrDefault()) {
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
	public int getContentWidth() {
		
		var contentWidth =
		cells
		.getColumns()
		.getSumByInt(c -> c.getMaxInt(cell -> cell.getWidth()));
		
		if (hasLines()) {
			switch (getRefCurrentStructure().getRecursiveLineTypeOrDefault()) {
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
		
		final var currentStructure = getRefCurrentStructure();
		
		if (!currentStructure.hasRecursiveElementMargin()) {
			return 0;
		}
		
		return currentStructure.getRecursiveElementMarginOrDefault();
	}
	
	//method
	public int getLineThickness() {
		
		final var currentStructure = getRefCurrentStructure();
		
		if (!hasLines()) {
			return 0;
		}
		
		return currentStructure.getRecursiveLineThicknessOrDefault();
	}
	
	//method
	public ReadContainer<Widget<?, ?>> getRefWidgets() {
		return new ReadContainer<Widget<?, ?>>(cells.getRefSelected(c -> c.containsAny()).to(c -> c.getRefWidget()));
	}
	
	//method
	public int getRowCount() {
		return cells.getRowCount();
	}
	
	//method
	public boolean hasInnerAndOuterLines() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return (
			currentStructure.hasRecursiveLineType()
			&& currentStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines
		);
	}
	
	//method
	public boolean hasLines() {
		return getRefCurrentStructure().hasRecursiveLineType();
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
		
		if (belongsToGUI()) {
			widget.setGUI(getRefGUI());
		}
		
		return this;
	}
	
	//method
	protected GridStructure createWidgetStructure() {
		return new GridStructure();
	}

	//method
	protected void paintContent(
		final GridStructure gridStructure,
		final IPainter painter
	) {
		if (gridStructure.hasRecursiveLineType()) {
			
			painter.setColor(gridStructure.getRecursiveLineColorOrDefault());
			
			final var outerLinesDefined =
			gridStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines;
			
			final var contentWidth = getContentWidth();
			final var contentHeight = getContentHeight();
			final var lineThickness = getLineThickness();
			final var elementMargin = getElementMargin();
			
			var y = 0;
			
			if (outerLinesDefined) {
				painter.paintFilledRectangle(contentWidth, lineThickness);
				y += lineThickness;
			}
			
			for (final MatrixRow<GridCell> r : cells.getRows()) {
				if (outerLinesDefined || r.getRowIndex() < r.getElementCount()) {
					y += elementMargin;
					y += r.getMaxInt(c -> c.getHeight());
					y += elementMargin;
					painter.paintFilledRectangle(0, y, contentWidth, lineThickness);
					y += lineThickness;
				}
			}
			
			var x = 0;
			
			if (gridStructure.getRecursiveLineTypeOrDefault() == GridLineType.InnerAndOuterLines) {
				painter.paintFilledRectangle(lineThickness, contentHeight);
				x += lineThickness;
			}
			
			for (final MatrixColumn<GridCell> c : cells.getColumns()) {
				if (outerLinesDefined || c.getColumnIndex() < c.getElementCount()) {
					x += elementMargin;
					x += c.getMaxInt(c2 -> c2.getWidth());
					x += elementMargin;
					painter.paintFilledRectangle(x, 0, lineThickness, contentHeight);
					x += lineThickness;
				}
			}
		}
		
		getRefWidgets().forEach(w -> w.paintUsingPositionOnContainer(painter));
	}
	
	//method
	/**
	 * Sets the relative position of the current {@link Grid}.
	 * 
	 * @param relativeXPosition
	 * @param relativeYPosition
	 */
	protected void setPositionOnContainer(
		final int relativeXPosition,
		final int relativeYPosition
	) {
		
		//Calls method of the base class.
		super.setPositionOnContainer(relativeXPosition, relativeYPosition);
				
		var y = getContentYPosition();
		
		if (hasInnerAndOuterLines()) {
			y += getLineThickness();
		}
		
		for (final MatrixRow<GridCell> r : cells.getRows()) {
							
			y += getElementMargin();
			
			var x = getContentXPosition();	
			
			if (hasInnerAndOuterLines()) {
				x += getLineThickness();
			}
			
			for (final GridCell c : r) {
				
				x += getElementMargin();
				
				if (c.containsAny()) {
					c.getRefWidget().setPositionOnContainer(x, y);
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
			
			final var column = new GridCell[getRowCount()];
			
			for (var ri = 1; ri <= getRowCount(); ri++) {
				column[ri - 1] = (new GridCell(ri, ci));
			}
			
			//TODO: Extend addColumn method of Matrix.
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
			
			final var row = new GridCell[getColumnCount()];
			
			for (var ci = 1; ci <= getColumnCount(); ci++) {
				row[ci - 1] = new GridCell(ri, ci);
			}
			
			//TODO: Extend addRow method of Matrix.
			cells.addRow(row);
		}
	}
	
	//method
	private void expandTo(final int rowCount, final int columnCount) {
		expandRowsTo(rowCount);
		expandColumnsTo(columnCount);
	}
}
