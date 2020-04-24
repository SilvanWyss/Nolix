//package declaration
package ch.nolix.template.consoleClientLook;

//own imports
import ch.nolix.element.GUI.WidgetIdCatalogue;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidgets.ContainerRole;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 50
 */
public final class GreyBlueConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public GreyBlueConsoleClientLook() {
		
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
