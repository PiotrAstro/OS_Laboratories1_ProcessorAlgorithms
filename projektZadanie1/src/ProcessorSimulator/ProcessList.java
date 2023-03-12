package ProcessorSimulator;

import MathForProject.MathForProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ProcessList {
    private List<Process> list;

    public ProcessList(int numberOfProcesses, int numberOfProcessesAtStart, int arrivalTimeRange, int doingTimeRange) {
        if(numberOfProcesses <= 0) {
            numberOfProcesses = 1;
        }
        if(numberOfProcessesAtStart <= 0) {
            numberOfProcessesAtStart = 0;
        }
        list = new ArrayList<>(numberOfProcesses);

        Random random = new Random();

        for(int i = 0; i < numberOfProcessesAtStart; i++) {
            addProcess(0, doingTimeRange, random);
        }

        for(int i = numberOfProcessesAtStart; i < numberOfProcesses; i++) {
            addProcess(arrivalTimeRange, doingTimeRange, random);
        }
    }

    public ProcessList(ProcessList copyFrom) {
        list = new ArrayList<>(0);
        for(int i = 0; i < copyFrom.getNumberOfProcesses(); i++) {
            addProcess(copyFrom.getProcess(i).deepCopy());
        }
    }

    public ProcessList() {
        list = new ArrayList<>(0);
    }

    public void removeProcess(Process process) {
        list.remove(process);
    }

    public Process getProcess(int index) {
        return list.get(index);
    }

    public int getNumberOfProcesses() {
        return list.size();
    }

    public void addProcess(Process process) {
        list.add(process);
    }
    public void addProcess(int arrivalTimeRange, int doingTimeRange, Random random) {
        int arrivalTime, doingTime;

        doingTime = (int) Math.round(MathForProject.getRandomWithStandardDistribution(1, 40, doingTimeRange, 200));
        arrivalTime = (int) Math.round(random.nextDouble() * arrivalTimeRange);

        Process process = new Process(arrivalTime, doingTime);
        list.add(process);
    }

    public void sortProcesses(Comparator<Process> comparator) {
        list.sort(comparator);
    }

    public static class ComparatorByArrivalTime implements Comparator<Process> {
        @Override
        public int compare(Process Process1, Process Process2) {
            if (Process1.getArrivalTime() > Process2.getArrivalTime())
                return 1;
            else if (Process1.getArrivalTime() < Process2.getArrivalTime())
                return -1;
            else
                return 0;
        }
    }
}
