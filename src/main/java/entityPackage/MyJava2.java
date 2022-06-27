package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entities.TeamList;
import entityPackage.entitiesCreate.PlayoffCalculating;

import java.util.*;

public class MyJava2 {

    // Здесь начинаются задачки из Jira

    public static void main(String[] args) {

        // Ввод с клавиатуры
        /*Scanner in = new Scanner(System.in);
        System.out.print("Введите количество игроков: ");
        int play = in.nextInt();
        in.close();*/
        int players = 10;

        System.out.println("\nТестирование нового класса\n");
        long start, finish;
        players = 8000;
        PlayoffCalculating game = new PlayoffCalculating();
        // Создание пустых игроков
        start = System.nanoTime();
        List<Player> playFor = game.createEmptyPlayersFor(players);
        finish = System.nanoTime();
        System.out.println("For создал " + playFor.size() + " игроков за " + (finish - start) + " нс");
        List<Player> playRec = new ArrayList<>();
        start = System.nanoTime();
        game.createEmptyPlayersRec(playRec, players); // Рекурсия валится на 9000 игроков
        finish = System.nanoTime();
        System.out.println("Рекурсия создала " + playRec.size() + " игроков за " + (finish - start) + " нс");
        start = System.nanoTime();
        List<Player> playStream = game.createEmptyPlayersStream(players);
        finish = System.nanoTime();
        System.out.println("Stream создал " + playStream.size() + " игроков за " + (finish - start) + " нс");

        players = 5;
        // Работа с игроками
        playFor.clear();
        System.out.println("\nПустые игроки:\n");
        playFor = game.createEmptyPlayersFor(players);
        game.infoPlayers(playFor);
        System.out.println("Заполненные игроки:\n");
        game.fillPlayersStandardCharacteristics(playFor);
        game.infoPlayers(playFor);
        System.out.println("Добавим 5 игроков:\n");
        game.addRandomPlayersToList(playFor, 5);
        game.infoPlayers(playFor);
        System.out.println("Удалим игроков с id 1, 3, 7:\n");
        game.deletePlayer(playFor, 1);
        game.deletePlayer(playFor, 3);
        game.deletePlayer(playFor, 7);
        game.infoPlayers(playFor);

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

        // Создание игроков и команд
        players = 20;
        List<Player> playersList = game.createPlayersRandom(players);
        System.out.println("Создано игроков: " + playersList.size());
        List<Team> teamsList = game.createTeamsByStandardCount(playersList);
        System.out.println("Составы команд:\n");
        game.infoTeamsIdPlayers(teamsList);
        game.maxPowerTeamNoStability(teamsList);
        System.out.println("Игра начинается\n");
        game.getExpectedWinner(teamsList);
    }
}
