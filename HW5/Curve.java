package HW5;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public interface Curve {
	
	void draw(SketchPad pad);  // draws line
	void translate(double tx, double ty); // translates to the right by tx and up by ty
	void scale(double sx, double sy); // scales width by sx and height by of sy
	void rotate(double degrees); // rotates counter-clockwise by degrees (about the origin)
	Curve copy();

}