package ProcessorSimulator;

public class Process {
    private int arrivalTime, processStartTime, processFinishTime, timeLeft, initialTimeLeft;

    public Process(int initialArrivalTime, int initialTimeLeft) {
        this.arrivalTime = initialArrivalTime;
        this.timeLeft = initialTimeLeft;
        this.initialTimeLeft = timeLeft;
        this.processStartTime = -1;
        this.processFinishTime = -1;
    }

    public Process deepCopy() {
        return new Process(this.arrivalTime, this.timeLeft);
    }

    public void doOneStep(int processorTime) {
        if(processStartTime == -1) {
            processStartTime = processorTime;
        }

        if(timeLeft > 0) {
            timeLeft --;

            if(timeLeft == 0) {
                processFinishTime = processorTime;
            }
        }
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getProcessStartTime() {
        return processStartTime;
    }

    public int getInitialTimeLeft() {
        return initialTimeLeft;
    }

    public int getProcessFinishTime() {
        return processFinishTime;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String toString() {
        return arrivalTime + "; " + initialTimeLeft;
    }
}
