package fr.rouen.mastergil.TDD;


import org.junit.Before;

/**
 * Created by rudy on 12/03/17.
 */
public class BowlingCalculatorTest {


    /**
     * Exemple de tests :
     * <p/>
     * #1 : score avec que des valeurs entières en frame 1
     * #2 : score avec que des valeurs entières en frame 1 et frame 2
     * #3 : score avec que des valeurs entières en frame 1 et frame 2 et 1 spare
     * #4 : score avec que des valeurs entières en frame 1 et frame 2 et 2 spare
     * #5 : score avec que des valeurs entières en frame 1 et frame 2 et 1 strike
     * ...
     * autre exemple :
     * <p/>
     * #1 : score avec que des valeurs à 1 en frame 1
     * #2 : score avec que des valeurs à 2 en frame 1
     * #3 : score avec que des valeurs à 1 en frame 1 et frame 2
     * #4 : score avec que des valeurs à 2 en frame 1 et frame 2
     * #5 : score avec que des valeurs à 2 en frame 1 et frame 2 et 1 spare
     * <p/>
     * S'inspirer des presets du calculator http://warwickbowling.50webs.com/calculator.html APRES les cas "basiques"
     */

    private BowlingCalculator bowlingCalculator;

    @Before
    public void setUp() throws Exception {
        bowlingCalculator = new BowlingCalculator();
    }


}