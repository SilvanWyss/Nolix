/*
 * file:	RectangleControlleroid.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	10
 */

//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.specification.Statement;

//class
final class WidgetController implements ILevel1Controller {
	
	private final Widget<?, ?> rectangle;
	
	public WidgetController(Widget<?, ?> rectangle) {
		this.rectangle = rectangle;
	}

	public final void run(Statement command) {
		if (command.hasHeader()) {
			switch (command.getHeader()) {
				case GUI.SIMPLE_CLASS_NAME:
					getRefRectangle().getRefDialog().run(command.getNextStatement());
					return;
				case Widget.SIMPLE_CLASS_NAME:
					getRefRectangle().run(command.getNextStatement());
					return;
			}
		}
		getRefRectangle().getRefDialog().getRefController().run(command);
	}
	
	private Widget<?, ?> getRefRectangle() {
		return rectangle;
	}
}
