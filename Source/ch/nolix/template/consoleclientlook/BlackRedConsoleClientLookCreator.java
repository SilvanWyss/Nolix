//package declaration
package ch.nolix.template.consoleclientlook;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.base.WidgetIdCatalogue;
import ch.nolix.system.gui.containerwidget.ContainerRole;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-01
 */
public final class BlackRedConsoleClientLookCreator {
	
	//constructor
	/**
	 * @return a new {@link Configuration}.
	 */
	public Configuration createClientLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(Black)")
		.addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MAINT_CONTAINER)
			.addAttachingAttribute(
				"ElementMargin(20)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.INFO_PANEL)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(300)",
				"BaseBackground(Color(0x202020))",
				"BasePadding(10)",
				"BaseTextColor(OrangeRed)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.CONSOLE)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(400)",
				"BaseBackground(Color(0x101010))",
				"BasePadding(10)",
				"BaseTextColor(Red)"
			)
		);
	}
}
