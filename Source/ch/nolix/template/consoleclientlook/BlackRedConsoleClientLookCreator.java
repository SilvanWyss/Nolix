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
 * @date 2017-10-01
 * @lines 50
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
