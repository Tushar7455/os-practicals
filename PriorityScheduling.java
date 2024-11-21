import java.util.*;

class Process {
    int pid, burstTime, priority, waitingTime, turnAroundTime;

    Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class PriorityScheduling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter priority for process " + (i + 1) + " (lower value means higher priority): ");
            int priority = sc.nextInt();
            processes.add(new Process(i + 1, burstTime, priority));
        }

        // Sort processes by priority (ascending order)
        processes.sort(Comparator.comparingInt(p -> p.priority));

        int currentTime = 0;
        for (Process p : processes) {
            p.waitingTime = currentTime;
            currentTime += p.burstTime;
            p.turnAroundTime = currentTime;
        }

        System.out.println("\nProcess\tPriority\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t\t" + p.priority + "\t\t" + p.burstTime +
                    "\t\t" + p.waitingTime + "\t\t" + p.turnAroundTime);
        }

        // Calculate average waiting time and turnaround time
        double totalWaitingTime = 0, totalTurnAroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnAroundTime += p.turnAroundTime;
        }

        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / n));
    }
}
