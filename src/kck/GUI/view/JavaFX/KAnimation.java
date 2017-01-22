package kck.GUI.view.JavaFX;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * Created by Piotr on 2017-01-22.
 */
public class KAnimation {

	public static void pathTransition(Node node, Point2D start, Point2D end){
		Path path = new Path();
		path.getElements().add(new MoveTo(20,20));
		path.getElements().add(new LineTo(end.getX(), end.getY()));
		//path.getElements().add(new CubicCurveTo(0, 0, 0, 120, 180, 120));
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(2000));
		pathTransition.setPath(path);
		pathTransition.setNode(node);
		pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		pathTransition.setCycleCount(1);
		pathTransition.setAutoReverse(true);
		pathTransition.play();
	}

	public static void fadeIn(Node node){
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
	}

	public static void fadeOut(Node node){
		FadeTransition ft = new FadeTransition(Duration.millis(300), node);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		ft.play();
	}

}
