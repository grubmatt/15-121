package HW5;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public class Sierpinski implements Fractal {

	public Curve step0() {
		Line bottom = new Line(.1,.1,.9,.1);
		Line left = new Line(.1,.1,.5,.9);
		Line right = new Line(.5,.9,.9,.1);
		CompositeCurve triangle = new CompositeCurve();
		triangle.add(bottom);
		triangle.add(left);
		triangle.add(right);
		
		return triangle;
	}

	public Curve transform(Curve curve) {
		CompositeCurve triangle = (CompositeCurve) curve;
		CompositeCurve triangleLeft = (CompositeCurve) curve.copy();
		CompositeCurve triangleRight = (CompositeCurve) curve.copy();
		CompositeCurve finalTriangle = new CompositeCurve();
		
		triangle.scale(.5,.5);
		triangle.translate(.25, .5);
		
		triangleLeft.scale(.5,.5);
		triangleLeft.translate(.05,.1);
		
		triangleRight.scale(.5,.5);
		triangleRight.translate(.45,.1);
		
		finalTriangle.add(triangle);
		finalTriangle.add(triangleLeft);
		finalTriangle.add(triangleRight);
		
		return finalTriangle;
	}

}
