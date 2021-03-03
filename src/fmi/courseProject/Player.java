package CourseProject;

public class Player {

    int id;
    boolean isActive;

    public Player p1 = new Player(1, true);
    public Player p2 = new Player(2, false);

    public Player(int id, boolean isActive) {
        this.id = id;
        this.isActive = isActive;
    }
}
