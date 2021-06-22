package client;
/**
 * Interface for Client Controllers objects
 * @author Yonatan Rozen
 *
 */
public interface IClientController {
	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	public void accept(Object obj);
}
