//package declaration
package ch.nolix.template.GUILook;

//own import
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;

//class
final class AnthrazitDialogLook extends DeepConfiguration {
	
	public AnthrazitDialogLook() {
		
		addSelectorRole(ContainerRole.DialogContainer);
		
		addAttachingAttribute("MinWidth(800)", "MinHeight(600)", "BaseBackgroundColor(Yellow)");
		
		freeze();
	}
}
