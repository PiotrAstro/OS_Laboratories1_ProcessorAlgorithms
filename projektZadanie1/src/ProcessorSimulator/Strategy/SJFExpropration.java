package ProcessorSimulator.Strategy;

import ProcessorSimulator.Process;
import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;

import java.util.Comparator;

public class SJFExpropration implements Strategy{
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
        processor.getDoingList().sortProcesses(new SJFNormal.ComparatorByTimeLeft());
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
            if (Process1.getTimeLeft() > Process2.getTimeLeft())
                return 1;
            else if (Process1.getTimeLeft() < Process2.getTimeLeft())
                return -1;
            else
                return 0;
        }
    }

    public String toString() {
        return "Strategy: SJFExpropration";
    }
}
