package HW5;
import java.util.ArrayList;
/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

public class CompositeCurve implements Curve
{	
	ArrayList<Line> lines = new ArrayList<Line>();
	
	public void add(Line newLine)
	{
		lines.add(newLine);
	}
	
	public void add(CompositeCurve newCurveGroup)
	{
		ArrayList<Line> newLines = newCurveGroup.getLines();
		
		for(Line line: newLines)
			this.add(line);
	}
	
	public ArrayList<Line> getLines()
	{
		return lines;
	}
	
	public void draw(SketchPad pad) 
	{
		for(Line line: lines)
			line.draw(pad);
	}

	public void translate(double tx, double ty) {
		for(Line line: lines)
			line.translate(tx, ty);
	}

	public void scale(double sx, double sy) {
		for(Line line: lines)
			line.scale(sx, sy);
	}

	public void rotate(double degrees) {
		for(Line line: lines)
			line.rotate(degrees);
	}
	
	public CompositeCurve copy(){
		CompositeCurve tempCompCurve = new CompositeCurve();
		for(Line line: lines)
			tempCompCurve.add(line.copy());
		
		return tempCompCurve;	
	}
}
