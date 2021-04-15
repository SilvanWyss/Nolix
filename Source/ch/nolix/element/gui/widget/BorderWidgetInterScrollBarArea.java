//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.HoverableByCursor;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class BorderWidgetInterScrollBarArea<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//constants
	public static final int WIDTH_WHEN_VERTICAL_SCROLLBAR_NOT_VISIBLE = 0;
	public static final int HEIGHT_WHEN_HORIZONTAL_SCROLLBAR_NOT_VISIBLE = 0;
	
	//attribute
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
	BorderWidgetInterScrollBarArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	@Override
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	@Override
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	@Override
	public int getHeight() {
		
		final var horizontalScrollBar = parentBorderWidget.getHorizontalScrollBar();
		
		if (!horizontalScrollBar.isVisible()) {
			return HEIGHT_WHEN_HORIZONTAL_SCROLLBAR_NOT_VISIBLE;
		}
		
		return horizontalScrollBar.getHeight();
	}
	
	//method
	@Override
	public int getWidth() {
		
		final var verticalScrollBar = parentBorderWidget.getVerticalScrollBar();
		
		if (!verticalScrollBar.isVisible()) {
			return WIDTH_WHEN_VERTICAL_SCROLLBAR_NOT_VISIBLE;
		}
		
		return verticalScrollBar.getWidth();
	}
	
	//method
	@Override
	public int getXPosition() {
		return (parentBorderWidget.getBorderedArea().getXPosition() + getXPositionOnBorderedArea());
	}
	
	//method
	public int getXPositionOnBorderedArea() {
		return parentBorderWidget.getHorizontalScrollBar().getWidth();
	}
	
	//method
	@Override
	public int getYPosition() {
		return (parentBorderWidget.getBorderedArea().getYPosition() + getYPositionOnBorderedArea());
	}
	
	//method
	public int getYPositionOnBorderedArea() {
		return parentBorderWidget.getVerticalScrollBar().getHeight();
	}
	
	//method
	public boolean isVisible() {
		return
		parentBorderWidget.getVerticalScrollBar().isVisible()
		&& parentBorderWidget.getHorizontalScrollBar().isVisible();
	}
	
	//method
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		if (isVisible()) {
			paintWhenVisible(painter, borderWidgetLook);
		}
	}
	
	//method
	private void paintWhenVisible(final IPainter painter, final BWL borderWidgetLook) {
		if (borderWidgetLook.hasBackgroundColor()) {
			paintWhenVisibleAndHasBackgroundColor(painter, borderWidgetLook);
		} else if (borderWidgetLook.hasBackgroundColorGradient()) {
			paintWhenVisibleAndHasBackgroundColorGradient(painter, borderWidgetLook);
		} else if (borderWidgetLook.hasBackgroundImage()) {
			paintWhenVisibleAndHasBackgroundImage(painter, borderWidgetLook);
		}
	}
	
	//method
	private void paintWhenVisibleAndHasBackgroundColor(final IPainter painter, final BWL borderWidgetLook) {
		
		painter.setColor(borderWidgetLook.getBackgroundColor());
		
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
	
	//method
	private void paintWhenVisibleAndHasBackgroundColorGradient(final IPainter painter, final BWL borderWidgetLook) {
		
		painter.setColor(borderWidgetLook.getBackgroundColorGradient().getColor2());
		
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
	
	//method
	private void paintWhenVisibleAndHasBackgroundImage(final IPainter painter, final BWL borderWidgetLook) {
		
		painter.setColor(borderWidgetLook.getBackgroundImage().getBottomRightPixel());
		
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
}
