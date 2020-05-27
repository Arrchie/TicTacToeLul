package sample;

import java.util.Arrays;
import java.util.Random;

public class Game {

    private String[][] gameBoard = new String[][] {{"", "", ""}, {"", "", ""}, {"", "", ""}};
    private Player player1, player2, currentPlayer;
    private int moves = 0;
    private boolean gameActive = true;
    private Controller controller;
    private String s1, s2, s3;

    public Game(Controller controller) {
        this.controller = controller;
    }

    public void restart() {
        gameBoard = new String[][] {{"", "", ""}, {"", "", ""}, {"", "", ""}};
        gameActive = true;
        moves = 0;
        controller.restart();
        start();
    }

    public void setSettings(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void start() {
        player1 = new Player(Player.PLAYER.X, s2.equals("Play vs AI") && s1.equals("Play as O"));
        player2 = new Player(Player.PLAYER.O, s2.equals("Play vs AI") && s1.equals("Play as X"));
        currentPlayer = s3.equals("X starts") ? player1 : player2;
        playMove(currentPlayer);
    }

    private void playMove(Player player) {
        moves++;
        if(checkWin(otherPlayer(player))) {
            end(otherPlayer(player), true);
            return;
        }
        if(moves == 10) {
            end(player, false);
        }
        if (player.isBot()) {
            makeBotMove(player);
        }
    }

    private Player otherPlayer(Player plr) {
        return plr == player1 ? player2 : player1;
    }

    private boolean checkWin(Player player) {
        if((gameBoard[0][0].equals(player.getIcon())
            && gameBoard[0][1].equals(player.getIcon())
            && gameBoard[0][2].equals(player.getIcon()))
        ||(gameBoard[1][0].equals(player.getIcon())
            && gameBoard[1][1].equals(player.getIcon())
            && gameBoard[1][2].equals(player.getIcon()))
        ||(gameBoard[2][0].equals(player.getIcon())
            && gameBoard[2][1].equals(player.getIcon())
            && gameBoard[2][2].equals(player.getIcon()))
        ||(gameBoard[0][0].equals(player.getIcon())
            && gameBoard[1][0].equals(player.getIcon())
            && gameBoard[2][0].equals(player.getIcon()))
        ||(gameBoard[0][1].equals(player.getIcon())
            && gameBoard[1][1].equals(player.getIcon())
            && gameBoard[2][1].equals(player.getIcon()))
        ||(gameBoard[0][2].equals(player.getIcon())
            && gameBoard[1][2].equals(player.getIcon())
            && gameBoard[2][2].equals(player.getIcon()))
        ||(gameBoard[0][0].equals(player.getIcon())
            && gameBoard[1][1].equals(player.getIcon())
            && gameBoard[2][2].equals(player.getIcon()))
        ||(gameBoard[0][2].equals(player.getIcon())
            && gameBoard[1][1].equals(player.getIcon())
            && gameBoard[2][0].equals(player.getIcon()))) {
            return true;
        }
        return false;
    }

    private void end(Player player, boolean isWin) {
        gameActive = false;
        controller.handleWin(player, isWin);
    }

    public void handlePlayerMove(double x, double y) {
        int xpos, ypos;
        System.out.println(x + " " + y);
        if(x <= 200) {
            ypos = 0;
            if(y <= 200) {
                xpos = 0;
            } else if (y <= 400) {
                xpos = 1;
            } else {
                xpos = 2;
            }
        } else if (x <= 400) {
            ypos = 1;
            if(y <= 200) {
                xpos = 0;
            } else if (y <= 400) {
                xpos = 1;
            } else {
                xpos = 2;
            }
        } else {
            ypos = 2;
            if(y <= 200) {
                xpos = 0;
            } else if (y <= 400) {
                xpos = 1;
            } else {
                xpos = 2;
            }
        }
        makePlayerMove(xpos, ypos);
    }

    private void makePlayerMove(int x, int y) {
        if(!currentPlayer.isBot()) {
            if(gameBoard[x][y].equals("")) {
                handleMove(x, y);
                playMove(currentPlayer);
            }
        }
    }

    private void handleMove(int x, int y) {
        gameBoard[x][y] = currentPlayer.getIcon();
        controller.drawMove(currentPlayer, x, y);
        currentPlayer = otherPlayer(currentPlayer);
        System.out.println(x + " " + y); //debugging
        for (String[] s : gameBoard) {
            System.out.println(Arrays.toString(s));
        }
    }

    private void makeBotMove(Player player) {
        boolean moved = false;
        while (!moved) {
            int x = new Random().nextInt(3);
            int y = new Random().nextInt(3);
            if(gameBoard[x][y].equals("")) {
                handleMove(x, y);
                currentPlayer = otherPlayer(player);
                moved = true;
                playMove(currentPlayer);
            }
        }
    }
}
