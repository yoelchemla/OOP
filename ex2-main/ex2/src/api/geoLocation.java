package api;

import java.util.StringTokenizer;

public class geoLocation implements geo_location {

	private double x;
	private double y;
	private double z;

	public geoLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public double x() {
		return this.x;
	}

	@Override
	public double y() {
		return this.y;
	}

	@Override
	public double z() {
		return this.z;
	}

	@Override
	public double distance(geo_location g) {		//??????
		double newX = Math.pow(this.x - g.x(), 2);
		double newY= Math.pow(this.y - g.y(), 2);
		double newZ = Math.pow(this.z - g.z(), 2);

		return Math.sqrt(newX + newY + newZ);
	}
	public geoLocation(String d) {
        StringTokenizer location = new StringTokenizer(d,",",false);
        x = Double.parseDouble(location.nextToken());
        y = Double.parseDouble(location.nextToken());
        z = Double.parseDouble(location.nextToken());
    }
}
