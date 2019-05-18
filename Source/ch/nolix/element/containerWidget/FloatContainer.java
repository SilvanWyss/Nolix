//package declaration
package ch.nolix.element.containerWidget;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.GUI.GUI;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.Widget;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, FloatContainerLook>
implements Clearable<FloatContainer> {
	
	//multi-attribute
	private final List<Widget<?, ?>> widgets = new List<Widget<?, ?>>();
	
	//constructor
	public FloatContainer() {
		resetAndApplyDefaultConfiguration();
	}
	
	//constructor
	public FloatContainer(final Iterable<Widget<?, ?>> widets) {
		
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
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (GUI.canCreateWidgetFrom(attribute)) {
			addWidget(GUI.createWidget(attribute));
			return;
		}
		
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	public FloatContainer addWidget(final Widget<?, ?> widget) {
		
		widget.setParentWidget(this);
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
	public List<DocumentNode> getAttributes() {
		
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
	public void recalculate() {
		
		final var contentAreaWidth = getContentAreaWidth();
		final var widgetMargin = getRefCurrentLook().getRecursiveOrDefaultWidgetMargin();
		
		var y = 0;
		var x = 0;
		final var row = new List<Widget<?, ?>>();
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
		
		super.recalculate();
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
	protected FloatContainerLook createWidgetLook() {
		return new FloatContainerLook();
	}

	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return
		widgets.getRefLast().getYPositionOnParent()
		+ getRefCurrentLook().getRecursiveOrDefaultWidgetMargin()
		+ widgets.getRefLast().getHeight();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		
		final var currentLook = getRefCurrentLook();
		
		if (currentLook.hasRecursiveProposeContentWidth()) {
			
			if (isEmpty()) {
				return currentLook.getRecursiveOrDefaultProposeContentWidth();
			}
			
			return
			Calculator.getMax(
				currentLook.getRecursiveOrDefaultProposeContentWidth(),
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
	@Override
	protected void paintContentArea(
		final FloatContainerLook borderWidgetLook,
		final IPainter painter
	) {
		widgets.forEach(w -> w.paint(painter));
	}
}
