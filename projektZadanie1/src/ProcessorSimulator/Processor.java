package ProcessorSimulator;

import MathForProject.Average;
import ProcessorSimulator.Strategy.Strategy;

public class Processor {
    private int selfTimer;
    private int howManySwithes;
    private ProcessList waitingProcesses, doingProcesses, doneProcesses;
    private Strategy strategy;

    public Processor(ProcessList processListCopyFrom, Strategy strategy) {
        this.strategy = strategy;
        selfTimer = 0;
        howManySwithes = 0;

        waitingProcesses = new ProcessList(processListCopyFrom);
        doingProcesses = new ProcessList();
        doneProcesses = new ProcessList();

        strategy.setProcessor(this);

        waitingProcesses.sortProcesses(new ProcessList.ComparatorByArrivalTime());
        moveProcessesFromWaitingToDoing();
    }

    public void runAllProcesses() {
        while(doingProcesses.getNumberOfProcesses() != 0 || waitingProcesses.getNumberOfProcesses() != 0) {
            if(doingProcesses.getNumberOfProcesses() != 0) {
                strategy.stepProcess();
            }

            selfTimer++;
            moveProcessesFromDoingToDone();
            moveProcessesFromWaitingToDoing();
        }
    }

    public ProcessList getDoingList() {
        return doingProcesses;
    }

    public void moveProcessesFromWaitingToDoing() {
        for(int i = 0; i < waitingProcesses.getNumberOfProcesses(); i++) {
            if(waitingProcesses.getProcess(i).getArrivalTime() <= selfTimer) {
                Process process = waitingProcesses.getProcess(i);
                doingProcesses.addProcess(process);
                waitingProcesses.removeProcess(process);
                i--;
            }
            else {
                break;
            }
        }
    }

    public void moveProcessesFromDoingToDone() {
        for(int i = 0; i < doingProcesses.getNumberOfProcesses(); i++) {
            if(doingProcesses.getProcess(i).getTimeLeft() == 0) {
                Process process = doingProcesses.getProcess(i);
                doneProcesses.addProcess(process);
                doingProcesses.removeProcess(process);
                i--;
            }
        }
    }

    public void newSwitch() {
        howManySwithes ++;
    }

    public int getSelfTimer() {
        return selfTimer;
    }

    public void showStatistics() {
        System.out.print("\n\n");
        System.out.println(strategy.toString());
        System.out.println("Self timer: " + selfTimer);
        System.out.println("How many switches: " + howManySwithes);
        System.out.println("How many processes: " + doneProcesses.getNumberOfProcesses());

        Process process;
        Average averageWaitingTime = new Average();
        Average averageDoingTime = new Average();
        Average averageWaitingTimeBeforeFirstTry = new Average();
        int maximumWaitingTime = 0;
        int maximumDoingTime = 0;
        int maximumWaitingTimeBeforeFirstTry = 0;
        int waitingTimeTMP, doingTimeTMP, waitingTimeBeforeFirstTryTMP;

        for(int i = 0; i < doneProcesses.getNumberOfProcesses(); i++) {
            process = doneProcesses.getProcess(i);
            waitingTimeTMP = process.getProcessFinishTime() - process.getArrivalTime() - process.getInitialTimeLeft() + 1;
            doingTimeTMP = process.getProcessFinishTime() - process.getProcessStartTime() + 1;
            waitingTimeBeforeFirstTryTMP = process.getProcessStartTime() - process.getArrivalTime();

            averageWaitingTime.addValue(waitingTimeTMP);
            averageDoingTime.addValue(doingTimeTMP);
            averageWaitingTimeBeforeFirstTry.addValue(waitingTimeBeforeFirstTryTMP);

            if(waitingTimeTMP > maximumWaitingTime) {
                maximumWaitingTime = waitingTimeTMP;
            }
            if(doingTimeTMP > maximumDoingTime) {
                maximumDoingTime = doingTimeTMP;
            }
            if(waitingTimeBeforeFirstTryTMP > maximumWaitingTimeBeforeFirstTry) {
                maximumWaitingTimeBeforeFirstTry = waitingTimeBeforeFirstTryTMP;
            }
        }
        System.out.printf("Average waiting time: %.2f\n", averageWaitingTime.getValue());
        System.out.printf("Average waiting time before first try: %.2f\n", averageWaitingTimeBeforeFirstTry.getValue());
        System.out.printf("Average doing time: %.2f\n", averageDoingTime.getValue());
        System.out.println("Maximum waiting time: " + maximumWaitingTime);
        System.out.println("Maximum waiting time before first try: " + maximumWaitingTimeBeforeFirstTry);
        System.out.println("Maximum doing time: " + maximumDoingTime);
    }
}
