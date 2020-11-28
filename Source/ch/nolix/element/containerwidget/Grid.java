//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.Matrix;
import ch.nolix.common.container.MatrixRow;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.gui.LayerGUI;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.Label;

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
	public LinkedList<Node> getAttributes() {
		
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
		
		var contentHeight = cells.getRows().getSumByInt(r -> r.getMaxInt(GridCell::getHeight));

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
		
		var contentWidth = cells.getColumns().getSumByInt(c -> c.getMaxInt(GridCell::getWidth));
		
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
		return setWidget(rowIndex, columnIndex, new Label().setText(text));
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
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
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
						y += r.getMaxInt(GridCell::getHeight);
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
						x += c.getMaxInt(GridCell::getWidth);
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
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {
						
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
				
				x += cells.getColumn(c.getColumnIndex()).getMaxInt(GridCell::getWidth);
				x += getElementMargin();
				x += getLineThickness();
			}
			
			y += r.getMaxInt(GridCell::getHeight);
			y += getElementMargin();
			y += getLineThickness();
		}
	}

	//method
	private void expandColumnsTo(final int columnIndex) {
		
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(VariableNameCatalogue.COLUMN_INDEX)
		.isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(new GridCell(1, 1));
		}
		
		for (var ci = getColumnCount() + 1; ci <= columnIndex; ci++) {
			
			final var column = new LinkedList<GridCell>();
			
			for (var ri = 1; ri <= getRowCount(); ri++) {
				column.addAtEnd(new GridCell(ri, ci));
			}
			
			cells.addColumn(column);
		}
	}
	
	//method
	private void expandRowsTo(final int rowIndex) {
		
		Validator
		.assertThat(rowIndex)
		.thatIsNamed(VariableNameCatalogue.ROW_INDEX)
		.isPositive();
		
		if (cells.isEmpty()) {
			cells.addRow(new GridCell(1, 1));
		}
		
		for (var ri = getRowCount() + 1; ri <= rowIndex; ri++) {
			
			final var row = new LinkedList<GridCell>();
			
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
