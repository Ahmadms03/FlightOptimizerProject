import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class FlightGraph {
    private CityNode head;

    public FlightGraph() {
        head = null;
    }

    public void addFlight(String from, String to, int cost, int duration) {
        CityNode fromNode = findOrAddCity(from);
        CityNode toNode = findOrAddCity(to);

        fromNode.addFlight(new Flight(from, to, cost, duration));
        toNode.addFlight(new Flight(to, from, cost, duration)); // Add reverse direction for undirected graph
    }

    private CityNode findOrAddCity(String cityName) {
        if (head == null) {
            head = new CityNode(cityName);
            return head;
        }

        CityNode current = head;
        while (current != null) {
            if (current.name.equals(cityName)) {
                return current;
            }
            if (current.next == null) {
                break;
            }
            current = current.next;
        }

        CityNode newCity = new CityNode(cityName);
        current.next = newCity;
        return newCity;
    }

    public List<FlightPath> findFlightPaths(String start, String end, char sortPreference) {
        List<FlightPath> paths = new LinkedList<>();
        Stack<FlightPath> stack = new Stack<>();
        FlightPath initialPath = new FlightPath(start);

        stack.push(initialPath);

        while (!stack.isEmpty()) {
            FlightPath currentPath = stack.pop();
            if (currentPath.getCurrentCity().equals(end)) {
                paths.add(new FlightPath(currentPath));
                continue;
            }

            CityNode cityNode = findOrAddCity(currentPath.getCurrentCity());
            for (Flight flight : cityNode.flights) {
                if (!currentPath.visitedContains(flight.to)) {
                    FlightPath newPath = new FlightPath(currentPath);
                    newPath.addFlight(flight);
                    stack.push(newPath);
                }
            }
        }

        if (sortPreference == 'T') {
            paths.sort(Comparator.comparingInt(FlightPath::getTotalTime));
        } else {
            paths.sort(Comparator.comparingInt(FlightPath::getTotalCost));
        }

        return paths.size() > 3 ? paths.subList(0, 3) : paths;
    }
}
