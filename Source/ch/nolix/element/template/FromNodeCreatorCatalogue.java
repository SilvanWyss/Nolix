//package declaration
package ch.nolix.element.template;

import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.valuecreator.SpecificValueCreator;
import ch.nolix.element.gui.image.Image;

//class
public final class FromNodeCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, Image> IMAGE_CREATOR =
	new SpecificValueCreator<>(Image.class, Image::fromSpecification);
	
	//constructor
	private FromNodeCreatorCatalogue() {}
}
