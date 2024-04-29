import java.rmi.*;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			String serverURL = "rmi://localhost/Server";
			ServerIntf serverintf = (ServerIntf) Naming.lookup(serverURL);
			
			System.out.println("Enter first number: ");
			double num1 = sc.nextDouble();
			System.out.println("Enter second number: ");
			double num2 = sc.nextDouble();
			
			System.out.println("Result is: ");
			System.out.println("Addition: " + serverintf.addition(num1, num2));
		}
		catch (Exception e) {
			System.out.println("Exception");
		}
	}
}
