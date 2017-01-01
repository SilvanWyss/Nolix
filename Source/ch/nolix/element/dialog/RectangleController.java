/*
 * file:	RectangleControlleroid.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	10
 */

//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.specification.Statement;

//class
final class RectangleController implements ILevel1Controller {
	
	private final Rectangle<?, ?> rectangle;
	
	public RectangleController(Rectangle<?, ?> rectangle) {
		this.rectangle = rectangle;
	}

	public final void run(Statement command) {
		if (command.hasHeader()) {
			switch (command.getHeader()) {
				case Dialog.SIMPLE_CLASS_NAME:
					getRefRectangle().getRefDialog().run(command.getNextStatement());
					return;
				case Rectangle.SIMPLE_CLASS_NAME:
					getRefRectangle().run(command.getNextStatement());
					return;
			}
		}
		getRefRectangle().getRefDialog().getRefController().run(command);
	}
	
	private Rectangle<?, ?> getRefRectangle() {
		return rectangle;
	}
}
