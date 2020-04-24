//package declaration
package ch.nolix.templateTest.GUILookTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.GUI.InvisibleLayerGUI;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.widgets.Area;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Console;
import ch.nolix.element.widgets.Downloader;
import ch.nolix.element.widgets.HorizontalLine;
import ch.nolix.element.widgets.ImageWidget;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.SelectionMenu;
import ch.nolix.element.widgets.TextBox;
import ch.nolix.element.widgets.Uploader;
import ch.nolix.element.widgets.VerticalStack;

//class
public abstract class GUILookTest<GL extends StandardConfiguration> extends ObjectTest<GL> {
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIContainsWidgets() {
		
		//setup
		final var GUI =
		new InvisibleLayerGUI()
		.addLayerOnTop(new VerticalStack(createNonContainerWidgets()));
		
		//execution & verification
		expect(() -> createTestObject().configure(GUI)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIIsEmpty() {
		
		//setup
		final var GUI = new InvisibleLayerGUI();
		
		//execution & verification
		expect(() -> createTestObject().configure(GUI)).doesNotThrowException();
	}
	
	//method
	private LinkedList<Widget<?, ?>> createNonContainerWidgets() {
		return
		new LinkedList<>(
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
			new VerticalStack()
		);
	}
}
