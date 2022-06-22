package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.CreatePlayers;

import java.util.*;

public class MyJava2 {

    // Здесь начинаются задачки из Jira

    public static void main(String[] args) {

        // Потом я это уберу
        /*Scanner in = new Scanner(System.in);
        System.out.print("Введите количество игроков: ");
        int play = in.nextInt();
        in.close();*/
        int play = 10;

        List<Player> plsFor = new ArrayList<>();
        for (int i = 0; i < play; i++) {
            plsFor.add(new Player(0, 0, 0, 0));
        }
        System.out.println("Размер листа в For: " + plsFor.size());

        List<Player> plsRec = new ArrayList<>();
        plsRec = recFunc(plsRec, play);
        System.out.println("Размер листа в Rec: " + plsRec.size());

        // Примерно то, что я хотел, но не создать лист с зарезервированным количеством элементов,
        // поэтому здесь присутствует побочный нулевой массив (заодно попробовал фильтр :) )
        Player[] pl = new Player[play];
        List<Player> plsStr = new ArrayList<>();
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> plsStr.add(new Player(0, 0, 0, 0)));
        System.out.println("Размер листа в Str: " + plsStr.size());
        pl = null;

        Team t1 = new Team(plsFor);
        Team t2 = new Team(plsRec);
        Team t3 = new Team(plsStr);

        // ========================================================
        int pls = 20; // Количество игроков
        int tms = 4; // Количество команд
        List<Player> playersList = new ArrayList<>();
        for (int i = 1; i <= pls; i++) {
            Random rand = new Random();
            playersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120), rand.nextFloat(0.6f, 1)));
        }

        // Понятное дело, что я это не использовал для 2000 игроков
        System.out.println("Посмотрим, что вышло:\n");
        for (Player l : playersList) {
            l.infoPlayer();
        }

        // Разбиение на команды, используя subList, где j - это количество человек в команде
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        for (int i = 0, j = pls / tms; i < pls; i += j) {
            teamsList.add(new Team(playersList.subList(i, i + j)));
        }

        System.out.println("Составы команд:");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print((i + 1) + ". ");
            teamsList.get(i).infoTeamId();
            System.out.println();
        }

        // Тест нового класса с созданием игроков
        CreatePlayers lst = new CreatePlayers();
        lst.createPlayersFor(10);
        lst.createPlayersRec(10);
        lst.createPlayersStream(10);
        CreatePlayers plsRand = new CreatePlayers();
        plsRand.createPlayersRandom(10);
        //System.out.println("Созданные игроки:");
        //plsRand.infoPlayer();
        // =======================================

        System.out.println("\nРассчитаем силу игроков на данный момент (с учётом стабильности): ");
        for (Player l : playersList) {
            System.out.println("Сила игрока " + l.id + " = " + l.playerPower());
        }

        System.out.println("\nРассчитаем силу команд на данный момент (с учётом стабильности): ");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print((i + 1) + ". " + teamsList.get(i).teamPower());
            System.out.println();
        }
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
