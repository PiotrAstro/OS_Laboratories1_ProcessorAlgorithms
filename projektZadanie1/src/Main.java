import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;
import ProcessorSimulator.Strategy.FCFS;
import ProcessorSimulator.Strategy.RoundRobin;
import ProcessorSimulator.Strategy.SJFExpropration;
import ProcessorSimulator.Strategy.SJFNormal;

public class Main {
    public static void main(String[] args) {
        ProcessList processList = new ProcessList(10000, 40, 1000000, 1000000);
        Processor processor;

        processor = new Processor(processList, new FCFS());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new SJFNormal());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new SJFExpropration());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new RoundRobin(40));
        processor.runAllProcesses();
        processor.showStatistics();


    }
}