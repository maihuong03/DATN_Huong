package newone;

public class TimeOb {
	private int hour, minute, PM_AM, index, mode;
	private String info;
	public TimeOb() {}
	public TimeOb(int index,int mode, int hour, int minute, int PM_AM, String info) {
		this.index = index;
		this.mode = mode;
		this.hour = hour;
		this.minute = minute;
		this.PM_AM = PM_AM;
		this.info = info;
	}

	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getPM_AM() {
		return PM_AM;
	}
	public void setPM_AM(int pM_AM) {
		PM_AM = pM_AM;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	
	
}
