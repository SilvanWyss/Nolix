//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.matrix.Matrix;
import ch.nolix.core.container.matrix.MatrixRow;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.element.MultiValueExtractor;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
public final class Grid extends ContainerWidget<Grid, GridLook> {
	
	//constant
	private static final String CELL_HEADER = PascalCaseCatalogue.CELL;
	
	//multi-attribute
	private Matrix<GridCell> cells = new Matrix<>();
	
	//attribute
	@SuppressWarnings("unused")
	private final MultiValueExtractor<GridCell> cellsExtractor =
	new MultiValueExtractor<>(
		CELL_HEADER,
		this::addCell,
		() -> cells.getRefSelected(GridCell::containsAny),
		GridCell::fromSpecification,
		GridCell::getSpecification
	);
	
	//constructor
	public Grid() {
		
		reset();
		
		setMaxWidth(1000);
		setMaxHeight(500);
		getRefActiveLook()
		.setGridTypeForState(WidgetLookState.BASE, GridType.INNER_LINES)
		.setElementMarginForState(WidgetLookState.BASE, 10);
	}
	
	//method
	@Override
	public void clear() {
		cells.clear();
	}
	
	//method
	public int getColumnCount() {
		return cells.getColumnCount();
	}
	
	//method
	@Override
	public int getNaturalContentAreaHeight() {
		
		var contentHeight = cells.getRows().getSumByInt(r -> r.getMaxInt(GridCell::getHeight));

			switch (getRefActiveLook().getGridType()) {
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
		
	
			switch (getRefActiveLook().getGridType()) {
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
		
		final var currentStructure = getRefActiveLook();
		
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
		
		final var currentStructure = getRefActiveLook();
		
		return (currentStructure.getGridType() == GridType.INNER_AND_OUTER_LINES);
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final String text) {
		return setWidget(rowIndex, columnIndex, new Label().setText(text));
	}
	
	//method
	public Grid setWidget(final int rowIndex, final int columnIndex, final Widget<?, ?> widget) {
		
		addCell(new GridCell(rowIndex, columnIndex).setWidget(widget));
				
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
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		for (final var c : cells) {
			if (c.containsAny()) {
				list.addAtEnd(c.getRefWidget());
			}
		}
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
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
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
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
		getChildWidgets().forEach(w -> w.paint(painter));
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
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void resetContainerWidget() {
		clear();
	}
	
	//method
	private void addCell(final GridCell cell) {
		expandTo(cell.getRowIndex(), cell.getColumnIndex());
		cells.setAt(cell.getRowIndex(), cell.getColumnIndex(), cell);
	}
	
	//method
	private void expandColumnsTo(final int columnIndex) {
		
		GlobalValidator
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
		
		GlobalValidator
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
		return getRefActiveLook().getGridThickness();
	}
}
