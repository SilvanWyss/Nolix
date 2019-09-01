//package declaration
package ch.nolix.techAPI.projectAPI;

import ch.nolix.common.skillAPI.Resettable;
import ch.nolix.element.baseAPI.IElement;
import ch.nolix.element.time.Time;

//interface
public interface ITask extends IElement, Resettable<ITask> {
	
	//abstract method
	public abstract Time getCreationDate();
	
	//abstract method
	public abstract TaskSize getSize();
	
	//abstract method
	public abstract Time getSolveDate();
	
	//abstract method
	public abstract String getTitle();
	
	//abstract method
	public abstract boolean hasSize();
	
	//abstract method
	public abstract boolean isSolved();
	
	//abstract method
	public abstract ITask setSize(TaskSize size);
	
	//abstract method
	public abstract ITask setSolved();
	
	//abstract method
	public abstract ITask setSolved(final Time solveTime);
	
	//abstract method
	public abstract ITask setUnsolved();
	
	//abstract method
	public abstract ITask setTitle(String title);
}
