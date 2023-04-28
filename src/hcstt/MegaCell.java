/**
 * 
 */
package hcstt;


/**
 * @author HuyLV
 *
 */
public class MegaCell {
	private MegaCell parent;
	private int x;
	private int y;
	private boolean status;

	public MegaCell() {

	}

	public MegaCell(MegaCell p, int x, int y, boolean status) {
		this.parent = p;
		this.x = x;
		this.y = y;
		this.status = status;
	}

	public MegaCell getParent() {
		return parent;
	}

	public void setParent(MegaCell parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isEqual(MegaCell mega){ //So sánh 2 megacell 
		boolean equalX = (mega.getX()/2 == this.x/2);
		boolean equalY = (mega.getY()/2 == this.y/2);
		return equalX && equalY;
	}

}
