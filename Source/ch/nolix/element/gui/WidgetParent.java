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
	
	//visiblity-reduced constructor
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
	public boolean belongsToGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (mGui != null || widget.belongsToGUI());
	}
	
	//method
	public WidgetGUI<?> getRefGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		
		if (mGui != null) {
			return mGui;
		}
		
		return widget.getParentGUI();
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		
		//Asserts that the current WidgetParent is a widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (widget == null) {
			throw new InvalidArgumentException(this, "is not a Widget");
		}
		
		return widget;
	}
	
	//method
	public WidgetParentType getType() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (mGui != null ? WidgetParentType.GUI : WidgetParentType.WIDGET);
	}
		
	//method
	public int getXPositionOnGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (mGui != null ? 0 : widget.getXPositionOnGUI());
	}
	
	//method
	public int getYPositionOnGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (mGui != null ? 0 : widget.getYPositionOnGUI());
	}
	
	//method
	public boolean GUIIsClosed() {
		return (belongsToGUI() && getRefGUI().isClosed());
	}
	
	//method
	public boolean is(final WidgetGUI<?> pGUI) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (mGui != null && mGui == pGUI);
	}
	
	//method
	public boolean is(final Widget<?, ?> widget) {
		
		//For a better performance, this implementation does not use all comfortable methods.
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
