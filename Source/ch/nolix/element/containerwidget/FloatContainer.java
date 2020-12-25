//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, FloatContainerLook> implements Clearable {
	
	//multi-attribute
	private final LinkedList<Widget<?, ?>> widgets = new LinkedList<>();
	
	//own imports
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			addWidget(WidgetGUI.createWidgetFrom(attribute));
			return;
		}
		
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	public FloatContainer addWidget(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		widgets.addAtEndRegardingSingularity(widget);
		
		return this;
	}
	
	//method
	public FloatContainer addWidget(final Widget<?, ?>... widgets) {
		
		for (final var w : widgets) {
			addWidget(w);
		}
		
		return this;
	}
	
	//method
	public FloatContainer addWidgets(final Iterable<Widget<?, ?>> widets) {
		
		widets.forEach(this::addWidget);
		
		return this;
	}
	
	//method
	@Override
	public void clear() {
		widgets.clear();
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		attributes.addAtEnd(widgets, Widget::getSpecification);
		
		return attributes;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return widgets.isEmpty();
	}
	
	//method
	@Override
	public void reset() {
		
		super.reset();
		
		widgets.clear();
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return true;
	}
		
	//method
	@Override
	protected FloatContainerLook createLook() {
		return new FloatContainerLook();
	}

	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return
		widgets.getRefLast().getYPosition()
		+ getRefLook().getRecursiveOrDefaultWidgetMargin()
		+ widgets.getRefLast().getHeight();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		
		final var look = getRefLook();
		
		if (look.hasRecursiveProposeContentWidth()) {
			
			if (isEmpty()) {
				return look.getRecursiveOrDefaultProposeContentWidth();
			}
			
			return
			Calculator.getMax(
				look.getRecursiveOrDefaultProposeContentWidth(),
				widgets.getMaxInt(Widget::getWidth)
			);
		}
		
		if (isEmpty()) {
			return getProposedContentAreaWidth();
		}
			
		return
		Calculator.getMax(
			getProposedContentAreaWidth(),
			widgets.getMaxInt(Widget::getWidth)
		);
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
	protected void paintContentArea(final FloatContainerLook floatContainerLook, final IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void recalculateSelfStage2() {
		
		final var contentAreaWidth = getContentAreaWidth();
		final var widgetMargin = getRefLook().getRecursiveOrDefaultWidgetMargin();
		
		var y = 0;
		var x = 0;
		final var row = new LinkedList<Widget<?, ?>>();
		for (final var w : widgets) {
			
			final var widgetWidth = w.getWidth();
			
			if (row.containsAny() && x + widgetMargin + widgetWidth > contentAreaWidth) {
				x = 0;
				y += row.getMaxInt(Widget::getHeight) + widgetMargin;
				row.clear();
			}
			
			w.setPositionOnParent(x, y);
			row.addAtEnd(w);
			x += widgetMargin + widgetWidth;
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelfStage3() {}
}
