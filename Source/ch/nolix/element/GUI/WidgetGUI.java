//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.element.painter.IPainter;

//class
public final class WidgetGUI extends BorderWidget<WidgetGUI, WidgetGUILook>
implements IGUI<WidgetGUI> {
	
	//attribute
	private boolean closed = false;
	
	//optional attribute
	private Widget<?, ?> rootWidget;
	
	//method
	public WidgetGUI clear() {
		
		rootWidget = null;
		
		return this;
	}
	
	//method
	public void close() throws Exception {
		closed = true;
	}
	
	//method
	public boolean containsElement(final String name) {
		return getRefOwnWidgetsRecursively().contains(w -> w.hasName(name));
	}
	
	//method
	public CursorIcon getContentAreaCursorIcon() {
		return getCustomCursorIcon();
	}
	
	//method
	public IGUIController getRefController() {
		//TODO
		return null;
	}
	
	//method
	public Widget<?, ?> getRefRootWidget() {
		
		if (isEmpty()) {
			throw new UnexistingAttributeException(this, "root widget");
		}
		
		return rootWidget;
	}
	
	//method
	public List<Widget<?, ?>> getRefWidgetsRecursively() {
		
		if (isEmpty()) {
			return new List<Widget<?, ?>>();
		}
		
		//For a better performance, this implementation does not use all comfortable methods.
		return rootWidget.getRefOwnWidgetsRecursively();
	}
	
	//method
	public boolean hasController() {
		// TODO
		return false;
	}
	
	//method
	public boolean hasRole(final String role) {
		return false;
	}
	
	//method
	public boolean isClosed() {
		return closed;
	}
	
	//method
	public boolean isEmpty() {
		return (rootWidget == null);
	}
	
	//method
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
				
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteLeftMouseButtonPress();
		}
	}
	
	//method
	public void noteLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteLeftMouseButtonPress();
		}
	}
	
	//method
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteMouseMove();
		}
	}
	
	//method
	public void noteRightMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonPress();
				
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteRightMouseButtonPress();
		}
	}
	
	//method
	public void noteRighttMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteRightMouseButtonPress();
		
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteRightMouseButtonPress();
		}
	}
	
	//method
	public void refresh() {}
	
	//method
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
		setProposalWidth(500);
		setProposalHeight(200);
		getRefBaseLook().setBorderThicknesses(1);
	}
	
	//method
	protected WidgetGUILook createWidgetLook() {
		return new WidgetGUILook();
	}
	
	//method
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {	}
	
	//method
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRootWidget().getHeight();
	}
	
	//method
	protected int getContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRootWidget().getWidth();
	}
	
	//method
	protected void paintContentArea(
		final WidgetGUILook borderWidgetLook,
		final IPainter painter
	) {
		if (containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.paint(painter);
		}
	}
}
