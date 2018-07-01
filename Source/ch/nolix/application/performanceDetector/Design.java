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
	 * Creates a new design.
	 * The design will be frozen.
	 */
	public Design() {
		
		addAttachingAttribute("ContentPosition(Top)");
		
		addConfiguration(
			new DeepConfiguration()
			.addSelectorRole(ContainerRole.MainContainer)
			.addAttachingAttribute("ContentPosition(Top)"),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.BENCHMARK_LABEL_NAME)
			.addAttachingAttribute("NormalTextSize(100)"),
			new DeepConfiguration()
			.setSelectorName(WidgetNameManager.BENCHMARK_INFO_LABEL_NAME)
			.addAttachingAttribute(
				"NormalTextSize(30)",
				"NormalTextColor(Grey)"
			)
		);
		
		freeze();
	}
}
