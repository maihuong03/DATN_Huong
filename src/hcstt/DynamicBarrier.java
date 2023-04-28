/**
 * 
 */
package hcstt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * @author Huong
 * @Vật cản di động
 */
public class DynamicBarrier implements ActionListener {
	private Timer timer;
	private Robot robot;
	private int x, y, direction;
	
	private int oldX, oldY;

	public DynamicBarrier(int x, int y, int speed, int direction, Robot robot) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.oldX = x;
		this.oldY = y;
		this.robot = robot;
		robot.changeToDynamicBarrier(x, y);

		timer = new Timer(speed, this);
		this.timer.setInitialDelay(speed);
		this.timer.setDelay(speed);
		this.timer.start();
	}

	public int getDirection() {
		return direction;
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

	// Vật cản di động cạnh vật cản cố định thì vật cản cố định bị mât
	@Override
	public void actionPerformed(ActionEvent arg0) {
		robot.changeToNormal(oldX, oldY);
		double direct = 4 * Math.random();
		int stepX = 0;
		int stepY = 0;
		if (direct < 1) {
			stepY = -1;
			if ((y + stepY) < 0 || !robot.isFree(x, y + stepY)) {
				stepY = -stepY;
			}
			y += stepY;
		} else if (direct < 2) {
			stepY = 1;
			if ((y + stepY) > robot.getAreaSize() - 1 || !robot.isFree(x, y + stepY)) {
				stepY = -stepY;
			}
			y += stepY;
		} else if (direct < 3) {
			stepX = -1;
			if ((x + stepX) < 0 || !robot.isFree(x + stepX, y)) {
				stepX = -stepX;
			}
			x += stepX;
		} else if (direct < 4) {
			stepX = 1;
			if ((x + stepX) > robot.getAreaSize() - 1 || !robot.isFree(x + stepX, y)) {
				stepX = -stepX;
			}
			x += stepX;
		}

		oldX = x;
		oldY = y;
		robot.changeToDynamicBarrier(x, y);
	}
}
