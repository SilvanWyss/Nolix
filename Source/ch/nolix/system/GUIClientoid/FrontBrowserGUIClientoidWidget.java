//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.element.GUI.BorderWidget;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.painter.IPainter;

//package-visible class
final class FrontBrowserGUIClientoidWidget
extends BorderWidget<FrontBrowserGUIClientoidWidget, FrontBrowserGUIClientoidWidgetLook> {
	
	//constants
	private static final String CONTENT_WIDTH_HEADER = "ContentWidth";
	private static final String CONTENT_HEIGHT_HEADER = "ContentHeight";
	
	//attribute
	private final MutableProperty<PositiveInteger> contentWidth =
	new MutableProperty<PositiveInteger>(
		CONTENT_WIDTH_HEADER,
		cw -> setContentWidth(cw.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		cw -> cw.getSpecification()
	);
	
	//attribute
	private final MutableProperty<PositiveInteger> contentHeight =
	new MutableProperty<PositiveInteger>(
		CONTENT_HEIGHT_HEADER,
		ch -> setContentHeight(ch.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		ch -> ch.getSpecification()
	);
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public FrontBrowserGUIClientoidWidget setContentHeight(final int contentHeight) {
		
		this.contentHeight.setValue(new PositiveInteger(contentHeight));
		
		return this;
	}
	
	//method
	public FrontBrowserGUIClientoidWidget setContentWidth(final int contentWidth) {
		
		this.contentWidth.setValue(new PositiveInteger(contentWidth));
		
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
		return contentHeight.getValue().getValue();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		return contentWidth.getValue().getValue();
	}
	
	//method
	@Override
	protected void paintContentArea(
		final FrontBrowserGUIClientoidWidgetLook frontBrowserGUIClientoidWidgetLook,
		final IPainter painter
	) {
		//TODO
	}
}
