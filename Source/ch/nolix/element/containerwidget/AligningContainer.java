//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.gui.WidgetGUI;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class AligningContainer extends ContainerWidget<AligningContainer, AligningContainerLook> {
	
	//constants
	private static final String TOP_LEFT_HEADER = "TopLeft";
	private static final String TOP = "Top";
	private static final String TOP_RIGHT = "TopRight";
	private static final String LEFT = "Left";
	private static final String CENTER = "Center";
	private static final String RIGHT = "Right";
	private static final String BOTTOM_LEFT = "BottomLeft";
	private static final String BOTTOM = "Bottom";
	private static final String BOTTOM_RIGHT = "BottomRight";
	
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
	
	//constructor
	public AligningContainer() {
		getRefBaseLook().setPaddings(10);
	}
	
	//method
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		switch (attribute.getHeader()) {
			case TOP:
				setOnTop(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case TOP_LEFT_HEADER:
				setOnTopLeft(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case TOP_RIGHT:
				setOnTopRight(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case LEFT:
				setOnLeft(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case CENTER:
				setOnCenter(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case RIGHT:
				setOnRight(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case BOTTOM_LEFT:
				setOnBottomLeft(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case BOTTOM:
				setOnBottom(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			case BOTTOM_RIGHT:
				setOnBottomRight(WidgetGUI.createWidgetFrom(attribute.getRefOneAttribute()));
				break;
			default:
				super.addOrChangeAttribute(attribute);
		}
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
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		super.fillUpAttributesInto(list);
		
		if (topLeftSlot.containsAny()) {
			list.addAtEnd(topLeftSlot.getRefWidget().getSpecification());
		}
		
		if (topSlot.containsAny()) {
			list.addAtEnd(topSlot.getRefWidget().getSpecification());
		}
		
		if (topRightSlot.containsAny()) {
			list.addAtEnd(topRightSlot.getRefWidget().getSpecification());
		}
		
		if (leftSlot.containsAny()) {
			list.addAtEnd(leftSlot.getRefWidget().getSpecification());
		}
		
		if (centerSlot.containsAny()) {
			list.addAtEnd(centerSlot.getRefWidget().getSpecification());
		}
		
		if (rightSlot.containsAny()) {
			list.addAtEnd(rightSlot.getRefWidget().getSpecification());
		}
		
		if (bottomLeftSlot.containsAny()) {
			list.addAtEnd(bottomLeftSlot.getRefWidget().getSpecification());
		}
		
		if (bottomSlot.containsAny()) {
			list.addAtEnd(bottomSlot.getRefWidget().getSpecification());
		}
		
		if (bottomRightSlot.containsAny()) {
			list.addAtEnd(bottomRightSlot.getRefWidget().getSpecification());
		}
	}
	
	//method
	public AligningContainer setOnBottom(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		bottomSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomLeft(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		bottomLeftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomRight(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		bottomRightSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnCenter(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		centerSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnLeft(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		leftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnRight(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		rightSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTop(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		topSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopLeft(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
		topLeftSlot.setWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopRight(final Widget<?, ?> widget) {
		
		addChildWidget(widget);
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
	protected void fillUpShownWidgets(final LinkedList<Widget<?, ?>> list) {
		fillUpChildWidgets(list);
	}
	
	//method
	@Override
	protected int getNaturalContentAreaHeight() {
		return
		Calculator.getMax(
			topLeftSlot.getHeight() + leftSlot.getHeight() + bottomLeftSlot.getHeight(),
			topSlot.getHeight() + centerSlot.getHeight() + bottomSlot.getHeight(),
			topRightSlot.getHeight() + rightSlot.getHeight() + bottomRightSlot.getHeight()
		);
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
	protected void paintContentArea(final AligningContainerLook borderWidgetLook, final IPainter painter) {}
	
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
	protected void resetBorderWidgetConfigurationOnSelf() {}
	
	//method
	@Override
	protected void resetContainerWidget() {}
}
