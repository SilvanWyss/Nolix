//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, FloatContainerLook> {
	
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
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		for (final var w : widgets) {
			list.addAtEnd(w.getSpecification());
		}
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
	protected int getNaturalContentAreaHeight() {
		
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
	protected int getNaturalContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		//TODO: Refactor FloatContainer.
		return 500;
	}
	
	//method
	@Override
	protected void noteKeyPressOnContentAreaWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {}
	
	//method
	@Override
	protected void paintContentArea(final FloatContainerLook floatContainerLook, final IPainter painter) {}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		final var contentAreaWidth = getNaturalContentAreaWidth();
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
	@Override
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	@Override
	protected void resetContainerWidget() {
		clear();
	}
}
