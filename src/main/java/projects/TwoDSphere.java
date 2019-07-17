package projects;

import java.io.File;
import java.io.IOException;
import raytracer.Canvas;
import raytracer.Color;
import raytracer.Intersections;
import raytracer.Ray;
import raytracer.Sphere;
import raytracer.Tuple;
import java.util.List;
import java.util.ArrayList;

public class TwoDSphere {
  public static void main(String[] args) {
    new TwoDSphere().run();
  }

  final int canvasSideLength = 1000;
  final Sphere s = new Sphere();
  final Tuple rayOrigin = Tuple.point(0, 0, -5);
  final double wallZ = 10.0;
  final double wallSide = 7.0;
  final double half = wallSide / 2;
  final double pixelSize = wallSide / canvasSideLength;
  final Color hitcolor = Color.RED;

  public void run() {
    Canvas corners = computeCornerCanvas();
    Canvas antiAliased = computeAntiAliasedCanvas(corners);
    try {
      antiAliased.writePngFile(new File("2DSphere.png"));
    } catch (IOException ex) {
      System.err.println("Error Writing Image " + ex.getMessage());
    }
  }

//Makes canvas composed of colors at the corners of final image

  public Canvas computeCornerCanvas() {
  	Canvas c = new Canvas(canvasSideLength + 1, canvasSideLength + 1);
    for (int y = 0; y <= canvasSideLength; y++) {
      for (int x = 0; x <= canvasSideLength; x++) {
        if (shootRay(x, y, 0, 0)) {
          c.writePixel(x, y, hitcolor);
        }
      }
    }
    return c;
  }

  public Canvas computeAntiAliasedCanvas(Canvas cornerCanvas) {
  	Canvas antiAliased = new Canvas(canvasSideLength, canvasSideLength);
  	for(int y = 0; y < canvasSideLength; y++) {
  		for(int x = 0; x < canvasSideLength; x++) {
  			antiAliased.writePixel(x, y, computePixelColor(cornerCanvas, x, y));
  		}
  	}
  	return antiAliased;
  }

  public Color computePixelColor(Canvas cornerCanvas, int x, int y) {
  	List<Color> colors = new ArrayList<>();
  	colors.add(cornerCanvas.pixelAt(x, y));
  	colors.add(cornerCanvas.pixelAt(x + 1, y));
  	colors.add(cornerCanvas.pixelAt(x, y +1));
  	colors.add(cornerCanvas.pixelAt(x + 1, y + 1));
  	if(colors.get(0) == colors.get(1) && colors.get(2) == colors.get(3) && colors.get(0) == colors.get(2)) {
  		return colors.get(0);
  	}
  	if(shootRay(x, y, 0.5, 0.5)) {
  		colors.add(hitcolor);
  	} else {
  		colors.add(Color.BLACK);
  	}
  	return averageColors(colors);
  }

  public boolean shootRay(int x, int y, double dx, double dy) {
  	double worldX = getWorldX(x, 0);
    double worldY = getWorldY(y, 0);
    Tuple rayTarget = Tuple.point(worldX, worldY, wallZ);
    Ray r = Ray.create(rayOrigin, rayTarget.subtract(rayOrigin).normalize());
    Intersections xs = s.intersect(r);
    return xs.isHit();
  }

  public Color averageColors(List<Color> colors) {
  	Color finalColor = Color.BLACK;
  	for(Color color : colors) {
  		finalColor = finalColor.add(color);
  	}
  	return finalColor.value(1.0 / colors.size());
  }

  public double getWorldX(int x, double dx) {
  	return (-half + pixelSize * x) + pixelSize * dx;
  }

  public double getWorldY(int y, double dy) {
  	return (half - pixelSize * y) - pixelSize * dy;
  }
}
