//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class BlackRedConsoleDesign extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public BlackRedConsoleDesign() {
		
		addAttachingAttribute("BackgroundColor(Black)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(300)",
				"NormalBackgroundColor(0x202020)",
				"NormalPadding(10)",
				"NormalTextColor(OrangeRed)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.CONSOLE_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(400)",
				"NormalBackgroundColor(0x101010)",
				"NormalPadding(10)",
				"NormalTextColor(Red)"
			)
		);
	}
}
