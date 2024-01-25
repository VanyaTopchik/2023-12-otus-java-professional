package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {
  private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

  public Map.Entry<Customer, String> getSmallest() {
    return copyEntry(map.firstEntry());
  }

  public Map.Entry<Customer, String> getNext(Customer customer) {
    return copyEntry(map.higherEntry(customer));
  }

  public void add(Customer customer, String data) {
    map.put(customer, data);
  }

  private Map.Entry<Customer, String> copyEntry(Map.Entry<Customer, String> entry) {
    if (entry == null) {
      return null;
    }
    Customer key = entry.getKey();
    String value = entry.getValue();
    return Map.entry(new Customer(key.getId(), key.getName(), key.getScores()), value);
  }
}
