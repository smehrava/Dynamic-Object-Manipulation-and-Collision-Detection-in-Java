/* this class is for the height and width of the pixels
 * AUTHOR'S NAME = SARA MEHRAVAR
 * STUDENT ID = 251185394
 */
public class MyObject {
	//instant variables 
	private int id;
	private int width;
	private int height;
	private String type;
	private Location pos;
	private BinarySearchTree tree;

	//constructor
	public MyObject(int id, int width, int height, String type,Location pos) {
		this.id = id; 
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		tree = new BinarySearchTree();
	}
	
	//setting type to type
	public void setType(String type) {
		this.type = type;
	}
	//getting width
	public int getWidth() {
		return width;
	
	}
	//getting height
	public int getHeight() {
		return height;
	}
	//getting type
	public String getType() {
		return type;
	}
	//getting id
	public int getId() {
		return id;
	}
	//getting location
	public Location getLocus() {
		return pos;
	}
	//setting location to value
	public void setLocus(Location value) {
		pos = value;
	}
	
	//Inserts pix into the binary search tree associated with this MyObject
	public void addPel(Pel pix) throws DuplicatedKeyException{
		try {
			this.tree.put(pix);
			
		}catch (DuplicatedKeyException){
			System.out.println("duplicated exception");
		}
		
		
	}
	
	//Returns true if this MyObject intersects the one specified in the parameter. It returns false otherwise.
	public boolean intersects(MyObject otherObject) {
		int LeftWidth = this.getLocus().getx();
        int RightWidth = LeftWidth + this.getWidth();
        int BottomHeight = this.getLocus().gety();
        int TopHeight = BottomHeight + this.getHeight();

        int LeftWidthOther = otherObject.getLocus().getx();
        int RightWidthOther = LeftWidthOther + otherObject.getWidth();
        int BottomHeightOther = otherObject.getLocus().gety();
        int TopHeightOther = BottomHeightOther + otherObject.getHeight();


        //testing whether intersection occurs or not
        if (((LeftWidthOther > LeftWidth && LeftWidth < RightWidthOther) || (RightWidthOther > LeftWidth && RightWidthOther < RightWidth))  && ((BottomHeightOther > BottomHeight && BottomHeightOther < TopHeight) && (TopHeightOther > BottomHeight && TopHeightOther < TopHeight))) { 
            Pel currentPel = pels.smallest(pels.getRoot());
            while (currentPel != null) {
                Location absolutePos = new Location(
                        currentPel.getLocus().getx()+this.width,
                        currentPel.getLocus().gety()+this.height);
                Location PelObjPosRel = new Location(
                        absolutePos.getx()-otherObject.getWidth(),
                        absolutePos.gety()-otherObject.getHeight());
                if (otherObject.pels.get(otherObject.pels.getRoot(), PelObjPosRel) != null) 
                    return true;
                currentPel = pels.successor(pels.getRoot(), currentPel.getLocus());
            }

        }
        return false;
    }
}
