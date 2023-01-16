package stricken;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import nu.pattern.OpenCV;

public class SimpleStricken {

	static int xImg = 0;
	static int yImg = 0;
	private String imagePath;
	private String resultPath;
	private Mat original;
	private Mat ergebnis;

	public void createKnittingPattern(int xImg, int treshold) {
		//loadImage();
		// xImg = 38;
		this.xImg = xImg;
		yImg = (int) xImg * original.height() / original.width();
		ergebnis = new Mat();
		Imgproc.resize(original, ergebnis, new Size(xImg, yImg));
		Imgproc.cvtColor(ergebnis, ergebnis, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(ergebnis, ergebnis, treshold, 255, Imgproc.THRESH_BINARY);
//Imgproc.threshold(srcImg, dstImg, thresholdValue, MAX_BINARY_VALUE, thresholdType);
		Imgproc.resize(ergebnis, ergebnis, new Size(xImg * 10 + 1, yImg * 10 + 1), 1, 1, 3);

		drawGrid(ergebnis);
		
		int rand = (int) (Math.random()*1000);
		
		resultPath = "ergebnis"+rand+".png";
		Imgcodecs.imwrite(resultPath, ergebnis);
		
	}
	public String getResultPath() {
		return resultPath;
	}
	public void saveImage(String path) {
		String pathSave = path;
		Imgcodecs.imwrite(pathSave, ergebnis);
	}

	public void loadImage(String path) {

		OpenCV.loadShared();
		// Load image
		//imagePath = "src/main/resources/pinguin2.png";
		this.imagePath = path;
		this.original = Imgcodecs.imread(imagePath);

		if (original.empty()) {
			System.out.println("Empty image: " + imagePath);
			System.exit(0);
		}
	}

	private static void drawGrid(Mat original) {

		int xSize = xImg * 10 + 10;
		int ySize = yImg * 10 + 10;

		int stepSize = 10;

		for (int x = 0; x < xSize; x += stepSize) {
			Point start = new Point(x, 0);
			Point end = new Point(x, yImg * 10);
			Scalar color = new Scalar(64, 64, 64);
			int thickness = 1;
			Imgproc.line(original, start, end, color, thickness);

		}
		for (int y = 0; y < ySize; y += stepSize) {
			Point start = new Point(0, y);
			Point end = new Point(xImg * 10, y);
			Scalar color = new Scalar(64, 64, 64);
			int thickness = 1;
			Imgproc.line(original, start, end, color, thickness);
		}

	}

}
