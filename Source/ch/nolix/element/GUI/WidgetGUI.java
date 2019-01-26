//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.painter.IPainter;

//class
public final class WidgetGUI extends BorderWidget<WidgetGUI, WidgetGUILook>
implements IGUI<WidgetGUI> {
	
	//attribute
	private boolean closed = false;
	
	//optional attribute
	private Widget<?, ?> rootWidget;
	
	//method
	@Override
	public WidgetGUI clear() {
		
		rootWidget = null;
		
		return this;
	}
	
	//method
	@Override
	public void close() throws Exception {
		closed = true;
	}
	
	//method
	@Override
	public boolean containsElement(final String name) {
		return getRefOwnWidgetsRecursively().contains(w -> w.hasName(name));
	}
	
	//method
	@Override
	public CursorIcon getContentAreaCursorIcon() {
		return getCustomCursorIcon();
	}
	
	//method
	@Override
	public IGUIController getRefController() {
		return getParentGUI().getRefController();
	}
	
	//method
	public Widget<?, ?> getRefRootWidget() {
		
		if (isEmpty()) {
			throw new UnexistingAttributeException(this, "root widget");
		}
		
		return rootWidget;
	}
	
	//method
	@Override
	public List<Widget<?, ?>> getRefWidgetsRecursively() {
		
		if (isEmpty()) {
			return new List<Widget<?, ?>>();
		}
		
		//For a better performance, this implementation does not use all comfortable methods.
		return rootWidget.getRefOwnWidgetsRecursively();
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
		return closed;
	}
	
	//method
	@Override
	public boolean isEmpty() {
		return (rootWidget == null);
	}
	
	//method
	@Override
	public boolean isRootGUI() {
		return false;
	}
	
	//method
	@Override
	public boolean keepsFocus() {
		return false;
	}
	
	//method
	@Override
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
				
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteLeftMouseButtonPress();
		}
	}
	
	//method
	@Override
	public void noteLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteLeftMouseButtonPress();
		}
	}
	
	//method
	@Override
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		if (isAlive() && containsAny()) {
			
			//For a better performance, this implementation does not use all comfortable methods.
			rootWidget.noteMouseMove();
		}
	}
	
	//method
	@Override
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
	@Override
	public void refresh() {}
	
	//method
	public WidgetGUI setRootWidget(final Widget<?, ?> rootWidget) {
		
		Validator.suppose(rootWidget).thatIsNamed("root widget").isNotNull();
		
		rootWidget.setParentGUI(this);
		this.rootWidget = rootWidget;
		
		return asConcreteType();
	}
	
	//method
	@Override
	protected void applyUsableConfigurationWhenConfigurationIsReset() {
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
	protected void fillUpOwnWidgets(final List<Widget<?, ?>> list) {	}
	
	//method
	@Override
	protected int getContentAreaHeight() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRootWidget().getHeight();
	}
	
	//method
	@Override
	protected int getContentAreaWidth() {
		
		if (isEmpty()) {
			return 0;
		}
		
		return getRefRootWidget().getWidth();
	}
	
	//method
	@Override
	protected void noteSetParent() {
		if (containsAny()) {
			getRefRootWidget().setParentGUI(this);
		}
	}
	
	//method
	@Override
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
