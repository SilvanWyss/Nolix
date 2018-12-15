//package declaration
package ch.nolix.system;

//own imports
import ch.nolix.system.resource.Resource;
import ch.nolix.systemAPI.ISystemFactory;
import ch.nolix.systemAPI.resourceAPI.IResource;

//class
public final class SystemFactory implements ISystemFactory {
	
	//method
	@Override
	public IResource createResource(final String name) {
		return new Resource(name);
	}
	
	//method
	@Override
	public IResource createResource(final String name, final IResource... baseResources) {
		return new Resource(name, baseResources);
	}
}
