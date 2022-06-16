//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.PascalCaseCatalogue;
import ch.nolix.system.element.MultiValueExtractor;
import ch.nolix.system.element.MutableOptionalValue;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.WidgetGUI;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
/**
 * A {@link Stack} is a {@link ContainerWidget} that places its widgets linearly.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @param <S> is the type of a {@link Stack}.
 */
public abstract class Stack<S extends Stack<S>> extends ContainerWidget<S, StackLook> {
	
	//constant
	private static final String ELEMENT_MARGIN_HEADER = "ElementMargin";
	
	//constant
	private static final String CHILD_HEADER = PascalCaseCatalogue.CHILD;
	
	//attribute
	private final MutableOptionalValue<Integer> elementMargin =
	new MutableOptionalValue<>(
		ELEMENT_MARGIN_HEADER,
		this::setElementMargin,
		BaseNode::getOneAttributeAsInt,
		Node::withAttribute
	);
	
	//multi-attribute
	private final LinkedList<IWidget<?, ?>> widgets = new LinkedList<>();
	
	//attribute
	@SuppressWarnings("unused")
	private final MultiValueExtractor<IWidget<?, ?>> widgetsExtractor =
	new MultiValueExtractor<>(
		CHILD_HEADER,
		this::addWidget,
		this::getChildWidgets,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
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
	public final S addWidget(final IWidget<?, ?> widget) {
		
		//Asserts that the given widget is not null.
		GlobalValidator.assertThat(widget).isOfType(Widget.class);
		
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
		
		GlobalValidator.assertThat(elementMargin).thatIsNamed("element margin").isNotNegative();
		
		this.elementMargin.setValue(elementMargin);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean contentAreaMustBeExpandedToTargetSize() {
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
	protected final void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
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
	protected final void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		for (final var w : widgets) {
			if (w.isExpanded()) {
				list.addAtEnd(w);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyDownOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
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
	protected final void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
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
	protected final void paintContentArea(final IPainter painter, final StackLook stackLook) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetBorderWidgetConfiguration() {
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
