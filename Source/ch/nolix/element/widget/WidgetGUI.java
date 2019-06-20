//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.painter.IPainter;

//class
public final class WidgetGUI extends BorderWidget<WidgetGUI, WidgetGUILook> implements IGUI<WidgetGUI> {
	
	//attributes
	private final Label titleLabel = new Label();
	private final WidgetGUIInternalWidget GUIWidget = new WidgetGUIInternalWidget();
	private final VerticalStack mainVerticalStack = new VerticalStack(titleLabel, GUIWidget);
	
	//method
	@Override
	public WidgetGUI addLayerOnTop(final IGUILayer<?> layer) {
		
		GUIWidget.addLayerOnTopOfGUI(layer);
		
		return this;
	}
	
	//method
	@Override
	public WidgetGUI addLayerOnTop(final Widget<?, ?> rootWidget) {
		
		GUIWidget.addLayerOnTopOfGUI(rootWidget);
		
		return this;
	}
	
	//method
	@Override
	public WidgetGUI clear() {
		
		GUIWidget.clearGUI();
		
		return this;
	}
	
	//method
	@Override
	public void close() throws Exception {
		GUIWidget.closeGUI();
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
		return GUIWidget.getRefWidgetsOfGUIRecursively();
	}
	
	//method
	@Override
	public int getViewAreaHeight() {
		return getContentAreaHeight();
	}
	
	//method
	@Override
	public int getViewAreaWidth() {
		return getContentAreaWidth();
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
		return GUIWidget.GUIIsClosed();
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return GUIWidget.GUIIsEmpty();
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	@Override
	public void refresh() {}
	
	//method
	@Override
	public WidgetGUI removeTopLayer() {
		
		GUIWidget.removeTopLayerOfGUI();
		
		return this;
	}
	
	@Override
	public void recalculate() {
		titleLabel.setText(GUIWidget.getTitleOfGUI());
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
	protected void fillUpChildWidgets(final List<Widget<?, ?>> list) {
		list.addAtEnd(mainVerticalStack);
	}
	
	//method
	@Override
	protected void fillUpConfigurableChildWidgets(final List<Widget<?, ?>> list) {}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		return mainVerticalStack.getHeight();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		return mainVerticalStack.getWidth();
	}
}
