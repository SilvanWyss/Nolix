//package declaration
package ch.nolix.systemapi.guiapi.widgetguiapi;

//interface
public interface IWidgetStyle<WL extends IWidgetStyle<WL>> {
	
	//method declaration
	<WL2 extends IWidgetStyle<WL2>> void addChild(final WL2 widgetLook);
}
