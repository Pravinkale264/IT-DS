import java.rmi.*;

interface ServerIntf extends Remote {
	public double addition (double num11, double num2) throws RemoteException;
}
