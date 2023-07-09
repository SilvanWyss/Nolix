//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class StyleTest extends Test {
	
	//method
	@TestCase
	public void testCase_styleElement() {
		
		//setup part 1: create WebGui
		final var webGui = new WebGui();
		
		// setup part 2: create testUnit
		final var testUnit =
		new Style(
			ImmutableList.withElements(
				Node.fromString("Title(my_title)"),
				Node.fromString("Background(Color(Blue))")
			),
			new ImmutableList<>()
		);
		
		//execution
		testUnit.styleElement(webGui);
		
		//verification
		expect(webGui.getTitle()).isEqualTo("my_title");
		expect(webGui.getBackgroundColor()).isEqualTo(Color.BLUE);
	}
}
