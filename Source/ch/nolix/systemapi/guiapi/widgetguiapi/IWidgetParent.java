//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//interface
public interface IWidgetParent {
	
	//method declaration
	boolean belongsToGUI();
	
	//method declaration
	IWidgetGUI<?> getRefGUI();
	
	//method declaration
	ILayer<?> getRefLayer();
	
	//method declaration
	IWidget<?, ?> getRefWidget();
	
	//method declaration
	int getXPositionOnGUIViewArea();
	
	//method declaration
	int getYPositionOnGUIViewArea();
	
	//method declaration
	boolean GUIIsClosed();
	
	//method declaration
	boolean isLayer();
	
	//method declaration
	boolean isWidget();
}
