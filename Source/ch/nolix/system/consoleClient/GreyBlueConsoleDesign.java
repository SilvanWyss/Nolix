//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public final class GreyBlueConsoleDesign extends StandardConfiguration {

	//constructor
	/**
	 * Creates new console client.
	 */
	public GreyBlueConsoleDesign() {
		
		addAttachingAttribute("BackgroundColor(LightSteelBlue)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"NormalBackgroundColor(White)",
				"NormalLeftPadding(20)",
				"NormalRightPadding(20)",
				"NormalBottomPadding(20)",
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(300)",
				"NormalBackgroundColor(Lavender)",
				"NormalPadding(10)",
				"NormalTextColor(DarkBlue)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.CONSOLE_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(400)",
				"NormalBackgroundColor(WhiteSmoke)",
				"NormalPadding(10)",
				"NormalTextColor(DarkBlue)"		
			)
		);
	}
}
