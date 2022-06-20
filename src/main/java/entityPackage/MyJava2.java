package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyJava2 {

    // Здесь начинаются задачки из Jira

    public static void main(String[] args) {

        // Потом я это уберу
        Scanner in = new Scanner(System.in);
        System.out.print("Введите количество игроков: ");
        int play = in.nextInt();
        in.close();

        List<Player> plsFor = new ArrayList<>();
        for (int i = 0; i < play; i++) {
            plsFor.add(new Player(0, 0, 0, 0));
        }
        System.out.println("Размер листа в For: " + plsFor.size());

        List<Player> plsRec = new ArrayList<>();
        plsRec = recFunc(plsRec, play);
        System.out.println("Размер листа в Rec: " + plsRec.size());

        List<Player> plsStr = new ArrayList<>();

        System.out.println("Размер листа в Str: " + plsStr.size());

        Team t1 = new Team(plsFor);
        Team t2 = new Team(plsRec);
        Team t3 = new Team(plsStr);
    }

    public static List<Player> recFunc(List<Player> lst, int i) {
        if (i > 0) {
            lst.add(new Player(0, 0, 0, 0));
            return recFunc(lst, --i);
        } else {
            return lst;
        }
    }
}
