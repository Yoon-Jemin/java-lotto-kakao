import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Separator {

    private Set<String> separators;

    public Separator() {
        this.separators = new HashSet<>(List.of(",", ":"));
    }

    public Set<String> getSeparators() {
        return this.separators;
    }

    public void addSeparator(String separator) {
        this.separators.add(separator);
    }
}
