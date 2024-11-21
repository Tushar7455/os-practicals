import java.util.*;

class Process {
    int pid, burstTime, waitingTime, turnAroundTime;

    Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
    }
}

public class SJF_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(i + 1, burstTime));
        }

        // Sort processes by burst time
        processes.sort(Comparator.comparingInt(p -> p.burstTime));

        int currentTime = 0;
        for (Process p : processes) {
            p.waitingTime = currentTime;
            currentTime += p.burstTime;
            p.turnAroundTime = currentTime;
        }

        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t\t" + p.burstTime + "\t\t" + p.waitingTime + "\t\t" + p.turnAroundTime);
        }
    }
}
