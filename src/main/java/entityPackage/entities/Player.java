package entityPackage.entities;

import java.util.Random;

public class Player {
    public int id; //идентификатор игрока
    public float kd; //отношение киллов к смертям (от 0,1 до 2)
    public float adr; //количество урона за раунд (от 40 до 120)
    public float stability; //от 0,6 до 1 (но как бы проценты)

    final float AVG_KD = 0.95f;
    final float AVG_ADR = 74;

    public Player(int id, float kd, float adr, float stability) {
        this.id = id;
        this.kd = kd;
        this.adr = adr;
        this.stability = stability;
    }

    public float playerPower() {
        Random st = new Random();
        float pw = (kd / AVG_KD * 50) + (adr / AVG_ADR * 50);
        if (st.nextFloat(0, 1) > stability) {
            pw /= 2;
        }
        return pw;
    }

    // Информация об игроке
    public void infoPlayer() {
        System.out.println("\tИдентификатор: " + id);
        System.out.println("\tКиллы к смертям: " + kd);
        System.out.println("\tКол-во урона за раунд: " + adr);
        System.out.println("\tСтабильность: " + stability + "\n");
    }
}
