//package declaration
package ch.nolix.templates.consoleClientLooks;

//own imports
import ch.nolix.element.GUI.WidgetIdCatalogue;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidgets.ContainerRole;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class BlackRedConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new console client.
	 */
	public BlackRedConsoleClientLook() {
		
		addAttachingAttribute("BackgroundColor(Black)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.INFO_PANEL)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(300)",
				"BaseBackgroundColor(0x202020)",
				"BasePadding(10)",
				"BaseTextColor(OrangeRed)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.CONSOLE)
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
