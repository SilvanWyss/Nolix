//package declaration
package ch.nolix.element.GUI_API;

//own imports
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.input.Key;

//interface
interface ILayer<GL extends ILayer<GL>> extends Clearable<GL>, IMutableElement<GL> {
	
	//abstract methods
	public abstract CursorIcon getCursorIcon();
	
	//abstract method
	public abstract Widget<?, ?> getRefRootWidget();
	
	//abstract method
	public abstract void noteKeyPress(Key key);
	
	//abstract method
	public abstract void noteKeyTyping(Key key);
	
	//abstract method
	public abstract void noteLeftMouseButtonPress();
	
	//abstract method
	public abstract void noteLeftMouseButtonRelease();
	
	//abstract method
	public abstract void noteRightMouseButtonPress();
	
	//abstract method
	public abstract void noteRightMouseButtonRelease();
	
	//abstract method
	public abstract GL setRootWidget(Widget<?, ?> rootWidget);
	
	//abstract method
	public abstract GL setParentGUI(ILayerGUI<?> parentGUI);
}
