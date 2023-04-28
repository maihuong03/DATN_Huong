package newone;

public class DataExcel {
	private int size, time, step, repeatStep, numOfCoffee, numOfWater, numOfSoda;
	private double rage;
	private int point, impressive, happy, normal, bad, angry;
	private String rule;
	//int time, int numOfSteps, int duplicateSteps,double  coverage, int numOfCoffee, int numOfWater, int numOfSoda
	public DataExcel() {
	}
	public DataExcel(int size, int time, int step, int repeatStep, double rage, int numOfCoffee, int numOfWater, int numOfSoda) {
		this.size = size;
		this.time = time;
		this.step  = step;
		this.repeatStep = repeatStep;
		this.numOfCoffee = numOfCoffee;
		this.numOfWater = numOfWater;
		this.numOfSoda = numOfSoda;
		this.rage = rage;
	}
	public DataExcel(int point, int a, int b, int c, int d, int e, String rule) {
		this.rule = rule;
		a = impressive;
		b = happy;
		c = normal;
		d= bad;
		e= angry;
		this.point= point;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getRepeatStep() {
		return repeatStep;
	}
	public void setRepeatStep(int repeatStep) {
		this.repeatStep = repeatStep;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getReapeatStep() {
		return repeatStep;
	}
	public void setReapeatStep(int reapeatStep) {
		this.repeatStep = reapeatStep;
	}
	public int getnumOfCoffee() {
		return numOfCoffee;
	}
	public void setnumOfCoffee(int numOfCoffee) {
		this.numOfCoffee = numOfCoffee;
	}
	public int getnumOfWater() {
		return numOfWater;
	}
	public void setnumOfWater(int numOfWater) {
		this.numOfWater = numOfWater;
	}
	public int getnumOfSoda() {
		return numOfSoda;
	}
	public void setnumOfSoda(int numOfSoda) {
		this.numOfSoda = numOfSoda;
	}
	public double getRage() {
		return rage;
	}
	public void setRage(double rage) {
		this.rage = rage;
	}

	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public int getImpressive() {
		return impressive;
	}
	public void setImpressive(int impressive) {
		this.impressive = impressive;
	}
	public int getHappy() {
		return happy;
	}
	public void setHappy(int happy) {
		this.happy = happy;
	}
	public int getNormal() {
		return normal;
	}
	public void setNormal(int normal) {
		this.normal = normal;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public int getAngry() {
		return angry;
	}
	public void setAngry(int angry) {
		this.angry = angry;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
}
