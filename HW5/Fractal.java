package HW5;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public interface Fractal {

	Curve step0(); // returns a new Curve representing step 0 of the fractal
	Curve transform(Curve curve); // given a Curve representing step n of the fractal,
								 // uses it to return a new Curve representing step
								 // n+1 of the fractal

	
	
}