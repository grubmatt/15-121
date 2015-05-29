package HW5;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public class Koch implements Fractal {

	public Curve step0() {
		CompositeCurve basicCurve = new CompositeCurve();
		basicCurve.add(new Line(.1,.5,.9,.5));
		return basicCurve;
	}

	public Curve transform(Curve curve) {
		curve.scale(.33, .33);
		
		CompositeCurve finalSnowflake = new CompositeCurve();
		
		CompositeCurve leftLine = (CompositeCurve) curve.copy();
		CompositeCurve leftMidSection = (CompositeCurve) curve.copy();
		CompositeCurve rightLine = (CompositeCurve) curve.copy();
		
		
		leftLine.translate(.066, .33);
		finalSnowflake.add(leftLine);
		
		rightLine.translate(.597, .33);
		finalSnowflake.add(rightLine);
		
		
		leftMidSection.rotate(60);
		leftMidSection.translate(.49, .382);
		finalSnowflake.add(leftMidSection);
		
		CompositeCurve rightMidSection = leftMidSection.copy();
		rightMidSection.rotate(-120);
		rightMidSection.translate(.253, 1.286);
		finalSnowflake.add(rightMidSection);
		return finalSnowflake;
	}

}
