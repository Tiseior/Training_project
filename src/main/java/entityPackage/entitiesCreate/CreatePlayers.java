package entityPackage.entitiesCreate;

import entityPackage.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreatePlayers {

    public Player player;
    public List<Player> lst = new ArrayList<>();

    public void createPlayersFor(int players) {
        for (int i = 0; i < players; i++) {
            lst.add(new Player(0, 0, 0, 0));
        }
    }

    public void createPlayersRec(int players) {
        if (players > 0) {
            lst.add(new Player(0, 0, 0, 0));
            createPlayersRec(--players);
        }
    }

    public void createPlayersRandom(int players) {
        for (int i = 1; i <= players; i++) {
            Random rand = new Random();
            lst.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120), rand.nextFloat(0.6f, 1)));
        }
    }

    public void createPlayersStream(int players) {
        Player[] pl = new Player[players];
        List<Player> plsStr = new ArrayList<>();
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> plsStr.add(new Player(0, 0, 0, 0)));
        pl = null;
    }

    public void infoPlayer() {
        for (int i=0; i<lst.size();i++){
            System.out.println("\tИдентификатор: " + lst.get(i).id);
            System.out.println("\tКиллы к смертям: " + lst.get(i).kd);
            System.out.println("\tКол-во урона за раунд: " + lst.get(i).adr);
            System.out.println("\tСтабильность: " + lst.get(i).stability + "\n");
        }
    }
}
