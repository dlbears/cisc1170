import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Date;
import java.util.Collections;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.stream.IntStream;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.lang.IllegalArgumentException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;



public class Transactor {

private interface RowShape {
  //Formatting Helpers
  public Double getDiscountedPrice();
  public String getDateString();

  //Core Getters
  public Date getDate();
  public String getSKU();
  public Double getPrice();
  public Integer getDiscount();
  
  //Core Setters 
  public void setDate(Object o) throws ParseException, IllegalArgumentException;
  public void setSKU(Object o) throws IllegalArgumentException;
  public void setPrice(Object o) throws IllegalArgumentException;
  public void setDiscount(Object o) throws IllegalArgumentException;

}

  // A 4-tuple data structure which implements the Shape interface stored using a size 4 ArrayList
  private static class Quartet implements RowShape {
  private List<Object> items; // tuple items
  private SimpleDateFormat simpleDate = new SimpleDateFormat("mm-dd"); // date format for parsing inputs and formatting outputs
  public Quartet(List<Object> init) throws ParseException, IllegalArgumentException {
    if (init.size() != 4) throw new IllegalArgumentException("Requires exactly 4 elements within the list.");
    items = new ArrayList<>();
    for (int i = 0; i < init.size(); i++) { // Clone/Parse elements from the init list
      switch (i) {
        case 0:
          setDate(init.get(i));
          break;
        case 1:
          setSKU(init.get(i));
          break;
        case 2:
          setPrice(init.get(i));
          break;
        case 3:
          setDiscount(init.get(i));
          break;
      }
    }
  } 
  
 public Date getDate() {
    return (Date) items.get(0);
  }

  public String getDateString() {
    return simpleDate.format((Date) items.get(0));
  }

  public String getSKU() {
    return (String) items.get(1);
  }

  public Double getPrice() {
    return (Double) items.get(2);
  }

  public Integer getDiscount() {
    return (Integer) items.get(3);
  }

  public void setDate(Object o) throws ParseException {
    if (items.size() >= 1) items.remove(0); //Replace if Existing
    if (o instanceof Date) {
      items.add(0, (Date) o);
    } else if (o instanceof String) { 
      items.add(0, simpleDate.parse((String) o)); // Parse String to Java Date
    } else {
      throw new IllegalArgumentException("Invalid type");
    }
  }
  public void setSKU(Object o) throws IllegalArgumentException {
    if (items.size() >= 2) items.remove(1);
    if (o instanceof String) {
      items.add(1, (String) o);
    } else {
      throw new IllegalArgumentException("Invalid type");
    }
  }

  public void setPrice(Object o) throws IllegalArgumentException {
    if (items.size() >= 3) items.remove(2);
    if (o instanceof Double) {
      items.add(2, (Double) o);
    } else if (o instanceof Float) {
      items.add(2, (Double) o);
    } else if (o instanceof Integer) {
      items.add(2, (Double) o);
    } else if (o instanceof String) {
      items.add(2, Double.parseDouble((String) o));
    } else {
      throw new IllegalArgumentException("Invalid type");
    }
  }

  public void setDiscount(Object o) throws IllegalArgumentException {
    if (items.size() >= 4) items.remove(3);
    if (o instanceof Integer) {
      items.add(3, (Integer) o);
    } else if (o instanceof String) {
      items.add(3, Integer.parseInt((String) o));
    } else {
      throw new IllegalArgumentException("Invalid type");
    }
  }

   public Double getDiscountedPrice() {
      Double price = this.getPrice();
      Integer discount = this.getDiscount();
      return price - (price * (discount * 0.01));
    }

}

   

    public static void main(String paths[]) throws ParseException, IOException, FileNotFoundException {
      
      //Init
      Path p;
      Scanner s;
      List<Quartet> rowList;
      Integer reportCounter = 0;

      //Read/Process Files
      for (String filePath: paths) {
        p = Paths.get(filePath);
        s = new Scanner(p.toFile());
        rowList = new ArrayList<>();
        s.nextLine(); // Skip First Line (Table headers)
        while (s.hasNextLine()) { // Process file by row
          String row = s.nextLine();
          String[] rawColumn = row.split(",", 4); // Split into Columns (Parse column seperator)
          if (rawColumn.length < 4) break; // Break on missing fields
          List<Object> genericColumn = Arrays.asList(rawColumn) // upclass (defer parsing)
                                .stream()
                                .map(String::trim) // post process by trimming superfluous whitespace
                                .collect(Collectors.toList());
          Quartet columns = new Quartet(genericColumn);
          rowList.add(columns);
        }

        //Process data
        List<Quartet> rows = rowList.stream().collect(Collectors.toList()); 
        String minDate = Collections.min(rows, (o1, o2) -> o1.getDate().compareTo(o2.getDate())).getDateString(),
               maxDate = Collections.max(rows, (o1, o2) -> o1.getDate().compareTo(o2.getDate())).getDateString();
        Map<Integer, Double> indexedPrices = IntStream.range(0, rows.size()).boxed().collect(Collectors.toMap(Function.identity(), i -> rows.get(i).getDiscountedPrice()));
        List<Double> prices = new ArrayList<>(indexedPrices.values());
        Double total = prices.stream().mapToDouble(d -> d).sum();
        List<Map.Entry<Integer, Double>> seqIndexedPrices = new ArrayList<>(indexedPrices.entrySet());
        Map.Entry<Integer, Double> minItem = Collections.min(seqIndexedPrices, (e1, e2) -> e1.getValue().compareTo(e2.getValue())),
                                  maxItem = Collections.max(seqIndexedPrices, (e1, e2) -> e1.getValue().compareTo(e2.getValue()));
        Double tax = total * 0.08875;

        //Output Report File
        FileWriter reportFile = new FileWriter("../logs/report-" + reportCounter);
        reportFile.write("Report from " + minDate + " to " + maxDate + "\n");
        reportFile.write(String.format("The total is $%.2f\n", total));
        reportFile.write(String.format("The tax is $%.2f\n", tax));
        reportFile.write(String.format("The highest price item is #%d at $%.2f\n", maxItem.getKey() + 1, maxItem.getValue()));
        reportFile.write(String.format("The lowest price item is #%d at $%.2f\n", minItem.getKey() + 1, minItem.getValue()));
        reportFile.close();

        //Output to Console
        System.out.println("File: " + p.getFileName() + " processed.\n Report generated at: ../logs/report-" + reportCounter);
      }
      
     
    }
}