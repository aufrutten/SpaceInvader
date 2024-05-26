import board.Board;
import units.SpaceEntity;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Path currentDir = Paths.get("").toAbsolutePath();

        System.out.println(currentDir.resolve("."));
    }
}
