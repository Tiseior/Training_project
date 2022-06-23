package entityPackage;

import entityPackage.entities.AbstractObject;
import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.CreateAbstractObject;
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

        Team t1 = new Team(plsFor, 1);
        Team t2 = new Team(plsRec, 2);
        Team t3 = new Team(plsStr, 3);

        // ========================================================
        int pls = 2000; // Количество игроков
        int tms = 400; // Количество команд                                     // !!!!!

        List<Player> playersList = new ArrayList<>();
        for (int i = 1; i <= pls; i++) {
            Random rand = new Random();
            playersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120), rand.nextFloat(0.6f, 1)));
        }

        // Понятное дело, что я это не использовал для 2000 игроков
        /*System.out.println("Характеристики игроков:\n");
        for (Player l : playersList) {
            l.infoPlayer();
        }*/

        // Разбиение на команды, используя subList, где j - это количество человек в команде
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        for (int i = 0, j = pls / tms, ind = 1; i < pls; i += j, ind++) {
            teamsList.add(new Team(playersList.subList(i, i + j), ind));
        }

        /*System.out.println("Составы команд:");
        for (int i = 0; i < teamsList.size(); i++) {
            teamsList.get(i).infoTeamId();
            System.out.println();
        }*/

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

        /*System.out.println("\nРассчитаем силу игроков на данный момент (с учётом стабильности): ");
        for (Player l : playersList) {
            System.out.println("Сила игрока " + l.id + " = " + l.playerPower());
        }

        System.out.println("\nРассчитаем силу команд на данный момент (с учётом стабильности): ");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print(teamsList.get(i).id + ". " + teamsList.get(i).teamPower());
            System.out.println();
        }*/

        // =============================================================================
        Collections.shuffle(teamsList);
        System.out.print("\nСписок команд: ");
        int mx = 0;
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print(teamsList.get(i).id + " ");
            if (teamsList.get(i).teamPowerNoStability() > teamsList.get(mx).teamPowerNoStability()) {
                mx = i;
            }
        }
        System.out.println("\nПредполагаемый победитель - команда " + teamsList.get(mx).id);
        System.out.println("\n\tНачинаем игру\n");
        while (teamsList.size() > 1) {
            float pw1 = teamsList.get(0).teamPower();
            float pw2 = teamsList.get(1).teamPower();
            System.out.println("Команда " + teamsList.get(0).id + " (Сила " + pw1 + ") vs Команда "
                               + teamsList.get(1).id + " (Сила " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("Победила команда " + teamsList.get(0).id);
                teamsList.add(teamsList.get(0));
            } else {
                System.out.println("Победила команда " + teamsList.get(1).id);
                teamsList.add(teamsList.get(1));
            }
            teamsList.remove(0);
            teamsList.remove(0);
            System.out.println("");
        }
        System.out.println("Победитель - команда " + teamsList.get(0).id);

        CreateAbstractObject createAbstractObject = new CreateAbstractObject();
        List<AbstractObject> list = createAbstractObject.createListOfAbstractObjects();
        // Влияние стабильности игроков на силу команды
        /*System.out.println("Исследуется команда победитель");
        for (int i = 1; i <= 20; i++) {
            System.out.println("Тест " + i + ", сила: " + teamsList.get(0).teamPower());
        }*/
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
