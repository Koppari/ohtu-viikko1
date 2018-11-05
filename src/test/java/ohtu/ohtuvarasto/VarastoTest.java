package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        varasto = new Varasto(-1, -1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVarastonTilavuudella0() {
        varasto = new Varasto(-1);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVaraston() {
        varasto = new Varasto(123, 123);
        assertEquals(123, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriAsettaaOikeanAlkusaldon() {
        varasto = new Varasto(123, 124);
        assertEquals(123, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nollaLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void LiikaaLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(9999);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void nollaOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(-1);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void liikaaOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);
        double saatuMaara = varasto.otaVarastosta(99999);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(2);

        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringOikein() {
        String haluttu = varasto.toString();
        assertEquals(haluttu, varasto.toString());
    }

}