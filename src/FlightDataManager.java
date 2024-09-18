import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class FlightDataManager {
    public static void loadFlightData(String fileName, FlightGraph graph) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int numberOfFlights = Integer.parseInt(reader.readLine().trim());  // Read the first line for number of flights
            for (int i = 0; i < numberOfFlights; i++) {
                String[] data = reader.readLine().split("\\|");
                graph.addFlight(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]));
            }
        }
    }

    public static void processFlightPlans(String fileName, FlightGraph graph, String outputFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
            int numberOfRequests = Integer.parseInt(reader.readLine().trim());  // Read the first line for number of requests
            for (int i = 0; i < numberOfRequests; i++) {
                String[] data = reader.readLine().split("\\|");
                List<FlightPath> paths = graph.findFlightPaths(data[0], data[1], data[2].charAt(0));
                writer.println("Flight " + (i + 1) + ": " + data[0] + " to " + data[1] + " (" + data[2] + ")");
                if (paths.isEmpty()) {
                    writer.println("No viable flight paths found.");
                } else {
                    for (int j = 0; j < paths.size(); j++) {
                        writer.println("Path " + (j + 1) + ": " + paths.get(j));
                    }
                }
            }
        }
    }
}
