//package declaration
package ch.nolix.system.gui.containerwidget;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.MultiValueExtractor;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.WidgetGUI;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
public final class FloatContainer extends ContainerWidget<FloatContainer, FloatContainerLook> {
	
	//constant
	private static final String CHILD_HEADER = PascalCaseCatalogue.CHILD;
	
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
	
	//constructor
	public FloatContainer() {
		
		reset();
		
		setMinWidth(100);
	}
	
	//method
	public FloatContainer addWidget(final IWidget<?, ?> widget) {
		
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
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		list.addAtEnd(widgets);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return (widgets.getRefLast().getYPosition() + widgets.getRefLast().getHeight());
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return widgets.getMaxIntOrZero(IWidget::getRightXPosition);
	}
	
	//method
	@Override
	protected void noteKeyDownOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteKeyReleaseOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteKeyTypingOnSelfWhenFocused(final Key key) {
		//Does nothing.
	}
		
	//method
	@Override
	protected void noteLeftMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseMoveOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteMouseWheelRotationStepOnBorderWidgetWhenFocused(final RotationDirection rotationDirection) {
		//Does nothing.
	}
		
	//method
	@Override
	protected void noteRightMouseButtonClickOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteRightMouseButtonPressOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void noteRightMouseButtonReleaseOnContentAreaWhenEnabled() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void paintContentArea(final IPainter painter, final FloatContainerLook floatContainerLook) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		final var contentAreaWidth = getTargetWidth();
		final var widgetMargin = getRefActiveLook().getElementMargin();
		
		var y = 0;
		var x = 0;
		final var row = new LinkedList<IWidget<?, ?>>();
		for (final var w : widgets) {
			
			final var widgetWidth = w.getWidth();
			
			if (row.containsAny() && x + widgetMargin + widgetWidth > contentAreaWidth) {
				x = 0;
				y += row.getMaxInt(IWidget::getHeight) + widgetMargin;
				row.clear();
			}
			
			w.setPositionOnParent(x, y);
			row.addAtEnd(w);
			x += widgetMargin + widgetWidth;
		}
	}
	
	//method
	@Override
	protected void resetBorderWidgetConfiguration() {
		//Does nothing.
	}
	
	//method
	@Override
	protected void resetContainerWidget() {
		clear();
	}
}
