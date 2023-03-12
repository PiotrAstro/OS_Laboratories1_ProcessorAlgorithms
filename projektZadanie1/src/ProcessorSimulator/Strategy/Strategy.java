package ProcessorSimulator.Strategy;

import ProcessorSimulator.ProcessList;
import ProcessorSimulator.Processor;

public interface Strategy {
    public void setProcessor(Processor processor);
    public void stepProcess();
}
