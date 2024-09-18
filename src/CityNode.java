import java.util.LinkedList;
import java.util.List;

public class CityNode {
    String name;
    List<Flight> flights;
    CityNode next;

    public CityNode(String name) {
        this.name = name;
        this.flights = new LinkedList<>();
        this.next = null;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }
}
