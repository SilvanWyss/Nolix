//package declaration
package ch.nolix.element.containerWidget;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link SingleContainer} is a {@link ContainerWidget} that can have 1 {@link Widget}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 270
 */
public final class SingleContainer extends ContainerWidget<SingleContainer, SingleContainerLook>
implements Clearable<SingleContainer> {
	
	//constant
	public static final String TYPE_NAME = "SingleContainer";
	
	//optional attribute
	private Widget<?, ?> widget;
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer}.
	 */
	public SingleContainer() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} with the given widget.
	 * 
	 * @param widget
	 * @throws ArgumentIsNullException if the given widget is null.
	 */
	public SingleContainer(final Widget<?, ?> widget) {
		
		resetAndApplyDefaultConfiguration();
		
		setWidget(widget);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void addOrChangeAttribute(Node attribute) {
		
		//Handles the case that the given attribute specicifies a widget.
		if (LayerGUI.canCreateWidgetFrom(attribute)) {
			setWidget(LayerGUI.createWidgetFrom(attribute));
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
	public SingleContainer clear() {
		
		widget = null;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current single container has a widget.
			if (widget != null) {
				attributes.addAtEnd(widget.getSpecification());
			}
				
		return attributes;
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (widget == null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SingleContainer reset() {
		
		//Calls method of the base class.
		super.reset();
		
		clear();
		
		return this;
	}
	
	/**
	 * Sets the widget of the current {@link SingleContainer}.
	 * 
	 * @param widget
	 * @return the current {@link SingleContainer}.
	 * @throws ArgumentIsNullException if the given widget is null.
	 */
	public SingleContainer setWidget(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		this.widget = widget;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
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
	protected int getContentAreaHeight() {
		
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
	protected int getContentAreaWidth() {
		
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
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final SingleContainerLook singleContainerLook, final IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {}
}
