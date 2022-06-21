package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;

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
            playersList.add(new Player(i, randomFloat(0.1f, 2), randomFloat(40, 120), randomFloat(0.6f, 1)));
        }

        // Понятное дело, что я это не использовал для 2000 игроков
        System.out.println("Посмотрим, что вышло:\n");
        for (Player l : playersList) {
            l.infoPlayer();
        }

        // Первый способ, который пришёл на ум.
        // Рандомно забираю игрока из списка игроков и засовываю его в список с командой, удаляя его из списка игроков.
        // Когда в команде набирается 5 человек, то засовываю эту команду в объект Team, затем чищу список с командой.
        /*List<Team> teamsList = new ArrayList<>();
        List<Player> teamOne = new ArrayList<>();
        int j = 0;
        for (int i = pls; i > 0; i--) {
            Random ri = new Random();
            int n = ri.nextInt(i);
            teamOne.add(playersList.get(n));
            playersList.remove(n);
            if (teamOne.size()== 5) {
                teamsList.add(new Team(teamOne));
                teamOne.clear();
            }
        }
        // Способ не увенчался успехом, так как я удаляю содержимое списка, где хранились игроки и данные стираются

        System.out.println("Составы команд:\n");
        for (int i = 0; i < teamsList.size(); i++) {
            System.out.print((i + 1) + ". ");
            teamsList.get(i).infoTeamId();
            System.out.println();
        }*/

        // Идея второго способа: перебирается список с игроками в рандомном порядке, когда рандомных игроков в конце
        // списка достаточно для формирования команды, то они засовываются в объект Team через subList, но появляется
        // непонятная мне ошибка
        /*List<Team> teamsList = new ArrayList<>();
        for (int i = pls, j = 0; i > 0; i--, j++) {
            Random ri = new Random();
            int n = ri.nextInt(i);
            playersList.add(playersList.get(n));
            playersList.remove(n);
            if (j == (pls / tms)) {
                teamsList.add(new Team(playersList.subList(pls - j, pls - 1)));
                j = 0;
            }
        }*/

        // Это просто тест вывода id игроков в команде
        List<Player> test = new ArrayList<>();
        test.add(new Player(1, 2, 3, 4));
        test.add(new Player(2, 3, 4, 5));
        Team tst = new Team(test);
        System.out.print("Составы команды: ");
        tst.infoTeamId();
        System.out.println();
    }

    public static List<Player> recFunc(List<Player> lst, int i) {
        if (i > 0) {
            lst.add(new Player(0, 0, 0, 0));
            return recFunc(lst, --i);
        } else {
            return lst;
        }
    }

    public static float randomFloat(float min, float max) {
        //float rd = (float) (Math.random() * ((max - min) + 1)) + min;
        //float rd = min + (float) (Math.random() * max);
        Random rd = new Random();
        return rd.nextFloat(max - min) + min;
    }
}
