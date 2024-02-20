/*this class  represents the data items to be stored in the nodes of the binary search tree
 * STUDENT ID = 251185394
 * AUTHORS NAME = SARA MEHRAVAR
 */
public class Pel {

	private int color;
	private Location p;
	
	public Pel(Location p, int color) {
		this.color = color;
		this.p = p;
	}
	//RETURN LOCATION
	public Location getLocus() {
		return p;
	}
	//RETURN COLOR
	public int getColor() {
		return color;
	}

}
