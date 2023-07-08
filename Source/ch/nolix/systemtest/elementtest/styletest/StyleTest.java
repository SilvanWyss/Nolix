//package declaration
package ch.nolix.systemtest.elementtest.styletest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class StyleTest extends Test {
	
	//method
	@TestCase
	public void testCase_styleElement_whenDoesNotSelectElement() {
		
		//setup part 1: create WebGui
		final var webGui =
		new WebGui()
		.setBackgroundColor(Color.WHITE);
		
		// setup part 2: creates testUnit
		final var testUnit =
		new Style()
		.setSelectorType(Button.class)
		.addAttachingAttribute(
			"MinWidth(200)",
			"Background(Color(Blue))"
		);
		
		//execution
		testUnit.styleElement(webGui);
		
		//verification
		expect(webGui.getBackgroundColor()).isEqualTo(Color.WHITE);
	}
	
	//method
	@TestCase
	public void testCase_styleElement_whenSelectsElement() {
		
		//setup part 1: create WebGui
		final var webGui = new WebGui();
		
		// setup part 2: create testUnit
		final var testUnit =
		new Style()
		.addAttachingAttribute(
			"Title(my_title)",
			"Background(Color(Blue))"
		);
		
		//execution
		testUnit.styleElement(webGui);
		
		//verification
		expect(webGui.getTitle()).isEqualTo("my_title");
		expect(webGui.getBackgroundColor()).isEqualTo(Color.BLUE);
	}
}
