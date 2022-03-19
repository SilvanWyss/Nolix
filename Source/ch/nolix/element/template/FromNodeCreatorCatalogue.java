//package declaration
package ch.nolix.element.template;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.valuecreator.SpecificValueCreator;
import ch.nolix.element.gui.image.MutableImage;

//class
public final class FromNodeCreatorCatalogue {
	
	//constant
	public static final SpecificValueCreator<BaseNode, MutableImage> IMAGE_CREATOR =
	new SpecificValueCreator<>(MutableImage.class, MutableImage::fromSpecification);
	
	//constructor
	private FromNodeCreatorCatalogue() {}
}
