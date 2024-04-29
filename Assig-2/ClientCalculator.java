import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class ClientCalculator{

	public static void main(String[] args){
	
		try{
			org.omg.CORBA.ORB orb= org.omg.CORBA.ORB.init(args,null);
			
			org.omg.CORBA.Object objRef= orb.resolve_initial_references("NameService");
			NamingContextExt ncRef= NamingContextExtHelper.narrow(objRef);
			
			String name="Calculator";
			Calculator calculator= CalculatorHelper.narrow(ncRef.resolve_str(name));
			System.out.println("Addition of two number (10 and 20) is "+ calculator.add(10,20));
			
			
		
		}catch(Exception e){
		
			System.out.println("Exception is occured at Client Side !! "+ e);
		}
	
	}	

}
