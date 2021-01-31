package api;

public class edgeLocation implements edge_location{

		private edge_data edge;
		private double ratio;

		
		public edgeLocation(edge_data edge, double ratio) {
		this.edge = edge;	
		this.ratio = ratio;
		}
		
	@Override
	public edge_data getEdge() {
		return this.edge;
	}

	@Override
	public double getRatio() {//????
		return this.ratio;
	}

}
 