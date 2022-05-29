//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.systemapi.guiapi.widgetguiapi.IWidgetGUI;

//class
public final class WidgetParent {
	
	//optional attributes
	private final Layer layer;
	private final Widget<?, ?> widget;
	
	//constructor
	WidgetParent(final Layer layer, final Widget<?, ?> childWidget) {
		
		GlobalValidator.assertThat(layer).thatIsNamed(Layer.class).isNotNull();
		GlobalValidator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		this.layer = layer;
		widget = null;
	}
	
	//constructor
	WidgetParent(final Widget<?, ?> widget, final Widget<?, ?> childWidget) {
		
		GlobalValidator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		GlobalValidator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		layer = null;
		this.widget = widget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public boolean belongsToGUI() {
		
		if (layer != null) {
			return layer.belongsToGUI();
		}
		
		return widget.belongsToGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public IWidgetGUI<?> getRefGUI() {
		
		if (layer != null) {
			return layer.getParentGUI();
		}
		
		return widget.getParentGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public Layer getRefLayer() {
		
		if (layer != null) {
			return layer;
		}
		
		return widget.getParentLayer();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public Widget<?, ?> getRefWidget() {
		
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
	public int getXPositionOnGUI() {
		
		if (layer != null) {
			return 0; 
		}
		
		return widget.getXPositionOnGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getYPositionOnGUI() {
		
		if (layer != null) {
			return 0; 
		}
		
		return widget.getYPositionOnGUI();
	}
	
	//method
	public boolean GUIIsClosed() {
		return (belongsToGUI() && getRefGUI().isClosed());
	}
	
	//method
	public boolean isLayer() {
		return (layer != null);
	}
	
	//method
	public boolean isWidget() {
		return (widget != null);
	}
}
