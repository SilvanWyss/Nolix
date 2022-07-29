//package declaration
package ch.nolix.system.gui.containerwidget;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.math.GlobalCalculator;
import ch.nolix.system.element.mutableelement.MutableOptionalValueExtractor;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.system.gui.widgetgui.WidgetGUI;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;
import ch.nolix.systemapi.guiapi.processproperty.RotationDirection;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;

//class
public final class AligningContainer extends ContainerWidget<AligningContainer, AligningContainerStyle> {
	
	//constants
	private static final String TOP_LEFT_HEADER = "TopLeft";
	private static final String TOP_HEADER = "Top";
	private static final String TOP_RIGHT_HEADER = "TopRight";
	private static final String LEFT_HEADER = "Left";
	private static final String CENTER_HEADER = "Center";
	private static final String RIGHT_HEADER = "Right";
	private static final String BOTTOM_LEFT_HEADER = "BottomLeft";
	private static final String BOTTOM_HEADER = "Bottom";
	private static final String BOTTOM_RIGHT_HEADER = "BottomRight";
	
	//attributes
	private final AligningContainerSlot topLeftSlot = new AligningContainerSlot();
	private final AligningContainerSlot topSlot = new AligningContainerSlot();
	private final AligningContainerSlot topRightSlot = new AligningContainerSlot();
	private final AligningContainerSlot leftSlot = new AligningContainerSlot();
	private final AligningContainerSlot centerSlot = new AligningContainerSlot();
	private final AligningContainerSlot rightSlot = new AligningContainerSlot();
	private final AligningContainerSlot bottomLeftSlot = new AligningContainerSlot();
	private final AligningContainerSlot bottomSlot = new AligningContainerSlot();
	private final AligningContainerSlot bottomRightSlot = new AligningContainerSlot();
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> topLeftWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		TOP_LEFT_HEADER,
		this::setOnTopLeft,
		this::containsWidgetOnTopLeft,
		this::getRefTopLeftWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> topWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		TOP_HEADER,
		this::setOnTop,
		this::containsWidgetOnTop,
		this::getRefTopWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> topRightWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		TOP_RIGHT_HEADER,
		this::setOnTopRight,
		this::containsWidgetOnTopRight,
		this::getRefTopRightWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> leftWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		LEFT_HEADER,
		this::setOnLeft,
		this::containsWidgetOnLeft,
		this::getRefLeftWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> centerWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		CENTER_HEADER,
		this::setOnCenter,
		this::containsWidgetOnCenter,
		this::getRefCenterWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> rightWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		RIGHT_HEADER,
		this::setOnRight,
		this::containsWidgetOnRight,
		this::getRefRightWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> bottomLeftWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		BOTTOM_LEFT_HEADER,
		this::setOnBottomLeft,
		this::containsWidgetOnBottomLeft,
		this::getRefBottomLeftWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> bottomWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		BOTTOM_HEADER,
		this::setOnBottom,
		this::containsWidgetOnBottom,
		this::getRefBottomWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> bottomRightWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		BOTTOM_RIGHT_HEADER,
		this::setOnBottomRight,
		this::containsWidgetOnBottomRight,
		this::getRefBottomRightWidget,
		WidgetGUI::createWidgetFrom,
		IWidget::getSpecification
	);
	
	//constructor
	public AligningContainer() {
		
		reset();
		
		getRefStyle().setPaddingForState(ControlState.BASE, 10);
	}
		
	//method
	@Override
	public void clear() {
		topLeftSlot.clear();
		topSlot.clear();
		topRightSlot.clear();
		leftSlot.clear();
		centerSlot.clear();
		rightSlot.clear();
		bottomLeftSlot.clear();
		bottomSlot.clear();
		bottomRightSlot.clear();
	}
	
	//method
	public boolean containsWidgetOnBottom() {
		return bottomSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnBottomLeft() {
		return bottomLeftSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnBottomRight() {
		return bottomRightSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnCenter() {
		return centerSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnLeft() {
		return leftSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnRight() {
		return rightSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnTop() {
		return topSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnTopLeft() {
		return topLeftSlot.containsAny();
	}
	
	//method
	public boolean containsWidgetOnTopRight() {
		return topRightSlot.containsAny();
	}
	
	//method
	public Widget<?, ?> getRefBottomLeftWidget() {
		return bottomLeftSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefBottomRightWidget() {
		return bottomRightSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefBottomWidget() {
		return bottomSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefCenterWidget() {
		return centerSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefLeftWidget() {
		return leftSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefRightWidget() {
		return rightSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefTopLeftWidget() {
		return topLeftSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefTopRightWidget() {
		return topRightSlot.getRefWidget();
	}
	
	//method
	public Widget<?, ?> getRefTopWidget() {
		return topSlot.getRefWidget();
	}
	
	//method
	public AligningContainer setOnBottom(final Widget<?, ?> widget) {
		
		bottomSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomLeft(final Widget<?, ?> widget) {
		
		bottomLeftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomRight(final Widget<?, ?> widget) {
		
		bottomRightSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnCenter(final Widget<?, ?> widget) {
		
		centerSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnLeft(final Widget<?, ?> widget) {
		
		leftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnRight(final Widget<?, ?> widget) {
		
		rightSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTop(final Widget<?, ?> widget) {
		
		topSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopLeft(final Widget<?, ?> widget) {
		
		topLeftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopRight(final Widget<?, ?> widget) {
		
		topRightSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	@Override
	protected boolean contentAreaMustBeExpandedToTargetSize() {
		return true;
	}
	
	//method
	@Override
	protected AligningContainerStyle createLook() {
		return new AligningContainerStyle();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<IWidget<?, ?>> list) {
		topLeftSlot.fillUpWidgetInto(list);
		topSlot.fillUpWidgetInto(list);
		topRightSlot.fillUpWidgetInto(list);
		leftSlot.fillUpWidgetInto(list);
		centerSlot.fillUpWidgetInto(list);
		rightSlot.fillUpWidgetInto(list);
		bottomLeftSlot.fillUpWidgetInto(list);
		bottomSlot.fillUpWidgetInto(list);
		bottomRightSlot.fillUpWidgetInto(list);
	}
	
	//method
	@Override
	protected void fillUpWidgetsForPainting(final LinkedList<IWidget<?, ?>> list) {
		fillUpChildWidgets(list);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return
		GlobalCalculator.getMax(topLeftSlot.getHeight(), topSlot.getHeight(), topRightSlot.getHeight())
		+ GlobalCalculator.getMax(leftSlot.getHeight(), centerSlot.getHeight(), rightSlot.getHeight())
		+ GlobalCalculator.getMax(bottomLeftSlot.getHeight(), bottomSlot.getHeight(), bottomRightSlot.getHeight());
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return
		GlobalCalculator.getMax(
			topLeftSlot.getWidth() + topSlot.getWidth() + topRightSlot.getWidth(),
			leftSlot.getWidth() + centerSlot.getWidth() + rightSlot.getWidth(),
			bottomLeftSlot.getWidth() + bottomSlot.getWidth() + bottomRightSlot.getWidth()
		);
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
	protected void paintContentArea(final IPainter painter, final AligningContainerStyle aligningContainerStyle) {
		//Does nothing.
	}
	
	//method
	@Override
	protected void recalculateBorderWidget() {
		
		final var contentAreaWidth = getContentArea().getWidth();
		final var contentAreaHeight = getContentArea().getHeight();
		
		if (topLeftSlot.containsAny()) {
			topLeftSlot.setPositionOnParentContentArea(0, 0);
		}
		
		if (topSlot.containsAny()) {
			topSlot.setPositionOnParentContentArea((contentAreaWidth - topSlot.getWidth()) / 2, 0);
		}
		
		if (topRightSlot.containsAny()) {
			topRightSlot.setPositionOnParentContentArea(contentAreaWidth - topRightSlot.getWidth(), 0);
		}
		
		if (leftSlot.containsAny()) {
			leftSlot.setPositionOnParentContentArea(0, (contentAreaHeight - leftSlot.getHeight()) / 2);
		}
		
		if (centerSlot.containsAny()) {
			centerSlot.setPositionOnParentContentArea(
				(contentAreaWidth - centerSlot.getWidth()) / 2,
				(contentAreaHeight - centerSlot.getHeight()) / 2
			);
		}
		
		if (rightSlot.containsAny()) {
			rightSlot.setPositionOnParentContentArea(
				contentAreaWidth - rightSlot.getWidth(),
				(contentAreaHeight - centerSlot.getHeight()) / 2
			);
		}
		
		if (bottomLeftSlot.containsAny()) {
			bottomLeftSlot.setPositionOnParentContentArea(0, contentAreaHeight - bottomLeftSlot.getHeight());
		}
		
		if (bottomSlot.containsAny()) {
			bottomSlot.setPositionOnParentContentArea(
				(contentAreaWidth - bottomSlot.getWidth()) / 2,
				contentAreaHeight - bottomSlot.getHeight()
			);
		}
		
		if (bottomRightSlot.containsAny()) {
			bottomRightSlot.setPositionOnParentContentArea(
				contentAreaWidth - bottomRightSlot.getWidth(),
				contentAreaHeight - bottomRightSlot.getHeight()
			);
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
		//Does nothing.
	}
}
