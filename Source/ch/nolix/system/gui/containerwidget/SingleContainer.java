//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.system.element.mutableelement.MutableOptionalValueExtractor;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.WidgetGUI;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * A {@link SingleContainer} is a {@link ContainerWidget} that can have 1 {@link Widget}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public final class SingleContainer extends ContainerWidget<SingleContainer, SingleContainerLook> {
	
	//constant
	private static final String WIDGET_HEADER = "Widget";
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> widgetExtractor =
	new MutableOptionalValueExtractor<>(
		WIDGET_HEADER,
		this::setWidget,
		this::containsAny,
		this::getRefWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//method
	/**
	 * Creates a new {@link SingleContainer}.
	 */
	public SingleContainer() {
		reset();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		widget = null;
	}
	
	//method
	/**
	 * @return the {@link Widget} of the current {@link SingleContainer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link SingleContainer} does not contain a {@link Widget}.
	 */
	public Widget<?, ?> getRefWidget() {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Asserts that the current single container has a widget.
			if (widget == null) {
				throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeType(this, Widget.class);
			}
			
			return widget;
	}
	
	/**
	 * Sets the widget of the current {@link SingleContainer}.
	 * 
	 * @param widget
	 * @return the current {@link SingleContainer}.
	 * @throws ArgumentIsNullException if the given widget is null.
	 */
	public SingleContainer setWidget(final Widget<?, ?> widget) {
		
		this.widget = widget;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SingleContainerLook createLook() {
		return new SingleContainerLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		//Handles the case that the current single container has a widget.	
		if (widget != null) {
			list.addAtEnd(widget);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		
		//Handles the case that the current single container contains a Widget.
		if (widget != null) {
			list.addAtEnd(widget);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaHeight() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (widget == null) {
			return 0;
		}
		
		return widget.getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int getNaturalContentAreaWidth() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (widget == null) {
			return 0;
		}
		
		return widget.getWidth();
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final IPainter painter, final SingleContainerLook singleContainerLook) {
		//Does nothing.
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {
		//Does nothing.
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		clear();
	}
}
