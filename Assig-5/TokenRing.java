import java.util.concurrent.locks.*;
import java.util.Scanner;

class TkRing {
    int processes;
    int tokenHandler;
    Lock tokenLock = null;
    Condition tokenAvailable = null;

    public TkRing(int numOfProc, int handler) {
        this.processes = numOfProc;
        this.tokenHandler = handler;
        this.tokenLock = new ReentrantLock();
        this.tokenAvailable = tokenLock.newCondition();
    }

    // creating the process
    public void createProcesses() {

        for (int i = 0; i < processes; i++) {
            System.out.println("Process with pid " + i + " is created !!");
            final int pid = i;
            // assigning task to process
            new Thread(() -> {
                processTaskHandler(pid);
            }).start();
        }

    }

    // creating the processTaskHandler so that threads are able to complete their task

    public void processTaskHandler(int pid) {
        while (true) {
            try {
                Thread.sleep(1000);
                this.tokenLock.lock();
                try {
                    while (pid != this.tokenHandler) {
                        System.out.println("Process with process id " + pid + " trying to enter in critical section but has no token");
                        this.tokenAvailable.await();
                    }

                    System.out.println("Process id " + pid + " enters in the critical section !!!");
                    System.out.println(" **** Executing the critical section ..... ***** ");
                    System.out.println("Process id " + pid + " leaves the critical section !!!");

                    this.tokenHandler = (this.tokenHandler + 1) % this.processes;

                    System.out.println("Token is passing from process with id " + pid + " to the id" + this.tokenHandler);
                    this.tokenAvailable.signalAll();

                } finally {
                    this.tokenLock.unlock();
                }
            } catch (Exception e) {
                System.out.println("Exception is occured while Executing the task " + e);

            }

        }

    }
}

public class TokenRing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numProcesses;
        int tkHandler;
        System.out.println("Enter the number of processes you want to create");
        numProcesses = sc.nextInt();
        System.out.println("Enter the handler you want to kept at the start");
        tkHandler = sc.nextInt();

        System.out.println("******* TOKEN RING ALGORITHM *******");
        System.out.println(" && Creating the process");

        TkRing tkRing = new TkRing(numProcesses, tkHandler);
        tkRing.createProcesses();
    }
}

