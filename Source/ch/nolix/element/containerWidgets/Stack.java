//package declaration
package ch.nolix.element.containerWidgets;

//own imports
import ch.nolix.core.containers.List;
import ch.nolix.core.containers.ReadContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI_API.Widget;
import ch.nolix.element.core.NonNegativeInteger;

//abstract class
/**
 * A {@link Stack} is a {@link ContainerWidget} that places its widgets linearly.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 310
 * @param <S> The type of a {@link Stack}.
 */
public abstract class Stack<S extends Stack<S>> extends ContainerWidget<S, StackLook> implements Clearable<S> {
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//optional attribute
	private NonNegativeInteger elementMargin;
	
	//multi-attribute
	private final List<Widget<?, ?>> widgets = new List<>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (LayerGUI.canCreateWidgetFrom(attribute)) {
			addWidget(LayerGUI.createWidgetFrom(attribute));
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
		Validator.suppose(widget).isOfType(Widget.class);
		
		addChildWidget(widget);
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
	@Override
	public final S clear() {
		
		widgets.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the active element margin of the current {@link Stack}.
	 */
	public final int getElementMargin() {
		
		//Handles the case that the current {@link Stack} does not have an element margin.
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
	@Override
	public final List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		//Handles the case that the current {@link Stack} has an element margin.
		if (hasElementMargin()) {
			attributes.addAtEnd(
				elementMargin.getSpecificationAs(ELEMENT_MARGIN_HEADER)
			);
		}
		
		getChildWidgets().forEach(r -> attributes.addAtEnd(r.getSpecification()));
		
		return attributes;
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
	 * @return true if the current {@link Stack} does not contain a widget.
	 */
	@Override
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
	 * @throws InvalidArgumentException
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
	@Override
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
	@Override
	protected final void applyDefaultConfigurationWhenHasBeenReset() {
		setElementMargin(10);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final StackLook createLook() {
		return new StackLook();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		for (final var w : widgets) {
			if (w.isEnabled()) {
				list.addAtEnd(w);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void fillUpPaintableWidgets(final List<Widget<?, ?>> list) {
		for (final var w : widgets) {
			if (w.isEnabled()) {
				list.addAtEnd(w);
			}
		}
	}
}
