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
	 * Creates a new console client.
	 */
	public GreyBlueConsoleDesign() {
		
		addAttachingAttribute("BackgroundColor(LightSteelBlue)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"BaseBackgroundColor(White)",
				"BaseLeftPadding(20)",
				"BaseRightPadding(20)",
				"BaseBottomPadding(20)",
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(200)",
				"BaseBackgroundColor(Lavender)",
				"BasePadding(10)",
				"BaseTextColor(DarkBlue)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.CONSOLE_NAME)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(300)",
				"BaseBackgroundColor(WhiteSmoke)",
				"BasePadding(10)",
				"BaseTextColor(DarkBlue)"		
			)
		);
	}
}
