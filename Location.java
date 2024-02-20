/*This class represents the location (x, y) of a pel.
 * AUTHOR'S NAME = SARA MEHRAVAR
 * STUDENT ID = 251185394
 */
public class Location {

	private int x;
	private int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	//returning x
	public int getx() {
		return x;
	}
	//returning y
	public int gety() {
		return y;
	}
	//compareTo method for comparing this and p's y and x
	public int compareTo(Location p) {
		if (this.gety() > p.gety() || (this.gety() == p.gety() && this.getx() > p.getx())) {
            return 1;
        } else if (this.getx() == p.getx() && this.gety() == p.gety()) {
            return 0;
        } else {
            return -1;
        }
		
	}

}
