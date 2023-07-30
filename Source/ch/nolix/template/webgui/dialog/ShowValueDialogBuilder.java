//package declaration
package ch.nolix.template.webgui.dialog;

//own imports
import ch.nolix.core.commontype.commontypeconstant.StringCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.coreapi.functionapi.skillapi.IBuilder;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.basecontainerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class ShowValueDialogBuilder implements IBuilder<ILayer<?>> {
	
	//constant
	private static final String DEFAULT_VALUE_NAME = LowerCaseCatalogue.VALUE;
	
	//constant
	private static final String DEFAULT_CONFIRM_BUTTON_TEXT = StringCatalogue.LONG_LEFT_ARROW;
	
	//attribute
	private String valueName = DEFAULT_VALUE_NAME;
	
	//attribute
	private String value = StringCatalogue.ZERO;
	
	//attribute
	private String confirmButtonText = DEFAULT_CONFIRM_BUTTON_TEXT;
	
	//optional attribute
	private IElementTaker<String> valueCopier;
	
	//method
	@Override
	public ILayer<?> build() {
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(getValueName() + ":"),
				new HorizontalStack()
				.addControl(
					new Label()
					.setText(getValue()),
					new Button()
					.setVisibility(hasValueCopier())
					.setText("Copy")
					.setLeftMouseButtonPressAction(() -> getValueCopier().run(value))
				),
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText(getConfirmButtonText())
				.setLeftMouseButtonPressAction(b -> b.getStoredParentLayer().removeSelfFromGui())
			)
		);
	}
	
	//method
	public ShowValueDialogBuilder setConfirmButtonText(final String confirmButtonText) {
		
		this.confirmButtonText = confirmButtonText;
		
		return this;
	}
	
	//method
	public ShowValueDialogBuilder setValue(final String value) {
		
		this.value = value;
		
		return this;
	}
	
	//method
	public ShowValueDialogBuilder setValueCopier(final IElementTaker<String> valueCopier) {
		
		this.valueCopier = valueCopier;
		
		return this;
	}
	
	//method
	public ShowValueDialogBuilder setValueName(final String valueName) {
		
		this.valueName = valueName;
		
		return this;
	}
	
	//method
	private void assertHasValueCopier() {
		if (!hasValueCopier()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "value copier");
		}
	}
	
	//method
	private String getConfirmButtonText() {
		return confirmButtonText;
	}
	
	//method
	private String getValue() {
		return value;
	}
	
	//method
	private IElementTaker<String> getValueCopier() {
		
		assertHasValueCopier();
		
		return valueCopier;
	}
	
	//method
	private String getValueName() {
		return valueName;
	}
	
	//method
	private boolean hasValueCopier() {
		return (valueCopier != null);
	}
}