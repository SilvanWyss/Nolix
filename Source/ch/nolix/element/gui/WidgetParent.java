//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
public final class WidgetParent {
	
	//optional attributes
	private final WidgetGUI<?> mGui;
	private final Widget<?, ?> widget;
	
	//visibility-reduced constructor
	WidgetParent(final WidgetGUI<?> pGUI, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(pGUI).thatIsNamed(WidgetGUI.class).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		mGui = pGUI;
		widget = null;
	}
	
	//visibility-reduced constructor
	WidgetParent(final Widget<?, ?> widget, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(widget).thatIsNamed(Widget.class).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		mGui = null;
		this.widget = widget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public boolean belongsToGUI() {
		return (mGui != null || widget.belongsToGUI());
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public WidgetGUI<?> getRefGUI() {
		
		if (mGui != null) {
			return mGui;
		}
		
		return widget.getParentGUI();
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
		
		if (mGui != null) {
			return WidgetParentType.GUI;
		}
		
		return WidgetParentType.WIDGET;
	}
		
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getXPositionOnGUI() {
		
		if (mGui != null) {
			return 0; 
		}
		
		return widget.getXPositionOnGUI();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	public int getYPositionOnGUI() {
		
		if (mGui != null) {
			return 0; 
		}
		
		return widget.getYPositionOnGUI();
	}
	
	//method
	public boolean GUIIsClosed() {
		return (belongsToGUI() && getRefGUI().isClosed());
	}
	
	//For a better performance, this implementation does not use all comfortable methods.
	//method
	public boolean is(final WidgetGUI<?> pGUI) {
		return (mGui != null && mGui == pGUI);
	}
	
	//For a better performance, this implementation does not use all comfortable methods.
	//method
	public boolean is(final Widget<?, ?> widget) {
		return (this.widget != null && this.widget == widget);
	}
	
	//method
	public boolean isGUI() {
		return (mGui != null);
	}
	
	//method
	public boolean isWidget() {
		return (widget != null);
	}
}
