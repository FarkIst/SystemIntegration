package rmisever;
/**
 * This interface defines the methods, which will be accessible remotely
 * It has to 
 * - extend the java.rmi.Remote interface
 * - all its methods must throw java.rmt.RemoteException, which will be caught by the clients, so they know if a RMI was successful
 *
 * @author Dora Di
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIInterface extends Remote
{
  // Simple arithmetics
  public List<User> getUsers() throws RemoteException;

}

