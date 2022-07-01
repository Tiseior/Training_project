package entityPackage;

import entityPackage.entities.Player;
import entityPackage.entities.Team;
import entityPackage.entitiesCreate.PlayoffCalculating;

import java.util.ArrayList;
import java.util.List;

public class MyJava4 {

    // Усиленный расчёт побед команд

    public static void main(String[] args) {

        int players = 32;
        PlayoffCalculating game = new PlayoffCalculating();
        List<Player> playersList = game.createPlayersRandom(players);
        game.infoPlayers(playersList);
        System.out.println("Уменьшили стабильность\n");
        game.reduceStability(playersList);
        game.infoPlayers(playersList);
        System.out.println("Увеличили стабильность\n");
        game.increaseStability(playersList);
        game.infoPlayers(playersList);
        List<Team> teamsList = game.createTeamsByStandardCount(playersList);
        game.infoTeamsIdPlayers(teamsList);

        System.out.println("Характеристики игроков команды 1\n");
        game.infoPlayers(teamsList.get(0).listPlayer);
        System.out.println("Уменьшили стабильность\n");
        game.reduceStability(teamsList.get(0).listPlayer);
        game.infoPlayers(teamsList.get(0).listPlayer);

        System.out.println("Характеристики игроков команды 2\n");
        game.infoPlayers(teamsList.get(1).listPlayer);
        System.out.println("Увеличили стабильность\n");
        game.increaseStability(teamsList.get(1).listPlayer);
        game.infoPlayers(teamsList.get(1).listPlayer);

        System.out.println("Старый состав команд:\n");
        game.infoTeamsIdPlayers(teamsList);
        System.out.println("\nНовый состав команд:\n");
        teamsList = game.redistributionPlayers(teamsList);
        game.infoTeamsIdPlayers(teamsList);

        System.out.println("Новый тест\n");
        teamsList = game.redistributionPlayers2(teamsList);
        game.infoTeamsIdPlayers(teamsList);

        playersList.clear();
        teamsList.clear();
        players = 80; // Количество игроков
        int years = 2; // Количество лет
        System.out.println("\nНачнём усложнённую игру\n");
        List<Team> winners = new ArrayList<>();
        playersList = game.createPlayersRandom(players);
        teamsList = game.createTeamsByStandardCount(playersList);
        for (int i = 0; i < years; i++) {
            System.out.println("\n\tГод " + (i+1) + "\n");
            System.out.println("Составы команд:\n");
            game.infoTeamsIdPlayers(teamsList);
            System.out.println("\nСезон зима-весна-лето\n");
            winners.add(game.getExpectedWinnerHard(teamsList));
            System.out.println("\nИзменяем составы команд:\n");
            teamsList = game.redistributionPlayers(teamsList);
            game.infoTeamsIdPlayers(teamsList);
            System.out.println("\nСезон лето-осень-зима\n");
            winners.add(game.getExpectedWinnerHard(teamsList));
            teamsList = game.redistributionPlayers2(teamsList);
        }
        System.out.println("\nСписок команд-победителей:");
        for (Team team : winners) {
            System.out.println("\tКоманда " + team.id);
        }
    }
}
