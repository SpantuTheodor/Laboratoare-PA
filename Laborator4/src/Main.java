import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        Resident r0 = new Resident("R0");
        Resident r1 = new Resident("R1");
        Resident r2 = new Resident("R2");
        Resident r3 = new Resident("R3");

        List<Resident> residentList = new ArrayList<>();
        residentList.add(r0);
        residentList.add(r2);
        residentList.add(r1);
        residentList.add(r3);

        Collections.sort(residentList,
                Comparator.comparing(Resident::getName));

        System.out.println(residentList);

        Hospital h0 = new Hospital("H0" , 1 );
        Hospital h1 = new Hospital("H1" , 2 );
        Hospital h2 = new Hospital("H2" , 2 );

        Set<Hospital> hospitalTreeSet = new TreeSet<Hospital>();


        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();

        resPrefMap.put(r0, Arrays.asList(h0,h1,h2));
        resPrefMap.put(r1, Arrays.asList(h0,h1,h2));
        resPrefMap.put(r2, Arrays.asList(h0,h1));
        resPrefMap.put(r3, Arrays.asList(h0,h2));


        Map<Hospital, List<Resident>> hosPrefMap = new LinkedHashMap<>();
        hosPrefMap.put(h0, Arrays.asList(r3,r0,r1,r2));
        hosPrefMap.put(h1, Arrays.asList(r0,r2,r1));
        hosPrefMap.put(h2, Arrays.asList(r0,r1,r3));


        System.out.println("Residents that find H0 acceptable");
        residentList.stream()
                .filter(res -> resPrefMap.get(res).contains(h0))
                .filter(res -> resPrefMap.get(res).contains(h2))
                .forEach(System.out::println);

        System.out.println("Residents that find H2 acceptable");
        residentList.stream()
                .filter(res -> resPrefMap.get(res).contains(h2))
                .forEach(System.out::println);

        System.out.println("Hospitals that prefer R0 ");

        hosPrefMap.entrySet().stream()
                .filter(hos -> !hosPrefMap.get(hos).isEmpty() && hosPrefMap.get(hos).get(0).getName().equals(r0.getName()) )
                .forEach(System.out::println);

    }
}
