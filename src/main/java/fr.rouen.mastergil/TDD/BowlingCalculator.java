package fr.rouen.mastergil.TDD;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by rudy on 12/03/17.
 */
public class BowlingCalculator {
    //See http://warwickbowling.50webs.com/calculator.html

    public int score(String feuilleDeScore) {
        final List<Frame> frames = extractFrameFromString(feuilleDeScore);
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
        }
        return score;
    }


    List<Frame> extractFrameFromString(String feuilleDeScore) {
        final List<Frame> frames = new ArrayList<Frame>();
        final String[] split = feuilleDeScore.split(";");
        for (int i = 0; i < split.length; i++) {
            frames.add(new Frame(split[i]));
        }
        return frames;
    }


}