//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.widgetguiapi.ILayer;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidget;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetGUI;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetParent;

//class
public final class WidgetParent implements IWidgetParent {
	
	//optional attributes
	private final ILayer<?> layer;
	private final IWidget<?, ?> widget;
	
	//constructor
	WidgetParent(final ILayer<?> layer, final IWidget<?, ?> childWidget) {
		
		GlobalValidator.assertThat(layer).thatIsNamed(Layer.class).isNotNull();
		GlobalValidator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		this.layer = layer;
		widget = null;
	}
	
	//constructor
	WidgetParent(final IWidget<?, ?> widget, final IWidget<?, ?> childWidget) {
		
		GlobalValidator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		GlobalValidator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		layer = null;
		this.widget = widget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public boolean belongsToGUI() {
		
		if (layer != null) {
			return layer.belongsToGUI();
		}
		
		return widget.belongsToGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public IWidgetGUI<?> getRefGUI() {
		
		if (layer != null) {
			return layer.getParentGUI();
		}
		
		return widget.getParentGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public ILayer<?> getRefLayer() {
		
		if (layer != null) {
			return layer;
		}
		
		return widget.getParentLayer();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public IWidget<?, ?> getRefWidget() {
		
		//Asserts that the current WidgetParent is a widget.
		if (widget == null) {
			throw new InvalidArgumentException(this, "is not a Widget");
		}
		
		return widget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public WidgetParentType getType() {
		
		if (layer != null) {
			return WidgetParentType.LAYER;
		}
		
		return WidgetParentType.WIDGET;
	}
		
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public int getXPositionOnGUIViewArea() {
		
		if (layer != null) {
			return 0; 
		}
		
		return widget.getXPositionOnGUIViewArea();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	@Override
	public int getYPositionOnGUIViewArea() {
		
		if (layer != null) {
			return 0; 
		}
		
		return widget.getYPositionOnGUIViewArea();
	}
	
	//method
	@Override
	public boolean GUIIsClosed() {
		return (belongsToGUI() && getRefGUI().isClosed());
	}
	
	//method
	@Override
	public boolean isLayer() {
		return (layer != null);
	}
	
	//method
	@Override
	public boolean isWidget() {
		return (widget != null);
	}
}
