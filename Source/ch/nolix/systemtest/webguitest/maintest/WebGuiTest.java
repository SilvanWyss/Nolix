//package declaration
package ch.nolix.systemtest.webguitest.maintest;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.system.webgui.main.WebGui;

//class
public final class WebGuiTest extends Test {
	
	//method
	@TestCase
	public void testCase_constructor() {
		
		//execution
		final var result = new WebGui();
		
		//verification
		expect(result.getTitle()).isEqualTo(WebGui.DEFAULT_TITLE);
		expect(result.getIcon()).isEqualTo(WebGui.DEFAULT_ICON);
		expect(result.getBackgroundColor()).isEqualTo(WebGui.DEFAULT_BACKGROUND_COLOR);
		expect(result.isEmpty());
		expect(result.getTokens()).isEmpty();
	}
}
