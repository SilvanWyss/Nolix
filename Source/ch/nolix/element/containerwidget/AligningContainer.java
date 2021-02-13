//package declaration
package ch.nolix.element.containerwidget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.Node;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;

//class
public final class AligningContainer extends ContainerWidget<AligningContainer, AligningContainerLook> {
	
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
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//TODO: Implement.
	}
	
	//method
	public AligningContainer setOnBottom(final Widget<?, ?> widget) {
		
		bottomSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomLeft(final Widget<?, ?> widget) {
		
		bottomLeftSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnBottomRight(final Widget<?, ?> widget) {
		
		bottomRightSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnCenter(final Widget<?, ?> widget) {
		
		centerSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnLeft(final Widget<?, ?> widget) {
		
		leftSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnRight(final Widget<?, ?> widget) {
		
		rightSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTop(final Widget<?, ?> widget) {
		
		topSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopLeft(final Widget<?, ?> widget) {
		
		topLeftSlot.setWidget(widget);
		addChildWidget(widget);
		
		return this;
	}
	
	//method
	public AligningContainer setOnTopRight(final Widget<?, ?> widget) {
		
		topRightSlot.setWidget(widget);
		addChildWidget(widget);
		
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
	protected void fillUpShownWidgets(LinkedList<Widget<?, ?>> list) {
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
