//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator2.Validator;
import ch.nolix.core.container.List;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * A {@link Stack} is a {@link Container} that places its widgets linearly.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 310
 * @param <S> The type of a {@link Stack}.
 */
public abstract class Stack<S extends Stack<S>> 
extends Container<S, StackLook>
implements Clearable<S> {
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//optional attribute
	private NonNegativeInteger elementMargin;
	
	//multi-attribute
	private final List<Widget<?, ?>> widgets = new List<Widget<?, ?>>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (GUI.canCreateWidget(attribute.getHeader())) {
			addWidget(GUI.createWidget(attribute));
			return;
		}
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ELEMENT_MARGIN_HEADER:
				setElementMargin(attribute.getOneAttributeAsInt());
				break;		
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Adds the given widget to the current {@link Stack}.
	 * 
	 * @param widget
	 * @return the current {@link Stack}.
	 * @throws NullArgumentException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another GUI than the current {@link Stack}.
	 */
	public final S addWidget(final Widget<?, ?> widget) {
		
		//Checks if the given widget is not null.
		Validator.suppose(widget).isInstanceOf(Widget.class);
		
		widget.setParentWidget(this);		
		widgets.addAtEnd(widget);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds the given widgets to the current {@link Stack}.
	 * 
	 * @param widgets
	 * @return the current {@link Stack}.
	 * @throws NullArgumentException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link Stack}.
	 */
	public final S addWidget(final Widget<?, ?>... widgets) {
		return addWidgets(new ReadContainer<Widget<?, ?>>(widgets));
	}
	
	//method
	/**
	 * Adds the given widgets to the current {@link Stack}.
	 * 
	 * @param widgets
	 * @return the current {@link Stack}.
	 * @throws NullArgumentException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link Stack}.
	 */
	public final <W extends Widget<?, ?>> S addWidgets(final Iterable<W> widgets) {
		
		widgets.forEach(w -> addWidget(w));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final S clear() {
		
		widgets.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the active element margin of the current {@link Stack}.
	 */
	public final int getActiveElementMargin() {
		
		//Handles the case that the current {@link Stack} has no element margin.
		if (!hasElementMargin()) {
			return 0;
		}
		
		//Handles the case that the current {@link Stack} has an element margin.
		return elementMargin.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that the current {@link Stack} has an element margin.
		if (hasElementMargin()) {
			attributes.addAtEnd(
				elementMargin.getSpecificationAs(ELEMENT_MARGIN_HEADER)
			);
		}
		
		getRefWidgets().forEach(r -> attributes.addAtEnd(r.getSpecification()));	
		
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final CursorIcon getContentAreaCursorIcon() {
		
		final var widgetUnderCursor =
		getRefWidgets().getRefFirstOrNull(w -> w.isUnderCursor());
		
		if (widgetUnderCursor != null) {
			return widgetUnderCursor.getCursorIcon();
		}
		
		return getCustomCursorIcon();
	}
	
	//method
	/**
	 * @return the widgets of the current {@link Stack} that are shown.
	 */
	public final ReadContainer<Widget<?, ?>> getRefShownWidgets() {
		return new ReadContainer<Widget<?, ?>>(
			getRefWidgets().getRefSelected(w -> !w.isCollapsed())
		);
	}
	
	//method
	/**
	 * @return true if the current {@link Stack} container has an element margin.
	 */
	public final boolean hasElementMargin() {
		return (elementMargin != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Stack} contains no widget.
	 */
	public final boolean isEmpty() {
		return widgets.isEmpty();
	}
	
	//method
	/**
	 * Removes the element margin of the current {@link Stack}.
	 * 
	 * @return the current {@link Stack}.
	 */
	public final S removeElementMargin() {
		
		elementMargin = null;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the given widget from the current {@link Stack}.
	 *
	 * @param widget
	 * @return the current {@link Stack}.
	 * @throws InvalidStateException
	 * if the current {@link Stack} does not contain the given widget.
	 */
	public final S removeWidget(final Widget<?, ?> widget) {
		
		widgets.removeFirst(widget);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final S resetConfiguration() {
		
		//Calls method of the base class.
		super.resetConfiguration();
	
		removeElementMargin();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the element margin of the current {@link Stack}.
	 * 
	 * @param elementMargin
	 * @return the current {@link Stack}.
	 * @throws NonPositiveArgumentException if the given element margin is not positive.
	 */
	public final S setElementMargin(final int elementMargin) {
		
		this.elementMargin = new NonNegativeInteger(elementMargin);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final void applyUsableConfigurationWhenConfigurationIsReset() {
		setElementMargin(10);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected final StackLook createWidgetLook() {
		return new StackLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void paintContentArea(
		final StackLook stackStructure,
		final IPainter painter
	) {
		//Paints the shown widgets of the current stack.
		getRefShownWidgets().forEach(r -> r.paintUsingPositionOnParent(painter));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	protected void setCursorPositionOnContentArea(
		int cursorXPositionOnContent,
		int cursorYPositionOnContent
	) {
		for (final var w : getRefWidgets()) {
			w.setParentCursorPosition(
				cursorXPositionOnContent,
				cursorYPositionOnContent
			);
		}
	}
}
