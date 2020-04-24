//package declaration
package ch.nolix.template.consoleClientLook;

//own imports
import ch.nolix.element.GUI.WidgetIdCatalogue;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class BlackGreenConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new green console design.
	 */
	public BlackGreenConsoleClientLook() {
		
		addAttachingAttribute("BackgroundColor(0x080F08)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
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
