//package declaration
package ch.nolix.templates.frontConsoleClientLooks;

import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.system.consoleClient.WidgetNameCatalogue;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public final class GreyBlueFrontConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public GreyBlueFrontConsoleClientLook() {
		
		addAttachingAttribute("BackgroundColor(LightSteelBlue)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
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
