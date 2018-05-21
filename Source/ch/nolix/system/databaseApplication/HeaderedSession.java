//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;
import ch.nolix.primitive.validator2.Validator;

//abstract class
public abstract class HeaderedSession extends UserSession {

	//attribute
	private final String header;
	
	//constructor
	public HeaderedSession(
		final DatabaseApplicationContext databaseApplicationContext,
		final String header
	) {
		super(databaseApplicationContext);
		
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
