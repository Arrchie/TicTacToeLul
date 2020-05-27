package sample;

public class Player {

    private PLAYER icon;
    private boolean isBot;

    public Player(PLAYER icon, boolean isBot) {
        this.icon = icon;
        this.isBot = isBot;
    }

    public String getIcon() {
        return icon.toString();
    }

    public boolean isBot() {
        return isBot;
    }

    public enum PLAYER {
        X, O
    }
}
