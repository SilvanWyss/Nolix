//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.container.IContainer;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.Stack;

//class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 130
 */
public final class VerticalStack extends Stack<VerticalStack> {
	
	//constant
	public static final String TYPE_NAME = "VerticalStack";

	//constructor
	/**
	 * Creates a new {@link VerticalStack}.
	 */
	public VerticalStack() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link VerticalStack} with the given widgets
	 * 
	 * @param widgets
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 */
	public VerticalStack(final IContainer<Widget<?, ?>> widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidgets(widgets);
	}
	
	//constructor
	/**
	 * Creates a new {@link VerticalStack} with the given widgets
	 * 
	 * @param widgets
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 */
	public VerticalStack(final Widget<?, ?>... widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidget(widgets);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void recalculateSelf() {
		
		//Calls method of the base class.
		super.recalculateSelf();
		
		//Enumerates the content position of the current vertical stack.
		switch (getContentPosition()) {
			case LeftTop:
			case LeftBottom:
			case Left:
				
				var y1 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(0, y1);
					y1 += w.getHeight() + getElementMargin();
				}
				
				break;
			case Top:
			case Center:
			case Bottom:
				
				final var contentAreaWidth2 = getContentAreaWidth();
				var y2 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent((contentAreaWidth2 - w.getWidth()) / 2, y2);
					y2 += w.getHeight() + getElementMargin();
				}
				
				break;
			case RightTop:
			case Right:
			case RightBottom:
				
				final var contentAreaWidth3 = getContentAreaWidth();
				var y3 = 0;
				for (final var w : getChildWidgets()) {
					w.setPositionOnParent(contentAreaWidth3 - w.getWidth(), y3);
					y3 += w.getHeight() + getElementMargin();
				}
				
				break;
		}
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaHeight() {
		
		int contentHeight = getChildWidgets().getSumByInt(w -> w.getHeight());
				
		//Handles the case that the current vertical stack is not empty.
		if (containsAny()) {
			contentHeight += (getChildWidgets().getSize() - 1) * getElementMargin();
		}
		
		return contentHeight;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getContentAreaWidth() {
		
		//Handles the case that the current vertical stack is empty.
		if (isEmpty()) {
			return 0;
		}
		
		//Handles the case that the current vertical stack is not empty.
		return getChildWidgets().getMaxByInt(w -> w.getWidth());
	}
}
