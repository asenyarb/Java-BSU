import java.io.IOException;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        SegmentsUtils su = new SegmentsUtils();
        Map<Pair<Segment>, Point> result = su.findIntersections();
        for (var key: result.keySet()){
            System.out.println(key + ": " + result.get(key));
        }
    }
}
