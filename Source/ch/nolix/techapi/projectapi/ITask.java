//package declaration
package ch.nolix.techapi.projectapi;

import ch.nolix.common.skillapi.Resettable;
import ch.nolix.element.elementAPI.IElement;
import ch.nolix.element.time.Time;

//interface
public interface ITask extends IElement, Resettable<ITask> {
	
	//method declaration
	Time getCreationDate();
	
	//method declaration
	TaskSize getSize();
	
	//method declaration
	Time getSolveDate();
	
	//method declaration
	String getTitle();
	
	//method declaration
	boolean hasSize();
	
	//method declaration
	boolean isSolved();
	
	//method declaration
	ITask setSize(TaskSize size);
	
	//method declaration
	ITask setSolved();
	
	//method declaration
	ITask setSolved(final Time solveTime);
	
	//method declaration
	ITask setUnsolved();
	
	//method declaration
	ITask setTitle(String title);
}
