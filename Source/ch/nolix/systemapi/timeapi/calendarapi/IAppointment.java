//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITitleHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IAppointment<A extends IAppointment<A>> extends ITitleHolder {

  //method declaration
  ITime getEndTime();

  //method declaration
  ITime getStartTime();
}
