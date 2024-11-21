import java.util.*;

class Process {
    int pid, burstTime, remainingTime, waitingTime, turnAroundTime, completionTime;

    Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter the time quantum: ");
        int timeQuantum = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            int burstTime = sc.nextInt();
            processes.add(new Process(i + 1, burstTime));
        }

        int currentTime = 0;
        Queue<Process> queue = new LinkedList<>(processes);
        while (!queue.isEmpty()) {
            Process currentProcess = queue.poll();

            // Execute process for time quantum or remaining time
            int timeExecuted = Math.min(timeQuantum, currentProcess.remainingTime);
            currentProcess.remainingTime -= timeExecuted;
            currentTime += timeExecuted;

            // If process is not finished, add it back to the queue
            if (currentProcess.remainingTime > 0) {
                queue.add(currentProcess);
            } else {
                // Process is completed
                currentProcess.completionTime = currentTime;
                currentProcess.turnAroundTime = currentProcess.completionTime;
                currentProcess.waitingTime = currentProcess.turnAroundTime - currentProcess.burstTime;
            }
        }

        // Display results
        System.out.println("\nProcess\tBurst Time\tWaiting Time\tTurnaround Time");
        double totalWaitingTime = 0, totalTurnAroundTime = 0;
        for (Process p : processes) {
            totalWaitingTime += p.waitingTime;
            totalTurnAroundTime += p.turnAroundTime;
            System.out.println("P" + p.pid + "\t\t" + p.burstTime + "\t\t" + p.waitingTime + "\t\t" + p.turnAroundTime);
        }

        // Display averages
        System.out.println("\nAverage Waiting Time: " + (totalWaitingTime / n));
        System.out.println("Average Turnaround Time: " + (totalTurnAroundTime / n));
    }
}
