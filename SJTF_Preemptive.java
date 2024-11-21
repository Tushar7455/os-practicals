import java.util.*;

class Process {
    int pid, burstTime, arrivalTime, remainingTime, waitingTime, turnAroundTime, completionTime;

    Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class SJTF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        List<Process> processes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(i + 1, arrivalTime, burstTime));
        }

        int currentTime = 0, completed = 0;
        Process currentProcess = null;

        while (completed < n) {
            // Get process with the shortest remaining time at the current time
            Process shortest = null;
            for (Process p : processes) {
                if (p.arrivalTime <= currentTime && p.remainingTime > 0) {
                    if (shortest == null || p.remainingTime < shortest.remainingTime) {
                        shortest = p;
                    }
                }
            }

            if (shortest == null) {
                currentTime++;
                continue;
            }

            currentProcess = shortest;
            currentProcess.remainingTime--;

            if (currentProcess.remainingTime == 0) {
                completed++;
                currentProcess.completionTime = currentTime + 1;
                currentProcess.turnAroundTime = currentProcess.completionTime - currentProcess.arrivalTime;
                currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.burstTime;
            }

            currentTime++;
        }

        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (Process p : processes) {
            System.out.println("P" + p.pid + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime +
                    "\t\t" + p.waitingTime + "\t\t" + p.turnAroundTime);
        }
    }
}
