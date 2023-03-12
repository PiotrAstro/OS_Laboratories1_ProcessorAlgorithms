package ProcessorSimulator.Strategy;

import ProcessorSimulator.Process;
import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;

import java.util.Comparator;

public class FCFS implements Strategy{
    private Processor processor;
    private Process previousProcess;
    private Process currentProcess;


    @Override
    public void setProcessor(Processor processor) {
        this.processor = processor;
        previousProcess = null;
    }

    @Override
    public void stepProcess() {
        currentProcess = processor.getDoingList().getProcess(0);
        currentProcess.doOneStep(processor.getSelfTimer());

        if(currentProcess != previousProcess) {
            processor.newSwitch();
            previousProcess = currentProcess;
        }
    }

    public static class ComparatorByTimeLeft implements Comparator<Process> {
        @Override
        public int compare(Process Process1, Process Process2) {
            if(Process1.getTimeLeft() < Process2.getTimeLeft())
                return 1;
            else
                return -1;
        }
    }

    public String toString() {
        return "Strategy: FCFS";
    }
}
