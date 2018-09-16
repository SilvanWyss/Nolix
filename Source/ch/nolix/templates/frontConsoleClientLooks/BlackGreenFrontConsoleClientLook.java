//package declaration
package ch.nolix.templates.frontConsoleClientLooks;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.consoleClient.WidgetNameCatalogue;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class BlackGreenFrontConsoleClientLook extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new green console design.
	 */
	public BlackGreenFrontConsoleClientLook() {
		
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
			.setSelectorName(WidgetNameCatalogue.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"BaseWidth(1000)",
				"BaseHeight(300)",
				"BaseBackgroundColorGradient(Vertical,0x001000,0x002000)",	
				"BasePadding(5)",
				"BaseTextColor(Lime)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameCatalogue.CONSOLE_NAME)
			.addAttachingAttribute(
				"BaseWidth(1000)",
				"BaseHeight(400)",				
				"BaseBackgroundColorGradient(Vertical,0x204020,0x406040)",
				"BasePadding(5)",
				"BaseTextColor(Lime)"		
			)
		);
	}
}
