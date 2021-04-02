//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link SingleContainer} is a {@link ContainerWidget} that can have 1 {@link Widget}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 280
 */
public final class SingleContainer extends ContainerWidget<SingleContainer, OldSingleContainerLook> {
	
	//constant
	public static final String TYPE_NAME = "SingleContainer";
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(BaseNode attribute) {
		
		//Handles the case that the given attribute specicifies a widget.
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setWidget(WidgetGUI.createWidgetFrom(attribute));
			return;
		}
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
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
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current single container has a widget.
		if (widget != null) {
			list.addAtEnd(widget.getSpecification());
		}
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
				throw new ArgumentDoesNotHaveAttributeException(this, Widget.class);
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
	protected OldSingleContainerLook createLook() {
		return new OldSingleContainerLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		//Handles the case that the current single container has a widget.	
		if (widget != null) {
			list.addAtEnd(widget);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		//Handles the case that the current single container has a widget.	
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
		
		return widget.getWidth();
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
		
		return widget.getHeight();
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final OldSingleContainerLook singleContainerLook, final IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateBorderWidget() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetContainerWidget() {
		clear();
	}
}
