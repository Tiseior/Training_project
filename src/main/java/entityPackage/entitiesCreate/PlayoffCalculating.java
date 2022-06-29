package entityPackage.entitiesCreate;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.*;

public class PlayoffCalculating {

    // Создание игроков через цикл For с нулевыми характеристиками
    public List<Player> createEmptyPlayersFor(int playersCount) {
        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < playersCount; i++) {
            playerList.add(new Player(0, 0, 0, 0));
        }
        return playerList;
    }

    // Создание игроков через рекурсию с нулевыми характеристиками
    public void createEmptyPlayersRec(List<Player> playersList, int playersCount) {
        if (playersCount > 0) {
            playersList.add(new Player(0, 0, 0, 0));
            createEmptyPlayersRec(playersList, --playersCount);
        }
    }

    // Создание игроков через stream с нулевыми характеристиками
    public List<Player> createEmptyPlayersStream(int players) {
        Player[] pl = new Player[players];
        List<Player> playersList = new ArrayList<>();
        Arrays.stream(pl).filter(el -> el == null).forEach(obj -> playersList.add(new Player(0, 0, 0, 0)));
        pl = null;
        return playersList;
    }

    // Заполнение характеристик у игроков стандартными рандомными значениями
    public void fillPlayersStandardCharacteristics(List<Player> playerList) {
        Random rand = new Random();
        int id = 1;
        for (Player player : playerList) {
            player.id = id;
            player.kd = rand.nextFloat(0.1f, 2);
            player.adr = rand.nextFloat(40, 120);
            player.stability = rand.nextFloat(0.6f, 1);
            id++;
        }
    }

    // Создание игроков со стандартными рандомными характеристиками
    public List<Player> createPlayersRandom(int playersCount) {
        List<Player> playersList = new ArrayList<>();
        Random rand = new Random();
        for (int id = 1; id <= playersCount; id++) {
            playersList.add(new Player(id, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
        return playersList;
    }

    // Добавление новых игроков со стандартными рандомными характеристиками
    public void addRandomPlayersToList(List<Player> playerList, int playersCount) {
        Random rand = new Random();
        int size = playerList.size();
        for (int id = size + 1; id <= size + playersCount; id++) {
            playerList.add(new Player(id, rand.nextFloat(0.1f, 2), rand.nextFloat(40, 120),
                    rand.nextFloat(0.6f, 1)));
        }
    }

    // Удаление игрока по id
    public void deletePlayer(List<Player> playerList, int id) {
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).id == id) {
                playerList.remove(i);
                break;
            }
        }
    }

    // Разбиение на команды, используя subList, где j - это количество человек в команде
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

    // Вычисление самой сильной команды без учёта стабильности игроков
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

    // Игра для выявления победившей команды
    public void getExpectedWinner(List<Team> teams) {
        Collections.shuffle(teams);
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

    // Уменьшить стабильность игроков
    public void reduceStability(List<Player> playersList) {
        for (Player player : playersList) {
            player.stability -= 0.05f;
            if (player.stability < 0.6f) {
                player.stability = 0.6f;
            }
        }
    }

    // Увеличить стабильность игроков
    public void increaseStability(List<Player> playersList) {
        for (Player player : playersList) {
            player.stability += 0.02f;
            if (player.stability > 1f) {
                player.stability = 1f;
            }
        }
    }

    // Перераспределение игроков в конце сезона
    // Метод не дописан, так как возможна ситуация, что будут удаляться игроки из пустой команды
    // Так же я не могу вывести id игроков в командах, это нужно исправлять
    public void redistributionPlayers(List<Team> teamsList) {
        Random id = new Random();
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            int teamId = id.nextInt(0, teamsList.size() - 1);
            System.out.println("Команда: " + teamId);
            int playerId = id.nextInt(0, teamsList.get(teamId).listPlayer.size() - 1);
            System.out.println("Игрок: " + playerId);
            System.out.println("Тот самый игрок " + teamsList.get(teamId).listPlayer.get(playerId).id);
            players.add(teamsList.get(teamId).listPlayer.get(playerId));
            teamsList.get(teamId).listPlayer.remove(playerId);
        }
        //System.out.println("Команды без игроков\n");
        //infoTeamsIdPlayers(teamsList);
        System.out.println("Исключённые игроки\n");
        infoPlayers(players);
    }

    // Вывод характеристик всех игроков
    public void infoPlayers(List<Player> playersList) {
        for (int i = 0; i < playersList.size(); i++) {
            System.out.println("\tИдентификатор: " + playersList.get(i).id);
            System.out.println("\tКиллы к смертям: " + playersList.get(i).kd);
            System.out.println("\tКол-во урона за раунд: " + playersList.get(i).adr);
            System.out.println("\tСтабильность: " + playersList.get(i).stability + "\n");
        }
    }

    // Вывод id игроков и команд, в которых они состоят
    public void infoTeamsIdPlayers(List<Team> teamsList) {
        for (int i = 0; i < teamsList.size(); i++) {
            teamsList.get(i).infoTeamId();
            System.out.println();
        }
    }
}
