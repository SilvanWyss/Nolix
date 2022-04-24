//package declaration
package ch.nolix.templatetest.guilooktest;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.ObjectTest;
import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.gui.base.InvisibleGUI;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.textbox.TextBox;
import ch.nolix.system.gui.widget.Area;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Console;
import ch.nolix.system.gui.widget.Downloader;
import ch.nolix.system.gui.widget.HorizontalLine;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.SelectionMenu;
import ch.nolix.system.gui.widget.Uploader;
import ch.nolix.system.gui.widget.VerticalLine;
import ch.nolix.system.gui.widget.Widget;

//class
public abstract class GUILookCreatorTest extends ObjectTest<Configuration> {
	
	//method
	@TestCase
	public void testCase_configure_whenTheGUIContainsWidgets() {
		
		//setup
		@SuppressWarnings("resource")
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
