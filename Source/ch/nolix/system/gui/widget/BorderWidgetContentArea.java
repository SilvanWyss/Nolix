//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Recalculable;
import ch.nolix.coreapi.geometryapi.griduniversalapi.Rectangular;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2019-05-08
 * @param <BWL>
 * is the type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetContentArea}.
 */
public final class BorderWidgetContentArea<BWL extends BorderWidgetLook<BWL>> implements Recalculable, Rectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetContentArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//attributes
	private int width;
	private int height;
	private int naturalWidth;
	private int naturalHeight;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetContentArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		GlobalValidator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetContentArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorXPosition() {
		return 
		parentBorderWidget.getExtendedContentArea().getCursorXPosition()
		- getXPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorYPosition() {
		return
		parentBorderWidget.getExtendedContentArea().getCursorYPosition()
		- getYPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return height;
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetContentArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetContentArea} does not have a target height.
	 */
	public int getTargetHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getExtendedContentArea().getTargetHeight()
		- look.getTopPadding()
		- look.getBottomPadding();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetContentArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetContentArea} does not have a target width.
	 */
	public int getTargetWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getExtendedContentArea().getTargetWidth()
		- look.getLeftPadding()
		- look.getRightPadding();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetContentArea}.
	 */
	public int getNaturalHeight() {
		return naturalHeight;
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetContentArea}.
	 */
	public int getNaturalWidth() {
		return naturalWidth;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return width;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		return parentBorderWidget.getExtendedContentArea().getXPosition() + getXPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return parentBorderWidget.getExtendedContentArea().getYPosition() + getYPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetExtendedContentArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnExtendedContentArea() {
		return parentBorderWidget.getRefLook().getLeftPadding();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetScrolledArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		return
		parentBorderWidget.getExtendedContentArea().getXPositionOnScrolledArea()
		+ parentBorderWidget.getRefLook().getLeftPadding();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetExtendedContentArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnExtendedContentArea() {
		return parentBorderWidget.getRefLook().getTopPadding();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetScrolledArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		return
		parentBorderWidget.getExtendedContentArea().getYPositionOnScrolledArea()
		+ parentBorderWidget.getRefLook().getTopPadding();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetContentArea} is under the cursor.
	 */
	public boolean isUnderCursor() {
				
		final var cursorXPosition = getCursorXPosition();
		final var cursorYPosition = getCursorYPosition();
		
		return
		cursorXPosition >= 0
		&& cursorYPosition >= 0
		&& cursorXPosition < getWidth()
		&& cursorYPosition < getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void recalculate() {
		
		naturalWidth = calculateNaturalWidth();
		naturalHeight = calculateNaturalHeight();
		
		width = calculateWidth();
		height = calculateHeight();
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetContentArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		parentBorderWidget.paintContentArea(painter.createPainter(), borderWidgetLook);
		
		parentBorderWidget.getRefWidgetsForPainting().forEach(cw -> cw.paint(painter));
	}
	
	//method
	private int calculateHeight() {
		
		var lHeight = getNaturalHeight();
		
		if (parentBorderWidget.contentAreaMustBeExpandedToTargetSize() && parentBorderWidget.hasTargetHeight()) {
			lHeight = GlobalCalculator.getMax(getTargetHeight(), lHeight);
		}
		
		return lHeight;
	}
	
	//method
	private int calculateNaturalHeight() {
		return parentBorderWidget.getNaturalContentAreaHeight();
	}
	
	//method
	private int calculateNaturalWidth() {
		return parentBorderWidget.getNaturalContentAreaWidth();
	}
	
	//method
	private int calculateWidth() {
		
		var lWidth = getNaturalWidth();
		
		if (parentBorderWidget.contentAreaMustBeExpandedToTargetSize() && parentBorderWidget.hasTargetWidth()) {
			lWidth = GlobalCalculator.getMax(getTargetWidth(), lWidth);
		}
		
		return lWidth;
	}
}
