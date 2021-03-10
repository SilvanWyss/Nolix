//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link Stack} is a {@link ContainerWidget} that places its widgets linearly.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 350
 * @param <S> is the type of a {@link Stack}.
 */
public abstract class Stack<S extends Stack<S>> extends ContainerWidget<S, StackLook> {
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//attribute
	private final MutableOptionalValue<Integer> elementMargin =
	new MutableOptionalValue<>(
		ELEMENT_MARGIN_HEADER,
		this::setElementMargin,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//multi-attribute
	private final LinkedList<Widget<?, ?>> widgets = new LinkedList<>();
	
	//constructor
	/**
	 * Creates a new {@link Stack}.
	 */
	public Stack() {
		setElementMargin(10);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			addWidget(WidgetGUI.createWidgetFrom(attribute));
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
	 * @param <W> is the type of the given {@link Widget}s.
	 * @return the current {@link Stack}.
	 * @throws ArgumentIsNullException if one of the given widgets is null.
	 * @throws InvalidArgumentException
	 * if one of the given widgets belongs to another GUI than the current {@link Stack}.
	 */
	public final <W extends Widget<?, ?>> S addWidgets(final Iterable<W> widgets) {
		
		widgets.forEach(this::addWidget);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void clear() {
		widgets.clear();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		for (final var cw : getChildWidgets()) {
			list.addAtEnd(cw.getSpecification());
		}
	}
	
	//method
	/**
	 * @return the element margin of the current {@link Stack}.
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
	 * @return true if the current {@link Stack} container has an element margin.
	 */
	public final boolean hasElementMargin() {
		return elementMargin.hasValue();
	}
	
	//method
	/**
	 * Removes the element margin of the current {@link Stack}.
	 */
	public final void removeElementMargin() {
		elementMargin.clear();
	}
	
	//method
	/**
	 * Removes the given widget from the current {@link Stack}.
	 *
	 * @param widget
	 * @throws InvalidArgumentException
	 * if the current {@link Stack} does not contain the given widget.
	 */
	public final void removeWidget(final Widget<?, ?> widget) {
		widgets.removeFirst(widget);
	}
		
	//method
	/**
	 * Sets the element margin of the current {@link Stack}.
	 * 
	 * @param elementMargin
	 * @return the current {@link Stack}.
	 * @throws NonPositiveArgumentException if the given elementMargin is not positive.
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
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return false;
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
	protected final void noteMouseMoveOnContentAreaWhenEnabled() {}
	
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
	protected void paintContentArea(final StackLook stackLook, final IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetBorderWidgetConfigurationOnSelf() {
		removeElementMargin();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetContainerWidget() {
		clear();
	}
}
