package HW5;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public class Fractals {
	public static Curve generateFractal(Fractal fractal, int step) {
		Curve fractalImage = new CompositeCurve();
		fractalImage = fractal.step0();
		for(int i=0;i<step;i++)
			fractalImage = fractal.transform(fractalImage);
		
		return fractalImage;
	}

	public static void main(String[] args) {
		Koch test = new Koch();
		Curve snowflake = new CompositeCurve();
		snowflake = generateFractal(test, 11);

		
		SketchPad pad = new SketchPad();
		snowflake.draw(pad);
	}
}