package entityPackage.entities;

import java.util.Random;

public class Player {
    public int id; //идентификатор игрока
    public float kd; //отношение киллов к смертям (от 0,1 до 2)
    public float adr; //количество урона за раунд (от 40 до 120)
    public float stability; //от 0,6 до 1 (но как бы проценты)

    public Player(int id, float kd, float adr, float stability) {
        this.id = id;
        this.kd = kd;
        this.adr = adr;
        this.stability = stability;
    }

    public float playerPower() {
        Random st = new Random();
        float power = (kd / Config.avgKd * 50) + (adr / Config.avgAdr * 50);
        if (st.nextFloat(0, 1) > stability) {
            power /= 2;
        }
        return power;
    }

    // Стоит ли закидывать расчёты силы в PlayoffCalculating?
    public float playerPowerHard() {
        Random st = new Random();
        float power = (kd / Config.avgKd * 50) + (adr / Config.avgAdr * 50);
        if (st.nextFloat(0, 1) > stability) {
            power = 0;
        }
        return power;
    }

    // Информация об игроке
    public void infoPlayer() {
        System.out.println("\tИдентификатор: " + id);
        System.out.println("\tКиллы к смертям: " + kd);
        System.out.println("\tКол-во урона за раунд: " + adr);
        System.out.println("\tСтабильность: " + stability + "\n");
    }
}
