//FCFS with Arrival Time

import java.util.Arrays;
import java.util.Comparator;

class Process {
    int pid;         // Process ID
    int arrivalTime; // Arrival time of process
    int burstTime;   // Burst time of process
    int waitingTime; // Waiting time of process
    int turnaroundTime; // Turnaround time of process

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
    }
}

public class practical5 {
    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 0, 4),
            new Process(2, 1, 3),
            new Process(3, 2, 1),
            new Process(4, 3, 2)
        };

        fcfsWithArrivalTime(processes);
        printProcesses(processes);
    }

    public static void fcfsWithArrivalTime(Process[] processes) {
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        for (Process process : processes) {
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }
            process.waitingTime = currentTime - process.arrivalTime;
            process.turnaroundTime = process.waitingTime + process.burstTime;
            currentTime += process.burstTime;
        }
    }

    public static void printProcesses(Process[] processes) {
        System.out.println("PID\tArrival\tBurst\tWaiting\tTurnaround");
        for (Process process : processes) {
            System.out.println(process.pid + "\t" +
                               process.arrivalTime + "\t" +
                               process.burstTime + "\t" +
                               process.waitingTime + "\t" +
                               process.turnaroundTime);
        }
    }
}

