//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link SingleContainer} is a {@link Container} that can have 1 {@link Widget}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 270
 */
public final class SingleContainer extends Container<SingleContainer, SingleContainerLook>
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
		reset();
		approveProperties();
		applyUsableConfiguration();
	}
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} with the given widget.
	 * 
	 * @param widget
	 * @throws NullArgumentException if the given widget is null.
	 */
	public SingleContainer(final Widget<?, ?> widget) {
		
		//Calls other constructor.
		this();
		
		setWidget(widget);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void addOrChangeAttribute(DocumentNode attribute) {
		
		//Handles the case that the given attribute specicifies a widget.
		if (GUI.canCreateWidget(attribute.getHeader())) {
			setWidget(GUI.createWidget(attribute));
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
	public List<DocumentNode> getAttributes() {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public CursorIcon getContentAreaCursorIcon() {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current single container does not have a widget.
			if (widget == null) {
				return getCustomCursorIcon();
			}
			
			//Handles the case that the current single container has a widget.
				//Handles the case that the widget of the current single container is not under the cursor.
				if (!widget.isUnderCursor()) {
					return getCustomCursorIcon();
				}
				
				//Handles the case that the widget of the current single container is under the cursor.
				return widget.getCursorIcon();
	}
	
	//method
	/**
	 * @return the {@link Widget} of the current {@link SingleContainer}.
	 * @throws ArgumentMissesAttributeException
	 * if the current {@link SingleContainer} does not contain a {@link Widget}.
	 */
	public Widget<?, ?> getRefWidget() {
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Checks if the current single container has a widget.
			if (widget == null) {
				throw new ArgumentMissesAttributeException(this, Widget.class);
			}
			
			return widget;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (widget != null);
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
	 * @throws NullArgumentException if the given widget is null.
	 */
	public SingleContainer setWidget(final Widget<?, ?> widget) {
		
		setParentWidget(this);
		this.widget = widget;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SingleContainerLook createWidgetLook() {
		return new SingleContainerLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {
		
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
	protected final int getContentAreaHeight() {
		
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
	protected final int getContentAreaWidth() {
		
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
	protected void paintContentArea(
		final SingleContainerLook singleContainerLook,
		final IPainter painter
	) {
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current single container has a widget.
			if (widget != null) {
				widget.paintUsingPositionOnParent(painter);
			}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {
		
		//Calls method of the base class.
		super.setPositionOnParent(xPositionOnParent, yPositionOnParent);
		
		//For a better performance, this implementation does not use all comfortable methods.
			//Handles the case that the current single container has a widget.
			if (widget != null) {
				widget.setPositionOnParent(
					getContentAreaXPosition(),
					getContentAreaYPosition()
				);
			}
	}
}
