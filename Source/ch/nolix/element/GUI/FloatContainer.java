//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.painter.IPainter;

//class
public final class FloatContainer extends Container<FloatContainer, FloatContainerLook>
implements Clearable<FloatContainer> {
	
	//multi-attribute
	private final List<Widget<?, ?>> widgets = new List<Widget<?, ?>>();
	
	//constructor
	public FloatContainer() {
		reset();
		approveProperties();
	}
	
	//constructor
	public FloatContainer(final Iterable<Widget<?, ?>> widets) {
		
		this();
		
		addWidgets(widgets);
	}
	
	//constructor
	public FloatContainer(final Widget<?, ?>... widgets) {
		
		this();
		
		addWidget(widgets);
	}

	//own imports
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		if (GUI.canCreateWidget(attribute)) {
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
	public CursorIcon getContentAreaCursorIcon() {
		
		final var widgetUnderCursor = widgets.getRefFirstOrNull(w -> w.isUnderCursor());
		
		if (widgetUnderCursor != null) {
			return widgetUnderCursor.getCursorIcon();
		}
		
		return getCustomCursorIcon();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return widgets.isEmpty();
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
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	//method
	@Override
	protected FloatContainerLook createWidgetLook() {
		return new FloatContainerLook();
	}

	//method
	@Override
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {
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
		widgets.forEach(w -> w.paintUsingPositionOnParent(painter));
	}
	
	//method
	@Override
	protected void setCursorPositionOnContentArea(
		final int cursorXPositionOnContent,
		final int cursorYPositionOnContent
	) {
		for (final var w : getRefWidgets()) {
			w.setParentCursorPosition(
				cursorXPositionOnContent,
				cursorYPositionOnContent
			);
		}
	}
	
	//method
	@Override
	protected void setPositionOnParent(
		final int relativeXPosition,
		final int relativeYPosition
	) {
		
		super.setPositionOnParent(relativeXPosition, relativeYPosition);
		
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
	}
}
