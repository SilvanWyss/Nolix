//package declaration
package ch.nolix.techAPI.projectAPI;

import ch.nolix.common.attributeAPI.Namable;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.skillAPI.Resettable;
import ch.nolix.element.baseAPI.IElement;

//interface
public interface IProject extends Clearable<IProject>, IElement, Namable<IProject>, Resettable<IProject> {
	
	//abstract method
	public abstract IProject addTask(ITask task);
	
	//default method
	public default IProject addTask(final ITask... tasks) {
		
		for (final var t : tasks) {
			addTask(t);
		}
		
		return this;
	}
	
	//abstract method
	public abstract IContainer<ITask> getRefTasks();
	
	//abstract method
	public abstract IProject removeTask(ITask task);
}
