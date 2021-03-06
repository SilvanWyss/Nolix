//package declaration
package ch.nolix.template.consoleclientlook;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.gui.base.WidgetIdCatalogue;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.configuration.Configuration;

//class
/**
 * @author Silvan Wyss
 * @date 2017-10-01
 * @lines 50
 */
public final class BlackGreenConsoleClientLookCreator {
	
	//method
	/**
	 * @return a new {@link Configuration}.
	 */
	public Configuration createClientLook() {
		return
		new Configuration()
		.addAttachingAttribute("BackgroundColor(0x080F08)")
		.addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MAINT_CONTAINER)
			.addAttachingAttribute(
				"BaseBackgroundColorGradient(Vertical,0x40C040,0x102010)",	
				"BaseLeftPadding(30)",
				"BaseRightPadding(30)",
				"BaseTopPadding(15)",
				"BaseBottomPadding(15)",
				"ElementMargin(15)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.INFO_PANEL)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(300)",
				"BaseBackgroundColorGradient(Vertical,0x001000,0x002000)",	
				"BasePadding(5)",
				"BaseTextColor(Lime)"
			),
			new DeepConfiguration()
			.setSelectorId(WidgetIdCatalogue.CONSOLE)
			.addAttachingAttribute(
				"ProposalWidth(1000)",
				"ProposalHeight(400)",				
				"BaseBackgroundColorGradient(Vertical,0x204020,0x406040)",
				"BasePadding(5)",
				"BaseTextColor(Lime)"		
			)
		);
	}
}
