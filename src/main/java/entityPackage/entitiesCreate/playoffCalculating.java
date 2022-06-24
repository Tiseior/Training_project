package entityPackage.entitiesCreate;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.*;

public class playoffCalculating {

    public void createEmptyPlayersFor(List<Player> playersList, int players) {
        for (int i = 0; i < players; i++) {
            playersList.add(new Player(0, 0, 0, 0));
        }
    }

    public void createEmptyPlayersRec(List<Player> playersList, int players) {
        if (players > 0) {
            playersList.add(new Player(0, 0, 0, 0));
            createEmptyPlayersRec(playersList, --players);
        }
    }

    public void createEmptyPlayersStream(List<Player> playersList, int players) {
        Player[] pl = new Player[players];
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> playersList.add(new Player(0, 0, 0, 0)));
        pl = null;
    }

    public void createPlayersRandom(List<Player> playersList, int players) {
        for (int i = 1; i <= players; i++) {
            Random rand = new Random();
            playersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
    }

    public List<Player> createPlayersRandomly(int playersCount) {
        Random rand = new Random();
        List<Player> returnedPlayersList = new ArrayList<>();
        for (int i = 0; i < playersCount; i++) { //
            returnedPlayersList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
        return returnedPlayersList;
    }

    public void addRandomPlayersToList(List<Player> playerList, int playersCount) {
        Random rand = new Random();
        for (int i = 0; i < playersCount; i++) {
            playerList.add(new Player(i, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
    }

    public void createTeams(List<Team> teamsList, List<Player> playersList, int teamCount) {
        Collections.shuffle(playersList);
        for (int i = 0, j = playersList.size() / teamCount, ind = 1; i < playersList.size(); i += j, ind++) {
            teamsList.add(new Team(playersList.subList(i, i + j), ind));
        }
    }

    public List<Team> createTeamsByStandardCount(List<Player> playersList) {
        List<Team> teamsList = new ArrayList<>();
        Collections.shuffle(playersList);
        try {
            for (int i = 0, ind = 1; i < playersList.size(); i += Config.standardSizeOfTeam, ind++) {
                teamsList.add(new Team(playersList.subList(i, i + Config.standardSizeOfTeam), ind));
            }
        } catch (IndexOutOfBoundsException e) {
            //nothing
        }
        return teamsList;
    }

    public void getExpectedWinner(List<Team> teams) {
        while (teams.size() > 1) {
            float pw1 = teams.get(0).teamPower();
            float pw2 = teams.get(1).teamPower();
            System.out.println("Команда " + teams.get(0).id + " (Сила " + pw1 + ") vs Команда "
                    + teams.get(1).id + " (Сила " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("Победила команда " + teams.get(0).id);
                teams.add(teams.get(0));
            } else {
                System.out.println("Победила команда " + teams.get(1).id);
                teams.add(teams.get(1));
            }
            teams.remove(0);
            teams.remove(0);
            System.out.println("");
        }
        System.out.println("Победитель - команда " + teams.get(0).id);
    }

    public void maxPowerTeamNoStability(List<Team> teamsList) {
        int mx = 0;
        for (int i = 0; i < teamsList.size(); i++) {
            if (teamsList.get(i).teamPowerNoStability() > teamsList.get(mx).teamPowerNoStability()) {
                mx = i;
            }
        }
        System.out.println("\nСамая большая сила, без учёта стабильности, у команды " + teamsList.get(mx).id);
        System.out.println("");
    }

    public void infoPlayers(List<Player> playersList) {
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println("\tИдентификатор: " + playersList.get(i).id);
            System.out.println("\tКиллы к смертям: " + playersList.get(i).kd);
            System.out.println("\tКол-во урона за раунд: " + playersList.get(i).adr);
            System.out.println("\tСтабильность: " + playersList.get(i).stability + "\n");
        }
    }

    public void infoTeamsIdPlayers(List<Team> teamsList) {
        for (int i = 0; i < teamsList.size(); i++) {
            teamsList.get(i).infoTeamId();
            System.out.println();
        }
    }
}
