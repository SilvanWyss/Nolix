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
 * @date 2017-09-01
 * @lines 50
 */
public final class GreyBlueConsoleClientLookCreator {

	//constructor
	/**
	 * @return a new {@link Configuration}.
	 */
	public Configuration createConsoleClientLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(LightSteelBlue)")
		.addConfiguration(
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
