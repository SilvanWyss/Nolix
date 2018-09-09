//package declaration
package ch.nolix.tech;

//own imports
import ch.nolix.tech.resource.Resource;
import ch.nolix.techAPI.ITechFactory;
import ch.nolix.techAPI.resourceAPI.IResource;

//class
public final class TechFactory implements ITechFactory {
	
	//method
	public IResource createResource(final String name) {
		return new Resource(name);
	}

	//method
	public IResource createResource(final String name, final IResource... baseResources) {
		return new Resource(name, baseResources);
	}
}
