//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.statement.Statement;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.painter.IPainter;
import ch.nolix.element.widget.BorderWidget;
import ch.nolix.element.widget.Widget;

//package-visible class
final class FrontBrowserGUIClientoidWidget
extends BorderWidget<FrontBrowserGUIClientoidWidget, FrontBrowserGUIClientoidWidgetLook> {
	
	//default values
	private static final int DEFAULT_CONTENT_WIDTH = 1000;
	private static final int DEFAULT_CONTENT_HEIGHT = 1000;
	
	//constants
	private static final String CONTENT_WIDTH_HEADER = "ContentWidth";
	private static final String CONTENT_HEIGHT_HEADER = "ContentHeight";
	
	//attribute
	private final MutableProperty<Integer> contentWidth =
	new MutableProperty<>(
		CONTENT_WIDTH_HEADER,
		cw -> setContentWidth(cw),
		s -> s.getOneAttributeAsInt(),
		cw -> DocumentNode.createWithOneAttribute(cw)
	);
	
	//attribute
	private final MutableProperty<Integer> contentHeight =
	new MutableProperty<>(
		CONTENT_HEIGHT_HEADER,
		ch -> setContentHeight(ch),
		s -> s.getOneAttributeAsInt(),
		ch -> DocumentNode.createWithOneAttribute(ch)
	);
	
	//attribute
	private FrontBroserGUIClientoidPaintManager frontBroserGUIClientoidPaintManager  =
	new FrontBroserGUIClientoidPaintManager();
	
	//constructor
	public FrontBrowserGUIClientoidWidget() {
		resetAndApplyDefaultConfiguration();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public FrontBrowserGUIClientoidWidget reset() {
		
		setContentWidth(DEFAULT_CONTENT_WIDTH);
		setContentHeight(DEFAULT_CONTENT_HEIGHT);
		
		frontBroserGUIClientoidPaintManager = new FrontBroserGUIClientoidPaintManager();
		
		super.reset();
		
		return this;
	}
	
	//method
	public FrontBrowserGUIClientoidWidget setContentHeight(final int contentHeight) {
		
		Validator.suppose(contentHeight).thatIsNamed("content height").isPositive();
		
		this.contentHeight.setValue(contentHeight);
		
		return this;
	}
	
	//method
	public FrontBrowserGUIClientoidWidget setContentWidth(final int contentWidth) {
		
		Validator.suppose(contentWidth).thatIsNamed("content width").isPositive();
		
		this.contentWidth.setValue(contentWidth);
		
		return this;
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
	//method
	@Override
	protected FrontBrowserGUIClientoidWidgetLook createWidgetLook() {
		return new FrontBrowserGUIClientoidWidgetLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		return contentHeight.getValue();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		return contentWidth.getValue();
	}
	
	//method
	@Override
	protected void paintContentArea(
		final FrontBrowserGUIClientoidWidgetLook frontBrowserGUIClientoidWidgetLook,
		final IPainter painter
	) {
		frontBroserGUIClientoidPaintManager.paint(painter);
	}
	
	//package-visible method
	void setPainterCommands(final IContainer<Statement> painterCommands) {
		frontBroserGUIClientoidPaintManager = new FrontBroserGUIClientoidPaintManager(painterCommands);
	}
}
