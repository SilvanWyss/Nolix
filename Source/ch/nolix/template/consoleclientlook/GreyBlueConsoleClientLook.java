//package declaration
package ch.nolix.template.consoleclientlook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.WidgetIdCatalogue;
import ch.nolix.element.configuration.Configuration;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public final class GreyBlueConsoleClientLook extends Configuration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public GreyBlueConsoleClientLook() {
		
		addAttachingAttribute("BackgroundColor(LightSteelBlue)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MAINT_CONTAINER)
			.addAttachingAttribute(
				"BaseBackgroundColor(White)",
				"BaseLeftPadding(20)",
				"BaseRightPadding(20)",
				"BaseBottomPadding(20)",
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.INFO_PANEL)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(200)",
				"BaseBackgroundColor(Lavender)",
				"BasePadding(10)",
				"BaseTextColor(DarkBlue)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.CONSOLE)
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
