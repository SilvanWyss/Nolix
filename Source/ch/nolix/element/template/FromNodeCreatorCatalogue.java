//package declaration
package ch.nolix.element.template;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.valuecreator.SpecificValueCreator;
import ch.nolix.element.graphic.Image;

//class
public final class FromNodeCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, Image> IMAGE_CREATOR =
	new SpecificValueCreator<>(Image.class, Image::fromSpecification);
	
	//visibility-reduced constructor
	private FromNodeCreatorCatalogue() {}
}
