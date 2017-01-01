//package declaration
package ch.nolix.application.timer;

//own imports
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 */
public final class Design extends StandardConfiguration {

	//constructor
	/**
	 * Creates new design.
	 */
	public Design() {
		
		addAttachingAttribute(
			"BackgroundColor(DarkBlue)",
			"ContentOrientation(Top)"
		);
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorType("VerticalStack")
			.setSelectorRole("MainContainer")
			.addAttachingAttribute(
				"TopPadding(50)",
				"ContentOrientation(Center)",
				"ElementMargin(50)"
			)
			.addConfiguration(
				new StandardConfiguration()
				.setSelectorType("Label")
				.addAttachingAttribute(
					"NormalTextSize(60)",
					"NormalTextColor(White)"
				),
				new StandardConfiguration()
				.setSelectorType("HorizontalStack")
				.addAttachingAttribute(
					"NormalBackgroundColor(Blue)",
					"LeftPadding(50)",
					"RightPadding(50)",
					"TopPadding(10)",
					"BottomPadding(10)",
					"ElementMargin(100)"
				)
				.addConfiguration(
					new StandardConfiguration()
					.setSelectorType("Button")
					.addAttachingAttribute(
						"CursorIcon(Hand)",
						"NormalTextSize(30)",
						"HoverTextColor(White)"
					)
				)
			)
		);
		
		freeze();
	}
}
