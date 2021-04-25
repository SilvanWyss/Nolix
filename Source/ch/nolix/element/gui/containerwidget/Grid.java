//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.matrix.Matrix;
import ch.nolix.common.container.matrix.MatrixRow;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;
import ch.nolix.element.gui.widget.Label;

//class
public final class Grid extends ContainerWidget<Grid, GridLook> {
		
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<>();
	
	//constructor
	public Grid() {
		
		setMaxWidth(1000);
		setMaxHeight(500);
		
		getRefLook()
		.setGridTypeForState(WidgetLookState.BASE, GridType.INNER_LINES)
		.setElementMarginForState(WidgetLookState.BASE, 10);
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case PascalCaseCatalogue.CELL:
				
				final var attributes = attribute.getRefAttributes();
				
				setWidget(
					attributes.getRefAt(1).toInt(),
					attributes.getRefAt(2).toInt(),
					WidgetGUI.createWidgetFrom(attributes.getRefAt(3))
				);
				
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	@Override
	public void clear() {
		cells.clear();
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		for (final GridCell c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getSpecificationAs(PascalCaseCatalogue.CELL));
			}
		}
	}
	
	//method
	public int getColumnCount() {
		return cells.getColumnCount();
	}
	
	//method
	@Override
	public int getNaturalContentAreaHeight() {
		
		var contentHeight = cells.getRows().getSumByInt(r -> r.getMaxInt(GridCell::getHeight));

			switch (getRefLook().getGridType()) {
				case INNER_LINES:
					contentHeight += (getRowCount() - 1) * getLineThickness();
					break;
				case INNER_AND_OUTER_LINES:
					contentHeight += (getRowCount() + 1) * getLineThickness();
					break;
			}
		
		contentHeight += getRowCount() * 2 * getElementMargin();
		
		return contentHeight;
	}
	
	//method
	@Override
	public int getNaturalContentAreaWidth() {
		
		var contentWidth = cells.getColumns().getSumByInt(c -> c.getMaxInt(GridCell::getWidth));
		
	
			switch (getRefLook().getGridType()) {
				case INNER_LINES:
					contentWidth += (getColumnCount() - 1) * getLineThickness();
					break;
				case INNER_AND_OUTER_LINES:
					contentWidth += (getColumnCount() + 1) * getLineThickness();
					break;
			}
		
		
		contentWidth += getColumnCount() * 2 * getElementMargin();
		
		return contentWidth;
	}
	
	//method
	public int getElementMargin() {
		
		final var currentStructure = getRefLook();
		
		return currentStructure.getElementMargin();
	}
	
	//method
	public int getLineThickness() {
		return getLineThicknessWhenHasLines();
	}
	
	//method
	public int getRowCount() {
		return cells.getRowCount();
	}
	
	//method
	public boolean hasInnerAndOuterLines() {
		
		final var currentStructure = getRefLook();
		
		return (currentStructure.getGridType() == GridType.INNER_AND_OUTER_LINES);
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final String text) {
		return setWidget(rowIndex, columnIndex, new Label().setText(text));
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final Widget<?, ?> widget) {
		
		expandTo(rowIndex, columnIndex);
		cells.setAt(rowIndex, columnIndex, new GridCell(rowIndex, columnIndex, widget));
		
		return this;
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
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
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
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
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
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
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
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
	protected void paintContentArea(final IPainter painter, final GridLook gridLook) {
			
			painter.setColor(gridLook.getGridColor());
			
			final var outerLinesDefined = gridLook.getGridType() == GridType.INNER_AND_OUTER_LINES;
			
			final var contentAreaWidth = getNaturalContentAreaWidth();
			final var contentAreaHeight = getNaturalContentAreaHeight();
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
				
				if (gridLook.getGridType() == GridType.INNER_AND_OUTER_LINES) {
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
		
		
		//Paints the widgets of the current grid.
		getChildWidgets().forEach(w -> w.paintRecursively(painter));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
						
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetContainerWidget() {
		clear();
	}
	
	//method
	private void expandColumnsTo(final int columnIndex) {
		
		Validator
		.assertThat(columnIndex)
		.thatIsNamed(LowerCaseCatalogue.COLUMN_INDEX)
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
		.thatIsNamed(LowerCaseCatalogue.ROW_INDEX)
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
	
	//method
	private int getLineThicknessWhenHasLines() {
		return getRefLook().getGridThickness();
	}
}
