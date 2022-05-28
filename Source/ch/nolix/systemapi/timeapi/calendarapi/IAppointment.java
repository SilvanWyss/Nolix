//package declaration
package ch.nolix.systemapi.timeapi.calendarapi;

//own imports
import ch.nolix.core.attributeuniversalapi.mutablemandatoryattributeuniversalapi.IMutableSubjectHolder;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IAppointment<A extends IAppointment<A>> extends IMutableSubjectHolder<A> {
	
	//method declaration
	ITime getEndTime();
	
	//method declaration
	ITime getStartTime();
	
	//method
	default boolean isAfter(final IAppointment<?> appointment) {
		return isAfter(appointment.getEndTime());
	}
	
	//method
	default boolean isAfter(final ITime time) {
		return getStartTime().isAfter(time);
	}
	
	//method
	default boolean isBefore(final ITime time) {
		return getEndTime().isBefore(time);
	}
	
	//method
	default boolean isBefore(final IAppointment<?> appointment) {
		return isBefore(appointment.getStartTime());
	}
	
	//method declaration
	A setEndTime(ITime endTime);
	
	//method declaration
	A setStartTime(ITime startTime);
}
