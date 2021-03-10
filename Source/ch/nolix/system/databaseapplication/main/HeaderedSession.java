//package declaration
package ch.nolix.system.databaseapplication.main;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;

//class
public abstract class HeaderedSession extends UserSession {

	//attribute
	private final String header;
	
	//constructor
	public HeaderedSession(final String header) {
		
		Validator
		.assertThat(header)
		.thatIsNamed(LowerCaseCatalogue.HEADER)
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
		return new VerticalStack()
				.addWidget(
			createHeaderWidget(),
			createSubSubContentWidget()
		);
	}

	//method declaration
	protected abstract Widget<?, ?> createSubSubContentWidget();

	//method
	private Label createHeaderWidget() {
		return
		new Label()
		.setRole(LabelRole.LEVEL1_HEADER)
		.setText(getHeader());
	}
}
