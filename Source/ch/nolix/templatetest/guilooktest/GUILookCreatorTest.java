//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.test.ObjectTest;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.base.InvisibleGUI;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Area;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.Console;
import ch.nolix.element.gui.widget.Downloader;
import ch.nolix.element.gui.widget.HorizontalLine;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.SelectionMenu;
import ch.nolix.element.gui.widget.TextBox;
import ch.nolix.element.gui.widget.Uploader;
import ch.nolix.element.gui.widget.VerticalLine;

//class
public abstract class GUILookCreatorTest extends ObjectTest<Configuration> {
	
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
		expectRunning(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
	}
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIIsEmpty() {
		
		//setup
		final var lGUI = new InvisibleGUI();
		
		//execution & verification
		expectRunning(() -> createTestUnit().configure(lGUI)).doesNotThrowException();
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
