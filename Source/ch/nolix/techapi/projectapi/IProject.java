//package declaration
package ch.nolix.techapi.projectapi;

import ch.nolix.common.container.IContainer;
import ch.nolix.common.mutableattributeapi.Namable;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.common.skillapi.Resettable;
import ch.nolix.element.elementAPI.IElement;

//interface
public interface IProject extends Clearable<IProject>, IElement, Namable<IProject>, Resettable<IProject> {
	
	//method declaration
	IProject addTask(ITask task);
	
	//method
	default IProject addTask(final ITask... tasks) {
		
		for (final var t : tasks) {
			addTask(t);
		}
		
		return this;
	}
	
	//method declaration
	IContainer<ITask> getRefTasks();
	
	//method declaration
	IProject removeTask(ITask task);
}
