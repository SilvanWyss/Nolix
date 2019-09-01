//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.LabelRole;
import ch.nolix.element.widgets.VerticalStack;

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
