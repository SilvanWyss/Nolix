//package declaration
package ch.nolix.templatetest.guilooktest;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.InvisibleGUI;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.widget.Area;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.Console;
import ch.nolix.element.widget.Downloader;
import ch.nolix.element.widget.HorizontalLine;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.SelectionMenu;
import ch.nolix.element.widget.TextBox;
import ch.nolix.element.widget.Uploader;
import ch.nolix.element.widget.VerticalLine;
import ch.nolix.element.widget.VerticalStack;

//class
public abstract class GUILookTest<GL extends Configuration> extends ObjectTest<GL> {
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIContainsWidgets() {
		
		//setup
		final var lGUI =
		new InvisibleGUI()
		.addLayerOnTop(
			new VerticalStack()
			.addWidgets(createNonContainerWidgets())
		);
		
		//execution & verification
		expect(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIIsEmpty() {
		
		//setup
		final var lGUI = new InvisibleGUI();
		
		//execution & verification
		expect(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
	}
	
	//method
	private LinkedList<Widget<?, ?>> createNonContainerWidgets() {
		return
		LinkedList.withElements(
			new Area(),
			new Button(),
			new Console(),
			new Downloader(),
			new HorizontalLine(),
			new ImageWidget(),
			new Label(),
			new SelectionMenu(),
			new Uploader(),
			new TextBox(),
			new VerticalLine()
		);
	}
}
