//FCFS without Arrival Time

public class practical5b {
    public static void main(String[] args) {
        Process[] processes = {
            new Process(1, 0, 4),
            new Process(2, 0, 3),
            new Process(3, 0, 1),
            new Process(4, 0, 2)
        };

        fcfsWithoutArrivalTime(processes);
        printProcesses(processes);
    }

    public static void fcfsWithoutArrivalTime(Process[] processes) {
        int currentTime = 0;
        for (Process process : processes) {
            process.waitingTime = currentTime;
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
