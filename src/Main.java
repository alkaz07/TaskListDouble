import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Double> doubleValues = loadFromF("dum");
        System.out.println("doubleValues = " + doubleValues);
        //perestanovka(doubleValues);
        // perestanovka2(doubleValues);
       // perestanovka3(doubleValues);
        perestanovka5(doubleValues);
        System.out.println("doubleValues = " + doubleValues);
    }

    private static List<Double> loadFromF(String fname) {
        List<Double> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fname))) {
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] massiv = s.split("; ");
                for (String token : massiv) {
                    Double d = Double.parseDouble(token.replace(",", "."));
                    numbers.add(d);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Аааааааа! " + e.getMessage());
        }
        return numbers;
    }


    private static void perestanovka(List<Double> doubleValues) {
        //проход от начала к концу и перекидывание положительных
        for (int i = 0, j = 0; i < doubleValues.size(); i++) {
            if (doubleValues.get(j) >= 0) {
                double x = doubleValues.get(j);
                doubleValues.add(x);
                doubleValues.remove(j);
            } else
                j++;
        }
    }

    private static void perestanovka2(List<Double> doubleValues) {
        //проход от конца к началу и перекидывание отрицательных
        for (int i = 0, j = doubleValues.size() - 1; i < doubleValues.size(); i++) {
            if (doubleValues.get(j) < 0) {
                double x = doubleValues.get(j);
                doubleValues.remove(j);
                doubleValues.add(0, x);
            } else
                j--;
        }
    }

    private static void perestanovka3(List<Double> doubleValues) {
        //использование стандартной сортировки с сохранением порядка отрицательных и порядка положительных значений
        doubleValues.sort((d1, d2) -> {
            if (d1 < 0 && d2 >= 0)
                return -1;
            if (d1 >= 0 && d2 < 0)
                return 1;
            return 0;
        });
    }

    private static void perestanovka4(List<Double> doubleValues) {
        //фильтрация в 2 списка и соединение

        //получить список отрицательных
        List<Double> negatives = new ArrayList<>();
        for (Double d: doubleValues)
            if(d<0) negatives.add(d);
        //получить список положительных
        List<Double> positives = new ArrayList<>();
        for (Double d: doubleValues)
            if(d>=0) positives.add(d);
        //соединить списки в один
        doubleValues.clear();
        doubleValues.addAll(negatives);
        doubleValues.addAll(positives);
    }

    private static void perestanovka5(List<Double> doubleValues) {
        //фильтрация в 2 списка через StreamAPI и соединение

        //получить список отрицательных
        List<Double> negatives = doubleValues.stream().filter(d->(d<0)).toList();
        //получить список положительных
        List<Double> positives = doubleValues.stream().filter(d->(d>=0)).toList();
        //соединить списки в один
        doubleValues.clear();
        doubleValues.addAll(negatives);
        doubleValues.addAll(positives);
    }
}