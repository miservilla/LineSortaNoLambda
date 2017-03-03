import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
/**
 * @author Michael Servilla
 * @version date 2017-03-02
 */
public class LineSorter implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length() - o2.length();
    }
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("testinput.txt"); //for source test file
        Path target = Paths.get("testoutput.txt"); //for target test file
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(source))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        Collections.sort(lines);
        Collections.sort(lines, new LineSorter());
        try (BufferedWriter writer = Files.newBufferedWriter(target))
        {
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                writer.write(s, 0, s.length());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}