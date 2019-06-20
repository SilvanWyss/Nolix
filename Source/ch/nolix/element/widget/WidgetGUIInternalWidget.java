//package declaration
package ch.nolix.element.widget;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.painter.IPainter;

//package-visible class
final class WidgetGUIInternalWidget extends Widget<WidgetGUIInternalWidget, WidgetGUIInternalWidgetLook> {
	
	//attribute
	private final WidgetGUIInternalGUI internalGUI = new WidgetGUIInternalGUI();
	
	//method
	public void addLayerOnTopOfGUI(final IGUILayer<?> layer) {
		internalGUI.addLayerOnTop(layer);
	}
	
	//method
	public void addLayerOnTopOfGUI(final Widget<?, ?> rootWidget) {
		internalGUI.addLayerOnTop(rootWidget);
	}
	
	//method
	public void clearGUI() {
		internalGUI.clear();
	}
	
	//method
	public void closeGUI() {
		internalGUI.close();
	}
	
	//method
	public List<Widget<?, ?>> getRefWidgetsOfGUIRecursively() {
		return internalGUI.getRefWidgetsRecursively();
	}
	
	//method
	public String getTitleOfGUI() {
		return internalGUI.getTitle();
	}

	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean GUIIsClosed() {
		return internalGUI.isClosed();
	}
	
	//method
	public boolean GUIIsEmpty() {
		return internalGUI.isEmpty();
	}
	
	//method
	public void removeTopLayerOfGUI() {
		internalGUI.removeTopLayer();
	}
	
	//method
	@Override
	public void noteKeyPress(KeyEvent keyEvent) {
		
		super.noteKeyPress(keyEvent);
		
		internalGUI.noteKeyPress(keyEvent);
	}
	
	//method
	@Override
	public void noteKeyTyping(KeyEvent keyEvent) {
		
		super.noteKeyTyping(keyEvent);
		
		internalGUI.noteKeyTyping(keyEvent);
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		
		super.noteLeftMouseButtonPress();
		
		internalGUI.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		
		super.noteLeftMouseButtonRelease();
		
		internalGUI.noteLeftMouseButtonRelease();
	}
	
	//method
	@Override
	public void noteRightMouseButtonPress() {
		
		super.noteRightMouseButtonPress();
		
		internalGUI.noteRightMouseButtonPress();
	}
	
	//method
	@Override
	public void noteRightMouseButtonRelease() {
		
		super.noteRightMouseButtonRelease();
		
		internalGUI.noteRightMouseButtonRelease();
	}
	
	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {}
	
	//method
	@Override
	protected WidgetGUIInternalWidgetLook createWidgetLook() {
		return new WidgetGUIInternalWidgetLook();
	}
	
	//method
	@Override
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected int getHeightWhenNotCollapsed() {
		return internalGUI.getHeight();
	}
	
	//method
	@Override
	protected int getWidthWhenNotCollapsed() {
		return internalGUI.getWidth();
	}
	
	//method
	@Override
	protected void paint(WidgetGUIInternalWidgetLook widgetStructure, IPainter painter) {
		internalGUI.paint(painter);
	}
	
	//method
	@Override
	protected boolean viewAreaIsUnderCursor() {
		return isUnderCursor();
	}
}
