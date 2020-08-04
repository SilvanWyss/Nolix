//package declaration
package ch.nolix.common.endPoint2;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.common.constant.IPv6Catalogue;
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.processProperty.ConnectionOrigin;
import ch.nolix.common.processProperty.TargetInfoState;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 240
 */
public final class NetEndPoint extends BaseNetEndPoint {
	
	//attributes
	private final Socket socket;
	private final InputStream socketInputStream;
	private final OutputStream socketOutputStream;
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to 
	 * the main target on the given port on the local machine.
	 * 
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final int port) {
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to the given target on the given port on the local machine.
	 * 
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final int port, final String target) {
		this(IPv6Catalogue.LOOP_BACK_ADDRESS, port, target);
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the main target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 */
	public NetEndPoint(final String ip, final int port) {
		
		super(ConnectionOrigin.REQUESTED_CONNECTION, TargetInfoState.RECEIVED_TARGET_INFO);
		
		Validator
		.assertThat(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		setPreCloseAction(this::preClose);
		
		try {
			socket = new Socket(ip, port);
			socketInputStream = socket.getInputStream();
			socketOutputStream = socket.getOutputStream();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		sendTargetMessage();
		new NetEndPointMessageListener(this).start();
	}
	
	//constructor
	/**
	 * Creates a new {@link NetEndPoint} that will connect to
	 * the given target on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param target
	 * @throws OutOfRangeException if the given port is not in [0, 65535].
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	public NetEndPoint(final String ip, final int port, final String target) {
		
		super(ConnectionOrigin.REQUESTED_CONNECTION, target);
		
		Validator
		.assertThat(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		setPreCloseAction(this::preClose);
		
		try {
			socket = new Socket(ip, port);
			socketInputStream = socket.getInputStream();
			socketOutputStream = socket.getOutputStream();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
		
		sendTargetMessage();
		new NetEndPointMessageListener(this).start();
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} with the given socket.
	 * The given socketInputStream and the given socketOutputStream belong to the given socket.
	 * 
	 * @param socket
	 * @param socketInputStream
	 * @param socketOutputStream
	 * @throws ArgumentIsNullException if the given socket is null.
	 * @throws ArgumentIsNullException if the given socketInputStream is null.
	 * @throws ArgumentIsNullException if the given socketOutputStream is null.
	 */
	NetEndPoint(
		final Socket socket,
		final InputStream socketInputStream,
		final OutputStream socketOutputStream
	) {
		
		super(ConnectionOrigin.ACCEPTED_CONNECTION, TargetInfoState.RECEIVED_TARGET_INFO);
		
		Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
		Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
		Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();
		
		setPreCloseAction(this::preClose);
		
		this.socket = socket;
		this.socketInputStream = socketInputStream;
		this.socketOutputStream = socketOutputStream;
		
		new NetEndPointMessageListener(this).start();
	}
	
	//constructor
	/**
	 * Creates a new {@link BaseNetEndPoint} with the given socket and target.
	 * The given socketInputStream and the given socketOutputStream belong to the given socket.
	 * 
	 * @param socket
	 * @param socketInputStream
	 * @param socketOutputStream
	 * @param target
	 * @throws ArgumentIsNullException if the given socket is null.
	 * @throws ArgumentIsNullException if the given socketInputStream is null.
	 * @throws ArgumentIsNullException if the given socketOutputStream is null.
	 * @throws ArgumentIsNullException if the given target is null.
	 * @throws InvalidArgumentException if the given target is blank.
	 */
	NetEndPoint(
		final Socket socket,
		final InputStream socketInputStream,
		final OutputStream socketOutputStream,
		final String target
	) {
		
		super(ConnectionOrigin.ACCEPTED_CONNECTION, target);
		
		Validator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();
		Validator.assertThat(socketInputStream).thatIsNamed("socket input stream").isNotNull();
		Validator.assertThat(socketOutputStream).thatIsNamed("socket output stream").isNotNull();
		
		setPreCloseAction(this::preClose);
		
		this.socket = socket;
		this.socketInputStream = socketInputStream;
		this.socketOutputStream = socketOutputStream;
		
		new NetEndPointMessageListener(this).start();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EndPointType getType() {
		return EndPointType.REGULAR_SOCKET;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void sendRawMessage(final String rawMessage) {
		
		assertIsOpen();
		
		try {
			socketOutputStream.write((rawMessage + "\r\n").getBytes(StandardCharsets.UTF_8));
			socketOutputStream.flush();
		}
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//method
	InputStream getRefInputStream() {
		return socketInputStream;
	}
	
	//method
	private boolean canWork() {
		return !socket.isClosed();
	}
	
	//method
	private void preClose() {
		if (canWork()) {
			try {
				sendRawMessage(NetEndPointProtocol.CLOSE_PREFIX);
				socket.close();
			}
			catch (final IOException pIOException) {
				throw new WrapperException(pIOException);
			}
		}
	}
}
