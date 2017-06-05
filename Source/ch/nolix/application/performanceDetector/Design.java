//package declaration
package ch.nolix.application.performanceDetector;

//own imports
import ch.nolix.element.GUI.ContainerRole;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 40
 */
final class Design extends StandardConfiguration {
	
	//constructor
	/**
	 * Creates new design.
	 * The design will be frozen.
	 */
	public Design() {
		
		addAttachingAttribute("ContentOrientation(Top)");
		
		addConfiguration(
			new DeepConfiguration()
			.setSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute("ContentOrientation(Top)"),
			new DeepConfiguration()
			.setSelectorName(WidgetNames.BENCHMARK_LABEL_NAME)
			.addAttachingAttribute("NormalTextSize(100)"),
			new DeepConfiguration()
			.setSelectorName(WidgetNames.BENCHMARK_INFO_LABEL_NAME)
			.addAttachingAttribute(
				"NormalTextSize(30)",
				"NormalTextColor(Grey)"
			)
		);
		
		freeze();
	}
}
