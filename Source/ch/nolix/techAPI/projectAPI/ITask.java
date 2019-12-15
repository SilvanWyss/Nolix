//package declaration
package ch.nolix.techAPI.projectAPI;

import ch.nolix.common.skillAPI.Resettable;
import ch.nolix.element.baseAPI.IElement;
import ch.nolix.element.time.Time;

//interface
public interface ITask extends IElement, Resettable<ITask> {
	
	//method declaration
	public abstract Time getCreationDate();
	
	//method declaration
	public abstract TaskSize getSize();
	
	//method declaration
	public abstract Time getSolveDate();
	
	//method declaration
	public abstract String getTitle();
	
	//method declaration
	public abstract boolean hasSize();
	
	//method declaration
	public abstract boolean isSolved();
	
	//method declaration
	public abstract ITask setSize(TaskSize size);
	
	//method declaration
	public abstract ITask setSolved();
	
	//method declaration
	public abstract ITask setSolved(final Time solveTime);
	
	//method declaration
	public abstract ITask setUnsolved();
	
	//method declaration
	public abstract ITask setTitle(String title);
}
