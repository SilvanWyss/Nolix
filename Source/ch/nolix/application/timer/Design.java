//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 50
 */
final class Design extends StandardConfiguration {

	//constructor
	/**
	 * Creates a new design.
	 * The design will be frozen.
	 */
	public Design() {
		
		addAttachingAttribute(
			"BackgroundColor(DarkBlue)",
			"ContentPosition(Top)"
		);
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute(
				"BaseTopPadding(50)",
				"ContentPosition(Center)",
				"ElementMargin(50)"
			),
			new StandardConfiguration()
			.setSelectorName(WidgetNameManager.TIME_LABEL_NAME)
			.addAttachingAttribute(
				"BaseTextSize(60)",
				"BaseTextColor(White)"
			),	
			new StandardConfiguration()
			.setSelectorType(Button.TYPE_NAME)
			.addAttachingAttribute(
				"CursorIcon(Hand)",
				"BaseTextSize(30)",
				"HoverTextColor(White)"
			)
		);
		
		freeze();
	}
}
