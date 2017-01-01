//package declaration
package ch.nolix.application.performanceDetector;

//own imports
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
	 */
	public Design() {
		
		addAttachingAttribute("ContentOrientation(Top)");
		
		addConfiguration(
			new StandardConfiguration()
			.addAttachingAttribute("ContentOrientation(Top)"),
			new DeepConfiguration()
			.setSelectorName("BenchmarkLabel")
			.addAttachingAttribute("NormalTextSize(100)"),
			new DeepConfiguration()
			.setSelectorName("BenchmarkInfoLabel")
			.addAttachingAttribute(
				"NormalTextSize(30)",
				"NormalTextColor(Grey)"
			)
		);
	}
}
