//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
public final class WidgetParent {
	
	//optional attributes
	private final Layer layer;
	private final Widget<?, ?> widget;
	
	//visibility-reduced constructor
	WidgetParent(final Layer layer, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(layer).thatIsNamed(Layer.class).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		this.layer = layer;
		widget = null;
	}
	
	//visibility-reduced constructor
	WidgetParent(final Widget<?, ?> widget, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
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
	public WidgetGUI<?> getRefGUI() {
		
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
