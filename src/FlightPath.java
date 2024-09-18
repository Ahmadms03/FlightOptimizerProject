import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FlightPath {
    List<Flight> flights;
    Set<String> visited;
    int totalCost;
    int totalTime;
    String currentCity;

    public FlightPath(String startCity) {
        flights = new LinkedList<>();
        visited = new HashSet<>();
        totalCost = 0;
        totalTime = 0;
        this.currentCity = startCity;
        visited.add(startCity);
    }

    public FlightPath(FlightPath other) {
        this.flights = new LinkedList<>(other.flights);
        this.visited = new HashSet<>(other.visited);
        this.totalCost = other.totalCost;
        this.totalTime = other.totalTime;
        this.currentCity = other.currentCity;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        visited.add(flight.to);
        totalCost += flight.cost;
        totalTime += flight.duration;
        currentCity = flight.to;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public boolean visitedContains(String city) {
        return visited.contains(city);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!flights.isEmpty()) {
            sb.append(flights.get(0).from);
            for (Flight flight : flights) {
                sb.append(" -> ").append(flight.to);
            }
        }
        sb.append(". Time: ").append(totalTime).append(" Cost: ").append(String.format("%.2f", (double) totalCost));
        return sb.toString();
    }
}
