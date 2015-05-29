package HW5;
import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public class Line implements Curve{
	double x1, x2, y1, y2, x1Original,y1Original;
	
	public Line(double x1, double y1, double x2, double y2)
	{
	    this.x1 = x1;
	    this.x2 = x2;
	    this.y1 = y1;
	    this.y2 = y2;
	}
	
	public void draw(SketchPad pad)
	{
		pad.drawLine(x1, y1, x2, y2);
	}

	public void translate(double tx, double ty) {
		x1+=tx;
		x2+=tx;
		y1+=ty;
		y2+=ty;		
	}

	public void scale(double sx, double sy) {
		x1*=sx;
		x2*=sx;
		y1*=sy;
		y2*=sy;				
	}

	public void rotate(double degrees) {
		// x' = x cos θ - y sin θ 
		// y' = x sin θ + y cos θ
		
		double radians = Math.toRadians(degrees);		
		double x1new, y1new, x2new, y2new;
		
		x1new = x1*Math.cos(radians) - y1*Math.sin(radians);
		y1new = x1*Math.sin(radians) + y1*Math.cos(radians);
		
		x2new = x2*Math.cos(radians) - y2*Math.sin(radians);
		y2new = x2*Math.sin(radians) + y2*Math.cos(radians);

		x1 = x1new;
		x2 = x2new;
		y1 = y1new;
		y2 = y2new;
	}

	public Line copy() {
		Line tempLine = new Line(x1,y1,x2,y2);
		
		return tempLine;
	}
}
