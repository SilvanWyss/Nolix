//package declaration
package ch.nolix.element.gui.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.element.base.MutableOptionalValueExtractor;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.base.WidgetGUI;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.painterapi.IPainter;

//class
public final class AligningContainer extends ContainerWidget<AligningContainer, AligningContainerLook> {
	
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
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
		Widget::getSpecification
	);
	
	//constructor
	public AligningContainer() {
		
		reset();
		
		getRefLook().setPaddingForState(WidgetLookState.BASE, 10);
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
	protected AligningContainerLook createLook() {
		return new AligningContainerLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final LinkedList<Widget<?, ?>> list) {
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
	protected void fillUpWidgetsForPainting(final LinkedList<Widget<?, ?>> list) {
		fillUpChildWidgets(list);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return
		Calculator.getMax(topLeftSlot.getHeight(), topSlot.getHeight(), topRightSlot.getHeight())
		+ Calculator.getMax(leftSlot.getHeight(), centerSlot.getHeight(), rightSlot.getHeight())
		+ Calculator.getMax(bottomLeftSlot.getHeight(), bottomSlot.getHeight(), bottomRightSlot.getHeight());
	}
	
	//method
	@Override
	protected int getNaturalContentAreaWidth() {
		return
		Calculator.getMax(
			topLeftSlot.getWidth() + topSlot.getWidth() + topRightSlot.getWidth(),
			leftSlot.getWidth() + centerSlot.getWidth() + rightSlot.getWidth(),
			bottomLeftSlot.getWidth() + bottomSlot.getWidth() + bottomRightSlot.getWidth()
		);
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
	protected void paintContentArea(final IPainter painter, final AligningContainerLook aligningContainerLook) {}
	
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
	protected void resetBorderWidgetConfiguration() {}
	
	//method
	@Override
	protected void resetContainerWidget() {}
}
