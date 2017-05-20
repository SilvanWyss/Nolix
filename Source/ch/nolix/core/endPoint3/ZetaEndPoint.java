//package declaration
package ch.nolix.core.endPoint3;

//Java imports
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


//own imports




import ch.nolix.core.basic.AbortableElement;
import ch.nolix.core.constants.IPv6Manager;
import ch.nolix.core.constants.PortManager;
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.IZetaReceiver;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A zeta end point can send messages to an other zeta end point that is on:
 * -the same process
 * -an other process on the same computer
 * -a process on an other computer
 *  
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 490
 */
public class ZetaEndPoint extends AbortableElement {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 2000;
	
	//constant
	private static final String DEFAULT_TARGET_APPLICATION = "DefaultApplication";

	//attributes
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	private int nextSentPackageIndex = 1;
	private final Socket socket;
	private final PrintWriter printWriter;
	
	//optional attributes
	private String targetApplication;
	private IZetaReceiver alphaReceiver;
	
	//multiple attribute
	private final List<ZetaPackage> receivedPackages = new List<ZetaPackage>();
	
	//constructor
	/**
	 * Creates new zeta end point that will connect to the default target application on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65'535].
	 */
	public ZetaEndPoint(final int port) {
		
		//Calls other constructor.
		this(IPv6Manager.LOOP_BACK_ADDRESS, port, DEFAULT_TARGET_APPLICATION);
	}
	
	//constructor
	/**
	 * Creates new zeta end point that will connect to the given target application on the given port on the local machine.
	 * 
	 * @param port
	 * @param targetApplication
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65'535].
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public ZetaEndPoint(final int port, final String targetApplication) {
		
		//Calls other constructor.
		this(IPv6Manager.LOOP_BACK_ADDRESS, port, DEFAULT_TARGET_APPLICATION);
	}
	
	//constructor
	/**
	 * Creates new zeta end point that will connect to the default target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65'535].
	 */
	public ZetaEndPoint(final String ip, final int port) {
		
		//Calls other constructor.
		this(ip, port, DEFAULT_TARGET_APPLICATION);
	}
	
	//constructor
	/**
	 * Creates new zeta end point that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws OutOfRangeArgumentException if the given port is not in [0, 65'535].
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target aplication is empty.
	 */
	public ZetaEndPoint(final String ip, final int port, final String targetApplication) {
		
		//Checks if the givne port is in [0, 65'536].
		Validator
		.supposeThat(port)
		.thatIsNamed("port")
		.isBetween(PortManager.MIN_PORT, PortManager.MAX_PORT);
			
		//Sets the target application of this zeta end point.
		this.targetApplication = targetApplication;
	
		try {
			socket = new Socket(ip, port);
			printWriter = new PrintWriter(socket.getOutputStream());
		} 
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
			
		//Creates and starts listener of this zeta end point.
		new Listener(this);
		
		send(new ZetaPackage(0, MessageRole.TARGET_APPLICATION_MESSAGE, targetApplication));
	}
	
	//package-visible constructor
	/**
	 * Creates new zeta end point with the given socket.
	 * 
	 * @param socket
	 * @throws NullArgumentException if the given socket is null.
	 */
	ZetaEndPoint(final Socket socket) {
		
		//Checks if the given socket is not null.
		Validator.supposeThat(socket).thatIsInstanceOf(Socket.class).isNotNull();
		
		//Sets the socket of this zeta end point.
		this.socket = socket;
		
		try {
			printWriter = new PrintWriter(socket.getOutputStream());
		}
		catch (final IOException exception) {
			throw new RuntimeException(exception);
		}
		
		//Creates and starts listener of this zeta end point.
		new Listener(this);
		
		waitToTargetApplication();
	}
	
	//method
	/**
	 * @return the target application of this zeta end point.
	 */
	public String getTargetApplication() {
		return targetApplication;
	}
	
	//method
	/**
	 * @return the timeout of this zeta end point in milliseconds.
	 */
	public int getTimeoutInMilliseconds() {
		return timeoutInMilliseconds;
	}
	
	//method
	/**
	 * @return true if this zeta end point has a receiver.
	 */
	public boolean hasReceiver() {
		return (alphaReceiver != null);
	}

	//method
	/**
	 * Sends the given message and returns the reply.
	 * This method throws an exception if no reply is received within the timeout.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 * @throws RuntimeException if this zeta end point is stopped.
	 * @throws RuntimeException if an error occurs by trying to send the message.
	 */
	public String sendMessageAndGetReply(final String message) {
		return sendMessageAndWaitToReply(message, true);
	}
	
	//method
	/**
	 * Sends the given message and returns the reply.
	 * This method waits to the reply without checking a timeout.
	 * 
	 * @param message
	 * @return the reply of the given message.
	 * @throws RuntimeException if this zeta end point is stopped.
	 * @throws RuntimeException if an error occurs by trying to send the message.
	 */	
	public String sendMessageAndWaitToReply(final String message) {
		return sendMessageAndWaitToReply(message, false);
	}
	
	//method
	/**
	 * Sets the receiver of this zeta end point.
	 * 
	 * @param receiver
	 * @throws NullArgumentException if the given receiver is null.
	 */
	public void setReceiver(final IZetaReceiver receiver) {
		
		//Checks if the given receiver is not null.
		Validator.supposeThat(receiver).thatIsNamed("receiver").isNotNull();
		
		this.alphaReceiver = receiver;
	}
	
	//method
	/**
	 * Sets the timeout of this zeta end point in milliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws NonPositiveArgumentException if the given timeout is not positive.
	 * @throws RuntimeException if this zeta end point is stopped.
	 */
	public void setTimeoutInMilliseconds(final int timeoutInMilliseconds) {
		
		//Checks if the given timeout is positive.
		Validator.supposeThat(timeoutInMilliseconds).thatIsNamed("timeout").isPositive();
	
		//Checks if this zeta end point is not stopped.
		throwExceptionIfAborted();

		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}
	
	//package-visible method
	/**
	 * @return the socket of this zeta end point.
	 */
	final Socket getRefSocket() {
		return socket;
	}
	
	//package-visible method
	/**
	 * Lets this zeta end point receive the given message.
	 * 
	 * @param message
	 */
	void receive(final String message) {
		receive(ZetaPackage.createZetaPackageFromString(message));
	}
	
	//method
	/**
	 * Lets this zeta end point return and remove the received package with the given index.
	 * 
	 * @param index
	 * @return the reply with the given index
	 * @throws InvalidArgumentException if this zeta end point has not received a package with the given index.
	 */
	private final ZetaPackage getAndRemoveReceivedPackage(final int index) {
		return receivedPackages.removeAndGetRefFirst(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * @return the index of the next sent package. of this zeta end point
	 */
	private final int getNextSentPackageIndex() {
		
		//Resets the index of the text sent package if it has reached the maximum value.
		if (nextSentPackageIndex == Integer.MAX_VALUE) {
			nextSentPackageIndex = 0;
		}
		
		//Returns and increments the next sent package index.
		return nextSentPackageIndex++;
	}
	
	//method
	/**
	 * @return the receiver of this zeta end point.
	 * @throws UnexistingAttributeException if this zeta end point has no receiver.
	 */
	private final IZetaReceiver getRefReceiver() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away from the compiler or the JVM.
		while (!hasReceiver()) {
			
			//The following statement that is actually unnecessary makes that the loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
				throw new UnexistingAttributeException(this, "receiver");
			}
		}
		
		return alphaReceiver;
	}
	
	//method
	/**
	 * @return true if this zeta end point has a target application.
	 */
	private boolean hasTargetApplication() {
		return (targetApplication != null);
	}
	
	//method
	/**
	 * Lets this zeta end point receive the given package.
	 * 
	 * @param package_
	 */
	private void receive(final ZetaPackage package_) {
		
		//Enumerates the message role of the given package.
		switch (package_.getMessageRole()) {
			case TARGET_APPLICATION_MESSAGE:
				setTargetApplication(package_.getMessage());
				break;
			case RESPONSE_EXPECTING_MESSAGE:
				
				try {
					final String reply = getRefReceiver().receiveMessageAndGetReply(package_.getMessage());
					send(new ZetaPackage(package_.getIndex(), MessageRole.SUCCESS_RESPONSE, reply));
				}
				catch (final Exception exception) {
					String responseMessage = exception.getMessage();
					send(new ZetaPackage(package_.getIndex(), MessageRole.ERROR_RESPONSE, responseMessage));
				}
				
				break;
			default:
				receivedPackages.addAtEnd(package_);
		}
	}
	
	//method
	/**
	 * @param index
	 * @return true if this zeta end point has received a package with the given index.
	 */
	private final boolean receivedPackage(final int index) {
		return receivedPackages.contains(rp -> rp.hasIndex(index));
	}
	
	//method
	/**
	 * Lets this zeta end point send the given package.
	 * 
	 * @param package_
	 * @throws RuntimeException if this zeta end point is stopped.
	 */
	private void send(final String package_) {
		
		//Checks if this zeta end point is not stopped.
		throwExceptionIfAborted();
		
		printWriter.println(package_);
		printWriter.flush();
	}
	
	//method
	/**
	 * Lets this zeta end point send the given package.
	 * 
	 * @param package_
	 * @throws RuntimeException if this zeta end point is stopped.
	 */
	private void send(final ZetaPackage package_) {
		send(package_.toString());
	}
	
	//method
	/**
	 * Sends the given message and waits to the reply.
	 * 
	 * @param message
	 * @param timeoutCheck
	 * @return the reply to the given message.
	 */
	private String sendMessageAndWaitToReply(
		final String message,
		final boolean timeoutCheck
	) {
		//Sends message nd receives reply.
		final int index = getNextSentPackageIndex();
		send(new ZetaPackage(index, MessageRole.RESPONSE_EXPECTING_MESSAGE, message));		
		final ZetaPackage response = waitToAndGetAndRemoveReceivedPackage(index, timeoutCheck);
		
		//Enumerates the response.
		switch (response.getMessageRole()) {
			case SUCCESS_RESPONSE:
				return response.getMessage();
			case ERROR_RESPONSE:
				throw new RuntimeException(response.getMessage());
			default:
				throw new RuntimeException("An error occured.");
		}
	}
	
	//method
	/**
	 * Sets the target application of this zeta end point.
	 * 
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	private void setTargetApplication(final String targetApplication) {
		
		//Checks if the given target application is not null or empty.
		Validator
		.supposeThat(targetApplication)
		.thatIsNamed("target application")
		.isNotEmpty();
		
		//Checks if this zeta end point has already a target applicaiton.
		if (hasTargetApplication()) {
			throw new RuntimeException("Zeta end point has already a target application.");
		}
		
		//Sets the target application of this zeta end point.
		this.targetApplication = targetApplication;
	}
	
	//method
	/**
	 * Lets this zeta end point wait to and return and remove the received package with the given index.
	 * 
	 * @param index
	 * @param timeoutCheck
	 * @return the received package with the given index.
	 * @throws RuntimeException if this zeta end point reaches its timeout before it receives a package with the given index.
	 */
	private ZetaPackage waitToAndGetAndRemoveReceivedPackage(
		final int index,
		final boolean timeoutCheck
	) {
		
		if (!timeoutCheck) {
			while (!receivedPackage(index));
		}
		else {
			
			final long startTimeInMilliseconds = System.currentTimeMillis();
			
			while (!receivedPackage(index)) {
				if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
					throw new RuntimeException("Zeta end point reached timeout while waiting to the package with the index " + index + ".");
				}
			}
		}
		
		return getAndRemoveReceivedPackage(index);
	}
	
	//method
	/**
	 * Lets this zeta end point wait until it gets its target application.
	 * 
	 *  @throws RuntimeException if this zeta end point reached its timeout before it gets its target application.
	 */
	private void waitToTargetApplication() {
		
		final long startTimeInMilliseconds = System.currentTimeMillis();
		
		//This loop suffers from being optimized away from the compiler or the JVM.
		while (!hasTargetApplication()) {
			
			//The following statement that is actually unnecessary makes that the loop is not optimized away.
			System.out.flush();
			
			if (System.currentTimeMillis() - startTimeInMilliseconds > getTimeoutInMilliseconds()) {
				throw new RuntimeException("Zeta end point reached timeout while waiting to target application.");
			}
		}
	}
}
