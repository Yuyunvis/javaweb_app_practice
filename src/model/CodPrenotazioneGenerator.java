package model;

import java.util.Random;

public class CodPrenotazioneGenerator {

    private Random random;

    public static CodPrenotazioneGenerator instance;
    public static CodPrenotazioneGenerator getInstance() {
        if (instance == null) instance = new CodPrenotazioneGenerator();
        return instance;
    }

    private CodPrenotazioneGenerator() {
        random = new Random();
    }

    public int generaCodice() {
        return random.nextInt(10000);
    }

}
