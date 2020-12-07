//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;

//class
public final class WidgetParent {
	
	//constants
	private static final String GUI_TYPE_HEADER = "GUI";
	private static final String WIDGET_TYPE_HEADER = "Widget";
	
	//optional attribute
	private final LayerGUI<?> mGui;
	
	//optional attribute
	private final Widget<?, ?> widget;
	
	//constructor
	public WidgetParent(final LayerGUI<?> pGUI, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(pGUI).thatIsNamed(GUI_TYPE_HEADER).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		mGui = pGUI;
		widget = null;
	}
	
	//constructor
	public WidgetParent(final Widget<?, ?> widget, final Widget<?, ?> childWidget) {
		
		Validator.assertThat(widget).thatIsNamed(WIDGET_TYPE_HEADER).isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNot(widget);
		
		mGui = null;
		this.widget = widget;
	}
	
	//method
	public boolean belongsToGUI() {
		return (mGui != null || widget.belongsToGUI());
	}
	
	//method
	public Element<?> getRef() {
		return (mGui != null ? mGui : widget);
	}
	
	//method
	public LayerGUI<?> getRefGUI() {
		
		if (mGui != null) {
			return mGui;
		}
		
		return widget.getParentGUI();
	}
	
	//method
	public Widget<?, ?> getRefWidget() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		if (widget == null) {
			throw new InvalidArgumentException(this, "is not a Widget");
		}
		
		return widget;
	}
	
	//method
	public String getType() {
		return mGui != null ? GUI_TYPE_HEADER : WIDGET_TYPE_HEADER;
	}
	
	//method
	public String getTypeInQuotes() {
		return ("'" + getType() + "'");
	}
	
	//method
	public int getXPositionOnGUI() {
		return (mGui != null ? 0 : widget.getXPositionOnGUI());
	}
	
	//method
	public int getYPositionOnGUI() {
		return (mGui != null ? 0 : widget.getYPositionOnGUI());
	}
	
	//method
	public boolean GUIIsClosed() {
		return (mGui != null && mGui.isClosed());
	}
	
	//method
	public boolean is(final LayerGUI<?> pGUI) {
		
		Validator.assertThat(pGUI).thatIsNamed(GUI_TYPE_HEADER).isNotNull();
		
		return (mGui == pGUI);
	}
	
	//method
	public boolean is(final Widget<?, ?> widget) {
		
		Validator.assertThat(widget).thatIsNamed(WIDGET_TYPE_HEADER).isNotNull();
		
		return (this.widget == widget);
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
