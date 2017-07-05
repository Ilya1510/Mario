package application.Levels;

import application.MyGraphic.Platforms.Fountain;
import application.MyGraphic.Platforms.HeroMario;
import application.MyGraphic.Platforms.Platform;
import application.MyGraphic.Platforms.PlatformRect;

import java.io.File;
import java.util.Scanner;

import static application.Application.WINDOW_H;
import static application.Application.WINDOW_W;

/**
 * Created by ДОМ on 22.06.2016.
 */

public class Level {
    public Platform[] plato;
    public int endLevelX;
    public int endLevelY;
    public static Level createLevel(File f) throws java.io.FileNotFoundException {
        Scanner scanner = new Scanner(f);
        Level res = new Level();
        res.endLevelX = scanner.nextInt();
        res.endLevelY = scanner.nextInt();
        int count = scanner.nextInt();
        res.plato = new Platform[count + 2];
        count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int data[] = new int[4];
            for (int j = 0; j < 4; j++) {
                data[j] = scanner.nextInt();
            }
            PlatformRect p = new PlatformRect(data[0], data[1], data[2], data[3]);
            res.plato[i] = p;
        }
        int countFountain = scanner.nextInt();
        for (int i = 0; i < countFountain; i++) {
            int data[] = new int[4];
            for (int j = 0; j < 4; j++) {
                data[j] = scanner.nextInt();
            }
            Fountain p = new Fountain(data[0], data[1], data[2], data[3]);
            res.plato[i + count] = p;
        }
        count += countFountain;
        PlatformRect board = new PlatformRect(0, res.endLevelY, res.endLevelX, res.endLevelY + 50);
        res.plato[count] = board;
        board = new PlatformRect(res.endLevelX, 0, res.endLevelX + 50, res.endLevelY);
        res.plato[count + 1] = board;
        return res;
    }
    public boolean pointInPlatform(int x, int y) {
        for (int i = 0; i < plato.length; i++) {
            if (plato[i].pointInPlatform(x, y)) {
                return true;
            }
        }
        return false;
    }
    public boolean marioSectPlatform(HeroMario mario) {
        for (int i = 0; i < plato.length; i++) {
            if (plato[i].marioSectPlatform(mario)) {
                return true;
            }
        }
        return false;
    }
}
