//package declaration
package ch.nolix.element.template;

//own imports
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.valueCreator.SpecificValueCreator;
import ch.nolix.element.graphic.Image;

//class
public final class FromNodeCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, Image> IMAGE_CREATOR =
	new SpecificValueCreator<>(Image.class, Image::fromSpecification);
	
	//visibility-reducing constructor
	private FromNodeCreatorCatalogue() {}
}
