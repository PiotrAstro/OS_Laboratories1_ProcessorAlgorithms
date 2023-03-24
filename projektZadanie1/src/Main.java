import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;
import ProcessorSimulator.Process;
import ProcessorSimulator.Strategy.FCFS;
import ProcessorSimulator.Strategy.RoundRobin;
import ProcessorSimulator.Strategy.SJFExpropration;
import ProcessorSimulator.Strategy.SJFNormal;

public class Main {
    public static void main(String[] args) {
        //initialising variables

        int numberOfProcesses = 10000;
        int numberOfProcessesAtStart = 0;
        int arrivalTimeRange = 1000000;
        int doingTimeRange = 100;
        int mostAtPoint = 10;
        int whatRangeFor70Percent = 10;

        int roundRobinTimeQuant = 20;

        ProcessList processList = new ProcessList(
                numberOfProcesses,
                numberOfProcessesAtStart,
                arrivalTimeRange,
                doingTimeRange,
                mostAtPoint,
                whatRangeFor70Percent);
        processList.addProcess(new Process(0, 100000));


        //processList.saveToFile("..\\savedProcesses.txt");

        //----------------------------------------------------------------------------------
        //initialising processor and running all processes
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

        processor = new Processor(processList, new RoundRobin(roundRobinTimeQuant));
        processor.runAllProcesses();
        processor.showStatistics();
    }
}