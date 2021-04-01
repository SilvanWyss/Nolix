//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, OldFloatContainerLook> {
	
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
	protected OldFloatContainerLook createLook() {
		return new OldFloatContainerLook();
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
	protected void noteKeyPressOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {}
		
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
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {}
		
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
	protected void paintContentArea(final OldFloatContainerLook floatContainerLook, final IPainter painter) {}
	
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
