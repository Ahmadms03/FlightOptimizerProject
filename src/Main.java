import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FlightGraph graph = new FlightGraph();
        String dataFile = "flightdata.txt";
        String planFile = "pathstocalculate.txt";
        String outputFile = "Output.txt";

        try {
            FlightDataManager.loadFlightData(dataFile, graph);
            FlightDataManager.processFlightPlans(planFile, graph, outputFile);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
