//package declaration
package ch.nolix.templates.frontConsoleClientLooks;

import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
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
				"ProposalWidth(1000)",
				"ProposalHeight(300)",
				"BaseBackgroundColor(0x202020)",
				"BasePadding(10)",
				"BaseTextColor(OrangeRed)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.CONSOLE_NAME)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(400)",
				"BaseBackgroundColor(0x101010)",
				"BasePadding(10)",
				"BaseTextColor(Red)"
			)
		);
	}
}
