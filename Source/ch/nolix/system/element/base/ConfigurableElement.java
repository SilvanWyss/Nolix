//package declaration
package ch.nolix.system.element.base;

//own imports
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;

//class
public abstract class ConfigurableElement<CE extends ConfigurableElement<CE>>
extends OptionalIdentifiableOptionalTokenableElement<CE>
implements IConfigurableElement<CE> {
	
	//method
	@Override
	public final void resetConfigurationRecursively() {
		
		resetConfiguration();
		
		getRefChildConfigurableElements().forEach(IConfigurableElement::resetConfigurationRecursively);
	}
	
	//method declaration
	protected abstract void resetConfiguration();
}
