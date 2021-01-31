package api;

public class edgeData implements edge_data{

	//fields
	private node_data src;
	private node_data dest;
	private double weight;
	private String info;
	private int tag;
	private String key;
	
	//constructor
	public edgeData(String key, double weight, node_data src, node_data dest) {//????
		this.src = src;
		this.dest = dest;
		this.weight = weight; 
		this.info = " ";
		this.tag = 0;
		this.key = key;
	}


	@Override
	public int getSrc() {
		return this.src.getKey();
	}

	@Override
	public int getDest() {
		return this.dest.getKey();
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		s = this.info;
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
		return "src"+ this.src +", dest: "+this.dest+", w: "+this.weight;
	}
}
