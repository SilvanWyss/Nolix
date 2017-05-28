package ch.nolix.core.communicationInterfaces;

public interface IGenericReplier<M, R> {

	public abstract R getReply(M message);
}
