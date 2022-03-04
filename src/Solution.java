import java.io.IOException;
import java.util.*;

/*
Enter your code here.
Create all the necessary classes and methods as per the requirements.
*/
class Map {
    public int size;
    public char [] [] array;
    public Position position;
    public Map (Scanner sc) throws InvalidMapException {
        size = sc.nextInt();
        array = new char [size] [size];
        if (size == 0) {
            throw new InvalidMapException("Map size can not be zero");
        }
        int i = 0; int j = 0;
        do {
            j = 0;
            do {
                try {
                    array [i] [j] = sc.next().charAt(0);
                }
                catch (NoSuchElementException e) {
                    throw new InvalidMapException("Not enough map elements");
                }
                if (array [i] [j] == 'P') {
                    position = new Position(j, i);
                }
                j++;
            } while(j < array.length);
            i++;
        } while (i < array.length);

    }
    public int getSize () {
        return size;
    }
    public char getValueAt (int x, int y) {
        return array [y] [x];
    }
    public void print () {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++){
                System.out.print(array [i] [j] + " ");
            }
            System.out.println();
        }
    }
}
class Position {
    public int x, y;

    public Position(int nextInt, int nextInt1) {
        this.x = nextInt;
        this.y = nextInt1;
    }

    public Position() {

    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position position) {
        if (this.x == position.getX() && this.y == position.getY())
            return true;
        else return false;
    }
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}
interface Player {
    public void moveRight();
    public void moveDown();
    public void moveLeft();
    public void moveUp();
    public void setMap(Map map);
    Position getPosition();
}
class MyPlayer implements Player{
    private Map map;
    private String commands;
    private int x, y;
    private int index;
    public Position position;

    public void moveRight() {
        Position position2 = new Position(position.getX() + 1, position.getY());
        if (position2.getX() != map.array.length && map.getValueAt(position.getX() + 1, position.getY()) != '1') {
            position.setX(position.getX() + 1);
        }
    }
    public void moveLeft() {
        Position position3 = new Position(position.getX() - 1, position.getY());
        if (position3.getX() != -1 && map.getValueAt(position.getX() - 1, position.getY()) != '1') {
            position.setX(position.getX() - 1);
        }
    }
    public void moveUp() {
        Position position4 = new Position(position.getX(), position.getY() - 1);
        if (position4.getY() != -1 && map.getValueAt(position.getX(), position.getY() - 1) != '1') {
            position.setY(position.getY() - 1);
        }
    }
    public void moveDown() {
        Position position5 = new Position(position.getX(), position.getY() + 1);
        if (position5.getY() != map.array.length && map.getValueAt(position.getX(), position.getY() + 1) != '1') {
            position.setY(position.getY() + 1);
        }
    }

    // привязывает игрока к карте
    public void setMap(Map map) {
        this.map = map;
        this.position = map.position;
    }
    // predel
    public Position getPosition() {
        Position position1 = position;
        return position1;
    }
}
class InvalidMapException extends Exception {
    InvalidMapException(String string) {
        super(string);
    }

}
class
Game {
    public Map map;
    public MyPlayer player;

    public Game(Map map) throws InvalidMapException {
        this.map = map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void addPlayer(Player player) {
        this.player = (MyPlayer) (player);
        this.player.setMap(map);
    }
}

/*
DO NOT MODIFY THIS PART!!!
Input and Output has been done for you.
Various conditions are meant to check individual classes separately.
You still need to implement completely all the necessary classes with their corresponding methods,
but the correctness for each class is checked individually.
In other words, you already get some partial points
for implementing some classes completely and correctly,
even if other classes are complete but still may not work properly.
*/
public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String className = input.nextLine();

        // Checking the implementation of the Position class
        if (className.equals("Position")) {
            Position p1 = new Position(input.nextInt(), input.nextInt());
            System.out.println(p1);
            p1.setX(5);
            System.out.println(p1.getX());

            Position p2 = new Position(5, 10);
            System.out.println(p1.equals(p2));
        }

        // Checking the implementation of the Map class
        else if (className.equals("Map")) {
            try {
                Map map = new Map(input);
                map.print();
                int size = map.getSize();
                System.out.println(size);
                System.out.println(map.getValueAt(size / 2, size / 2));
            } catch (Exception e) {
            }
        }

        // Checking the Player interface and the MyPlayer class
        else if (className.equals("Player")) {
            Player player = new MyPlayer();
            System.out.println(Player.class.isInterface());
            System.out.println(player instanceof Player);
            System.out.println(player instanceof MyPlayer);
        }

        // Checking the InvalidMapException class
        else if (className.equals("Exception")) {
            try {
                throw new InvalidMapException("Some message");
            } catch (InvalidMapException e) {
                System.out.println(e.getMessage());
            }
        }

        // Checking the Game class and all of its components
        else if (className.equals("Game")) {

            // Initialize player, map, and the game
            Player player = new MyPlayer();
            Game game = null;
            try {
                game = new Game(new Map(input));
            }
            catch (InvalidMapException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }
            game.addPlayer(player);

            // Make the player move based on the commands given in the input
            String moves = input.next();
            char move;
            for (int i = 0; i < moves.length(); i++) {
                move = moves.charAt(i);
                switch (move) {
                    case 'R':
                        player.moveRight();
                        break;
                    case 'L':
                        player.moveLeft();
                        break;
                    case 'U':
                        player.moveUp();
                        break;
                    case 'D':
                        player.moveDown();
                        break;
                }
            }

            // Determine the final position of the player after completing all the moves above
            Position playerPosition = player.getPosition();
            System.out.println("Player final position");
            System.out.println("Row: " + playerPosition.getY());
            System.out.println("Col: " + playerPosition.getX());

        }
    }
}