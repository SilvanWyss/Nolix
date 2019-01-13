//package declaration
package ch.nolix.system.GUIClientoid;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.painter.IPainter;

//package-visible class
final class FrontBrowserGUIClientoidWidget
extends Widget<FrontBrowserGUIClientoidWidget, FrontBrowserGUIClientoidWidgetLook> {
	
	@Override
	public CursorIcon getCursorIcon() {
		return getCustomCursorIcon();
	}
	
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	@Override
	public boolean keepsFocus() {
		return false;
	}
	
	@Override
	protected void applyUsableConfigurationWhenConfigurationIsReset() {}
	
	@Override
	protected FrontBrowserGUIClientoidWidgetLook createWidgetLook() {
		return new FrontBrowserGUIClientoidWidgetLook();
	}

	@Override
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {}

	@Override
	protected int getHeightWhenNotCollapsed() {
		//TODO
		return 0;
	}
	
	@Override
	protected int getWidthWhenNotCollapsed() {
		//TODO
		return 0;
	}
	
	@Override
	protected void paint(FrontBrowserGUIClientoidWidgetLook widgetStructure, IPainter painter) {
		//TODO: Implement.
	}
	
	@Override
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}
}
