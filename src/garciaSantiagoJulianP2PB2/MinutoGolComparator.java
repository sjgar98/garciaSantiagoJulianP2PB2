package garciaSantiagoJulianP2PB2;

import java.util.Comparator;

public class MinutoGolComparator implements Comparator<Gol> {
    @Override
    public int compare(Gol gol1, Gol gol2) {
        return gol1.getMinuto() - (gol2.getMinuto());
    }
}
