//package declaration
package ch.nolix.templates.frontConsoleClientLooks;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.consoleClient.WidgetNameCatalogue;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class BlackRedFrontConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public BlackRedFrontConsoleClientLook() {
		
		addAttachingAttribute("BackgroundColor(Black)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(300)",
				"NormalBackgroundColor(0x202020)",
				"NormalPadding(10)",
				"NormalTextColor(OrangeRed)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.CONSOLE_NAME)
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
