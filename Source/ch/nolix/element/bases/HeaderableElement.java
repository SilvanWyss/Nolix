//package declaration
package ch.nolix.element.bases;

//own imports
import ch.nolix.core.constants.PascalCaseNameCatalogue;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.skillAPI.Headerable;
import ch.nolix.element.core.MutableElement;
import ch.nolix.element.core.NonEmptyText;

//abstract class
public abstract class HeaderableElement<HE extends HeaderableElement<HE>>
extends MutableElement<HE>
implements Headerable<HE> {
	
	//attribute
	private final MutableProperty<NonEmptyText> headerProperty =
	new MutableProperty<NonEmptyText>(
		PascalCaseNameCatalogue.HEADER,
		h -> setHeader(h.getValue()),
		s -> NonEmptyText.createFromSpecification(s),
		h -> h.getSpecification()
	);
	
	//method
	public final String getHeader() {
		return headerProperty.getValue().getValue();
	}

	//method
	public final HE setHeader(final String header) {
		
		headerProperty.setValue(new NonEmptyText(header));
		
		return asConcreteType();
	}	
}
