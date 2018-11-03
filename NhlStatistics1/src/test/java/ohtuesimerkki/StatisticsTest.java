package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.stream.Collectors;

public class StatisticsTest {

    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void hakuToimii() {
        String nimi = stats.search("Kurri").getName();
        assertEquals(nimi, "Kurri");
    }

    @Test
    public void hakuToimiiOlemattomalla() {
        Player p = stats.search("Asd");
        assertEquals(p, null);
    }

    @Test
    public void tiimiHakuToimii() {
        List<Player> pelaajat = stats.team("EDM");
        List<Player> halutut = readerStub.getPlayers().stream()
                .filter(p -> "EDM".equals(p.getTeam())).collect(Collectors.toList());
        assertEquals(pelaajat.get(0).getTeam(), halutut.get(0).getTeam());
    }

    @Test
    public void topScorersToimii() {
        assertEquals(stats.topScorers(4).size(), 5);
    }

}
