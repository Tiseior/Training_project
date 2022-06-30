package entityPackage.entitiesCreate;

import entityPackage.entities.Config;
import entityPackage.entities.Player;
import entityPackage.entities.Team;

import java.util.*;
import java.util.stream.Collectors;

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
        teamsList.stream().forEach(e -> {
            e.listPlayer.sort(Comparator.comparingInt(i -> i.id));
        });
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
    // Один из способов перераспределения. Он сделан так, что я расформировываю все команды и засовываю игроков
    // в один список. Потом рандомом переношу игроков в другой список, а в основном зануляю их.
    // Встряхиваю побочный список и бегаю по основному, в поисках нулевых игроков, заменив их на игрока с индексом 0 из
    // побочного списка, а затем удаляю его из побочного. В конце собираю новые команды, используя метод для построения
    // команд без рандомирования списка игроков (возможно этот метод избыточен).
    // Придумал ещё один способ, где рандомно выбирается номер команды и индекс игрока в этой команде. Игрок переносится
    // в побочный список и удаляется из команды. Потом побочный список встряхивается, и пустые места заполняются игроками
    // из него. Но возникла проблема с удалением игроков из списка с командами, поэтому сделал то, что сделал.
    public void redistributionPlayers(List<Team> teamsList) {
        List<Player> players = teamsList.stream().flatMap(e -> e.listPlayer.stream()).collect(Collectors.toList());
        Random id = new Random();
        List<Player> redistributedPlayers = new ArrayList<>();
        int playerId = 0;//id.nextInt(0, players.size() - 1);
        Player zero = new Player(0, 0, 0, 0);
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            while (players.get(playerId) == zero) {
                playerId = id.nextInt(0, players.size() - 1);
            }
            redistributedPlayers.add(players.get(playerId));
            players.set(playerId, zero);
        }
        Collections.shuffle(redistributedPlayers);
        for (int i = 0; i < Config.redistributePlayersCount; i++) {
            playerId = players.indexOf(zero);
            players.set(playerId, redistributedPlayers.get(0));
            redistributedPlayers.remove(0);
        }
        teamsList = createTeamsByStandardCountNoRandom(players);
        /*teamsList.stream().forEach(e -> {
            e.listPlayer.sort(Comparator.comparingInt(i -> i.id));
        });*/
        //return teamsList; // Не знаю, стоит ли выводить этот список
    }

    // Создание команд без перемешивания списка с игроками
    private List<Team> createTeamsByStandardCountNoRandom(List<Player> playersList) {
        List<Team> teamsList = new ArrayList<>();
        try {
            for (int i = 0, ind = 1; i < playersList.size(); i += Config.standardSizeOfTeam, ind++) {
                teamsList.add(new Team(playersList.subList(i, i + Config.standardSizeOfTeam), ind));
            }
        } catch (IndexOutOfBoundsException e) {
            //nothing
        }
        return teamsList;
    }

    // Сложная игра для выявления победившей команды
    public Team getExpectedWinnerHard(List<Team> teams) {
        List<Team> teamsThisSeason = teams;
        Collections.shuffle(teamsThisSeason);
        while (teamsThisSeason.size() > 1) {
            float pw1 = teamsThisSeason.get(0).teamPowerHard();
            float pw2 = teamsThisSeason.get(1).teamPowerHard();
            System.out.println("Команда " + teamsThisSeason.get(0).id + " (Сила " + pw1 + ") vs Команда "
                    + teamsThisSeason.get(1).id + " (Сила " + pw2 + ")");
            if (pw1 > pw2) {
                System.out.println("Победила команда " + teamsThisSeason.get(0).id);
                teamsThisSeason.add(teamsThisSeason.get(0));
                if (teamsThisSeason.get(0).teamPowerNoStability() < teamsThisSeason.get(1).teamPowerNoStability()) {
                    System.out.println("Команда " + teamsThisSeason.get(1).id + " штрафуется");
                    System.out.println("Сила 1 " + teamsThisSeason.get(0).teamPowerNoStability() + " Сила 2 " + teamsThisSeason.get(1).teamPowerNoStability());
                    reduceStability(teamsThisSeason.get(1).listPlayer);
                    increaseStability(teamsThisSeason.get(0).listPlayer);
                }
            } else {
                System.out.println("Победила команда " + teamsThisSeason.get(1).id);
                teamsThisSeason.add(teamsThisSeason.get(1));
                if (teamsThisSeason.get(0).teamPowerNoStability() > teamsThisSeason.get(1).teamPowerNoStability()) {
                    System.out.println("Команда " + teamsThisSeason.get(0).id + " штрафуется");
                    System.out.println("Сила 1 " + teamsThisSeason.get(0).teamPowerNoStability() + " Сила 2 " + teamsThisSeason.get(1).teamPowerNoStability());
                    reduceStability(teamsThisSeason.get(0).listPlayer);
                    increaseStability(teamsThisSeason.get(1).listPlayer);
                }
            }
            teamsThisSeason.remove(0);
            teamsThisSeason.remove(0);
            System.out.println("");
        }
        System.out.println("Победитель этого сезона - команда " + teamsThisSeason.get(0).id);
        return teamsThisSeason.get(0);
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
