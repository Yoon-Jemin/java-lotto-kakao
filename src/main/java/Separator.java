import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Separator {

    private Set<String> separators;

    public Separator(Set<String> separators) {
        this.separators = separators;
    }

    public Set<String> getSeparators() {
        return new HashSet<>(List.of(","));
    }
}
