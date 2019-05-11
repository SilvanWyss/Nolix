//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.element.widget.Widget;

//abstract class
public abstract class HeaderedSession extends UserSession {

	//attribute
	private final String header;
	
	//constructor
	public HeaderedSession(final String header) {
		
		Validator
		.suppose(header)
		.thatIsNamed(VariableNameCatalogue.HEADER)
		.isNotEmpty();
		
		this.header = header;
	}
	
	//method
	public final String getHeader() {
		return header;
	}

	//method
	@Override
	protected final VerticalStack createSubContentWidget() {
		return new VerticalStack(
			createHeaderWidget(),
			createSubSubContentWidget()
		);
	}

	//abstract method
	protected abstract Widget<?, ?> createSubSubContentWidget();

	//method
	private Label createHeaderWidget() {
		return
		new Label(getHeader())
		.setRole(LabelRole.Level1Header);
	}
}
