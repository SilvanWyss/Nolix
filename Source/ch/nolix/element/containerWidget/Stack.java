//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link Stack} is a {@link ContainerWidget} that places its widgets linearly.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 350
 * @param <S> The type of a {@link Stack}.
 */
public abstract class Stack<S extends Stack<S>> extends ContainerWidget<S, StackLook> implements Clearable<S> {
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//attribute
	private final MutableOptionalValue<Integer> elementMargin =
	new MutableOptionalValue<>(
		ELEMENT_MARGIN_HEADER,
		em -> setElementMargin(em),
		BaseNode::getOneAttributeAsInt,
		em -> Node.withOneAttribute(em)
	);
	
	//multi-attribute
	private final LinkedList<Widget<?, ?>> widgets = new LinkedList<>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
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
	 * @throws ArgumentIsNullException if the given widget is null.
	 * @throws InvalidArgumentException
	 * if the given widget belongs to another GUI than the current {@link Stack}.
	 */
	public final S addWidget(final Widget<?, ?> widget) {
		
		//Asserts that the given widget is not null.
		Validator.assertThat(widget).isOfType(Widget.class);
		
		addChildWidget(widget);
		widgets.addAtEnd(widget);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Adds the given widgets to the current {@link Stack}.
	 * 
	 * @param widgets
	 * @return the current {@link Stack}.
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link Stack}.
	 */
	public final S addWidget(final Widget<?, ?>... widgets) {
		return addWidgets(ReadContainer.forArray(widgets));
	}
	
	//method
	/**
	 * Adds the given widgets to the current {@link Stack}.
	 * 
	 * @param widgets
	 * @return the current {@link Stack}.
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link Stack}.
	 */
	public final <W extends Widget<?, ?>> S addWidgets(final Iterable<W> widgets) {
		
		widgets.forEach(w -> addWidget(w));
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final S clear() {
		
		widgets.clear();
		
		return asConcrete();
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
	public final LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final LinkedList<Node> attributes = super.getAttributes();
		
		getChildWidgets().forEach(r -> attributes.addAtEnd(r.getSpecification()));
		
		return attributes;
	}
	
	//method
	/**
	 * @return true if the current {@link Stack} container has an element margin.
	 */
	public final boolean hasElementMargin() {
		return elementMargin.hasValue();
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
		
		elementMargin.clear();
		
		return asConcrete();
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
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public final S reset() {
		
		super.reset();
		
		removeElementMargin();
		
		return asConcrete();
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
		
		Validator.assertThat(elementMargin).thatIsNamed("element margin").isNotNegative();
		
		this.elementMargin.setValue(elementMargin);
		
		return asConcrete();
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
	protected final void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
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
	protected final void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
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
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintContentArea(final StackLook stackLook, final IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurationOnSelf() {
		
		super.resetConfigurationOnSelf();
		
		removeElementMargin();
	}
}
