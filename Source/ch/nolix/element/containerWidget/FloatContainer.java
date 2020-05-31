//package declaration
package ch.nolix.element.containerWidget;

import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.element.GUI.LayerGUI;
import ch.nolix.element.GUI.Widget;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, FloatContainerLook>
implements Clearable<FloatContainer> {
	
	//multi-attribute
	private final LinkedList<Widget<?, ?>> widgets = new LinkedList<>();
	
	//constructor
	public FloatContainer() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public FloatContainer(final Iterable<Widget<?, ?>> widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidgets(widgets);
	}
	
	//constructor
	public FloatContainer(final Widget<?, ?>... widgets) {
		
		resetAndApplyDefaultConfiguration();
		
		addWidget(widgets);
	}

	//own imports
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		if (LayerGUI.canCreateWidgetFrom(attribute)) {
			addWidget(LayerGUI.createWidgetFrom(attribute));
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
		
		widets.forEach(w -> addWidget(w));
		
		return this;
	}
	
	//method
	@Override
	public FloatContainer clear() {
		
		widgets.clear();
		
		return this;
	}
	
	//method
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		attributes.addAtEnd(widgets, w -> w.getSpecification());
		
		return attributes;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return widgets.isEmpty();
	}
	
	//method
	@Override
	public void recalculateSelf() {
		
		final var contentAreaWidth = getContentAreaWidth();
		final var widgetMargin = getRefLook().getRecursiveOrDefaultWidgetMargin();
		
		var y = 0;
		var x = 0;
		final var row = new LinkedList<Widget<?, ?>>();
		for (final var w : widgets) {
			
			final var widgetWidth = w.getWidth();
			
			if (row.containsAny() && x + widgetMargin + widgetWidth > contentAreaWidth) {
				x = 0;
				y += row.getMaxByInt(w2 -> w2.getHeight()) + widgetMargin;
				row.clear();
			}
			
			w.setPositionOnParent(x, y);
			row.addAtEnd(w);
			x += widgetMargin + widgetWidth;
		}
		
		super.recalculateSelf();
	}
	
	//method
	@Override
	public FloatContainer reset() {
		
		super.reset();
		
		widgets.clear();
		
		return this;
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
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
				widgets.getMaxByInt(w -> w.getWidth())
			);
		}
		
		if (isEmpty()) {
			return getProposedContentAreaWidth();
		}
			
		return
		Calculator.getMax(
			getProposedContentAreaWidth(),
			widgets.getMaxByInt(w -> w.getWidth())
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonClickOnShowreaWhenEnabled() {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteLeftMouseButtonPressOnShowAreaWhenEnabled() {}
}
