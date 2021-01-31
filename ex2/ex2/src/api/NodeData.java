package api;

public class NodeData implements node_data {

	private int key;
	private double weight;
	private String info;
	private int tag;
	private static int counter = 0;
	private geo_location geoLocation;
	
	public NodeData() {
		this.key = counter;
		counter++;
		weight = 0;
		info = "";
		tag = 0; 
	}
	public NodeData(node_data T) {
	this.key = T.getKey();
	this.info = T.getInfo();
	this.weight = T.getWeight();
	this.tag = T.getTag();
	this.geoLocation = T.getLocation();
	}
	
	public NodeData(int key) {
		this.key = key;
	}
	
	
	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public geo_location getLocation() {
		return this.geoLocation;
	}

	@Override
	public void setLocation(geo_location p) {
		p = this.geoLocation;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		return this.info ;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		t = this.tag;
	}
	public String toString() 
	{
		return " " + this.key;
	}
}
