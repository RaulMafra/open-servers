package model.entity;

public class Store {

	private Short num;
	private String name;

	public Store(Short num, String name) {
		this.num = num;
		this.name = name;
	}

	public Short getNum() {
		return num;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return num + "," + name;
	}
	
}
