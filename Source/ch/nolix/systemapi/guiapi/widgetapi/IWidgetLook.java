//package declaration
package ch.nolix.systemapi.guiapi.widgetapi;

//interface
public interface IWidgetLook<WL extends IWidgetLook<WL>> {
	
	//method declaration
	<WL2 extends IWidgetLook<WL2>> void addChild(final WL2 widgetLook);
}
