//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 50
 */
public final class GreenConsoleDesign extends StandardConfiguration {

	//constructor
	/**
	 * Creates new green console design.
	 */
	public GreenConsoleDesign() {
		
		addAttachingAttribute("BackgroundColor(0x080F08)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"NormalBackgroundColorGradient(Vertical,0x40C040,0x102010)",	
				"NormalLeftPadding(30)",
				"NormalRightPadding(30)",
				"NormalTopPadding(15)",
				"NormalBottomPadding(15)",
				"ElementMargin(15)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.INFO_PANEL_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(300)",
				"NormalBackgroundColorGradient(Vertical,0x001000,0x002000)",	
				"NormalPadding(5)",
				"NormalTextColor(Lime)"
			),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.CONSOLE_NAME)
			.addAttachingAttribute(
				"NormalWidth(1000)",
				"NormalHeight(400)",				
				"NormalBackgroundColorGradient(Vertical,0x204020,0x406040)",
				"NormalPadding(5)",
				"NormalTextColor(Lime)"		
			)
		);
	}
}
