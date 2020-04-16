//package declaration
package ch.nolix.element.templates;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.valueCreator.SpecificValueCreator;
import ch.nolix.element.image.Image;

//class
public final class FromNodeSpecificValueCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, Image> IMAGE_CREATOR =
	new SpecificValueCreator<>(Image.class, s -> Image.fromSpecification(s));
	
	//visibility-reducing constructor
	private FromNodeSpecificValueCreatorCatalogue() {}
}
