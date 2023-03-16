import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;
import ProcessorSimulator.Process;
import ProcessorSimulator.Strategy.FCFS;
import ProcessorSimulator.Strategy.RoundRobin;
import ProcessorSimulator.Strategy.SJFExpropration;
import ProcessorSimulator.Strategy.SJFNormal;

public class Main {
    public static void main(String[] args) {
        ProcessList processList = new ProcessList(
                10000,
                40,
                1000,
                100,
                20,
                40);
//        ProcessList processList = new ProcessList(0, 0, 1000000, 1000000);
        Processor processor;

        processList.addProcess(new Process(2, 200));

        processor = new Processor(processList, new FCFS());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new SJFNormal());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new SJFExpropration());
        processor.runAllProcesses();
        processor.showStatistics();

        processor = new Processor(processList, new RoundRobin(50));
        processor.runAllProcesses();
        processor.showStatistics();


    }
}