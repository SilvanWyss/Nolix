//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.painter.IPainter;

//class
public final class WidgetGUI extends BorderWidget<WidgetGUI, WidgetGUILook> implements IGUI<WidgetGUI> {
	
	//attribute
	private final WidgetGUIInternalGUI internalGUI = new WidgetGUIInternalGUI();
	
	//method
	@Override
	public WidgetGUI addLayerOnTop(final IGUILayer<?> layer) {
		
		internalGUI.addLayerOnTop(layer);
		
		return this;
	}
	
	//method
	@Override
	public WidgetGUI addLayerOnTop(final Widget<?, ?> rootWidget) {
		
		internalGUI.addLayerOnTop(rootWidget);
		
		return this;
	}
	
	//method
	@Override
	public WidgetGUI clear() {
		
		internalGUI.clear();
		
		return this;
	}
	
	//method
	@Override
	public void close() throws Exception {
		internalGUI.close();
	}
	
	//method
	@Override
	public boolean containsElement(final String name) {
		return getChildWidgetsRecursively().contains(w -> w.hasName(name));
	}
	
	//method
	@Override
	public IGUIController getRefController() {
		return getParentGUI().getRefController();
	}
	
	//method
	@Override
	public List<Widget<?, ?>> getRefWidgetsRecursively() {
		return internalGUI.getRefWidgetsRecursively();
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		//TODO
		return 0;
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		//TODO
		return 0;
	}

	//method
	@Override
	public boolean hasController() {
		
		if (!belongsToGUI()) {
			return false;
		}
		
		return getParentGUI().hasController();
	}
	
	//method
	@Override
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	@Override
	public boolean isClosed() {
		return internalGUI.isClosed();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return internalGUI.isEmpty();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
		
	//method
	@Override
	public void noteLeftMouseButtonPressOnViewArea() {
		internalGUI.noteLeftMouseButtonPress();
	}
	
	//method
	@Override
	public void refresh() {}
	
	//method
	@Override
	public WidgetGUI removeTopLayer() {
		
		internalGUI.removeTopLayer();
		
		return this;
	}

	//method
	@Override
	protected void applyDefaultConfigurationWhenHasBeenReset() {
		setProposalWidth(500);
		setProposalHeight(200);
		getRefBaseLook().setBorderThicknesses(1);
	}
	
	//method
	@Override
	protected WidgetGUILook createWidgetLook() {
		return new WidgetGUILook();
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
		//TODO
		return 0;
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		//TODO
		return 0;
	}
	
	//method
	@Override
	protected void paintContentArea(final WidgetGUILook borderWidgetLook, final IPainter painter) {
		internalGUI.paint(painter);
	}
}
