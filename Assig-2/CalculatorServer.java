import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

public class CalculatorServer {

    public static void main(String[] args) {

        try {

            ORB orb = ORB.init(args, null);

            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();

            CalculatorImpl cal = new CalculatorImpl();

            org.omg.CORBA.Object calReference = rootPOA.servant_to_reference(cal);
            System.out.println(calReference.getClass().getName());
            System.out.println(calReference instanceof org.omg.CORBA.Object);
            Calculator cRef = CalculatorApp.CalculatorHelper.narrow(calReference);

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Calculator";
            NameComponent[] path = ncRef.to_name(name);

            ncRef.rebind(path, cRef);
            System.out.println("Server is started !!!!");
            orb.run();

        } catch (Exception e) {
            System.out.println("Exception is occurred at server side " + e);
        }
    }
}

class CalculatorImpl extends CalculatorPOA {

    public float add(float num1, float num2) {
        return num1 + num2;
    }

    public float subtract(float num1, float num2) {
        return num1 - num2;
    }

    public float multiplication(float num1, float num2) {
        return num1 * num2;
    }

    public float division(float num1, float num2) {
        try {
            return num1 / num2;
        } catch (Exception e) {
            System.out.println(e);
        }
        return Integer.MIN_VALUE * -1;
    }
}

