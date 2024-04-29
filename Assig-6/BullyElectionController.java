import java.util.Scanner;

class Bully {
    int coordinator;
    int max_processes;
    boolean[] processes;

    // new process are created with taking the user i/p and also defining the coordinator of the processes
    public Bully(int num_processes) {
        max_processes = num_processes;
        coordinator = max_processes;
        processes = new boolean[max_processes + 1];
        
        System.out.println("Creating the processes");
        
        for (int i = 1; i <= max_processes; i++) {
            processes[i] = true;
            System.out.println("Process with proc_id " + i + " is created !!");
        }

        System.out.println("Coordinator of all the distributed process is " + coordinator);
    }

    // displaying all the currently running processes it will display the processes which are currently ON (RAM)
    public void displayProcess() {
        System.out.println("***** Displaying Process ******");
        for (int pid = 1; pid <= max_processes; pid++) {
            if (processes[pid]) {
                System.out.println("Process with id " + pid + " Currently Running!!!");
            }
            else {
            	System.out.println("Process with id " + pid + " Currently dead!!!");
            }
        }
        System.out.println("Coordinator of all the distributed process is " + coordinator);
        System.out.println();
    }

    // Now making the provided process id up
    public void upProcess(int pid) {
        if (processes[pid]) {
            System.out.println("Given process with proc_id " + pid + " is already up");
        } else {
            System.out.println("Making the Proces with id " + pid + " up");
            processes[pid] = true;
            System.out.println("Done !!");
        }
    }

    // Now making the process down
    public void downProcess(int pid) {
        if (!processes[pid]) {
            System.out.println("Process with proc_id " + pid + " is already down !!");
        } else {
            System.out.println("Making the Proces with id " + pid + " down");
            processes[pid] = false;
            System.out.println("Done !!");
        }
    }

    // run the election algorithm when it is requested by any other process
    public void runElection(int pid) {
        // handling exceptional case
        if (!processes[pid]) {
            System.out.println("Process who requested for election is dead !!");
            return;
        }
        coordinator = pid;
        for (int process = pid + 1; process <= max_processes; process++) {
            if (processes[process]) {
                coordinator = process;
            }
        }

        this.displayProcess();
    }
}

public class BullyElectionController {
    public static void main(String[] args) {
        int num_proc;
        int choice;
        boolean flag = true;
        Bully bully = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("**** BULLY ALGORITHM *****");
        do {
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice:- ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Number of process you want to create");
                    num_proc = sc.nextInt();
                    bully = new Bully(num_proc);
                    break;
                case 2:
                    bully.displayProcess();
                    break;
                case 3:
                    System.out.println("Enter the pid which you want to up");
                    int upPid = sc.nextInt();
                    bully.upProcess(upPid);
                    break;
                case 4:
                    System.out.println("Enter the pid which you want to down");
                    int downPid = sc.nextInt();
                    bully.downProcess(downPid);
                    break;
                case 5:
                    System.out.println("Enter the pid of Prcess which want to start election");
                    int eleId = sc.nextInt();
                    bully.runElection(eleId);
                    break;
                case 6:
                    System.out.println("Do you want to dis-continue with the operations");
                    flag = sc.nextInt() != 6;
                    break;
                default:
                    System.out.println("Enter the valid choice");
                    break;
            }

        } while (flag);
    }
}

