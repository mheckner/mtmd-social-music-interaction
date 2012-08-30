package uniregensburg.medieninformatik.examples.hello_mt4j.scenes;

import java.util.HashMap;

import org.mt4j.AbstractMTApplication;
import org.mt4j.components.visibleComponents.widgets.MTImage;
import org.mt4j.components.visibleComponents.widgets.MTTextArea;
import org.mt4j.input.inputProcessors.IGestureEventListener;
import org.mt4j.input.inputProcessors.MTGestureEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragEvent;
import org.mt4j.input.inputProcessors.componentProcessors.dragProcessor.DragProcessor;
import org.mt4j.sceneManagement.AbstractScene;
import org.mt4j.util.animation.Animation;
import org.mt4j.util.animation.AnimationEvent;
import org.mt4j.util.animation.IAnimationListener;
import org.mt4j.util.animation.MultiPurposeInterpolator;
import org.mt4j.util.math.ToolsGeometry;
import org.mt4j.util.math.Vector3D;

import processing.core.PImage;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Children;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Colors;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Files;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Geometry;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Strings;
import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Values;
import uniregensburg.medieninformatik.examples.hello_mt4j.ui.SceneFrame;

public class HelloMT4JScene extends AbstractScene {

	private MTTextArea textArea;
	private HashMap<String, MTImage> images;
	private SceneFrame sceneFrame;

	private float endPosition = SMUX_Values.DEFAULT_ENDPOSITION_FOR_IMAGE_ANIMATION;

	public HelloMT4JScene(AbstractMTApplication mtApplication, String name) {
		super(mtApplication, name);
		initUI();
		initSceneFrame();
		initText(SMUX_Strings.HELLO_MT4J);
		initImage(SMUX_Values.IMAGE_COUNT_FOR_DEMOSCENE);
		slideImageIn();
	}

	private void initUI() {
		setClearColor(SMUX_Colors.SCENE_BACKGROUND_COLOR);
	}

	private void initSceneFrame() {
		sceneFrame = new SceneFrame(getMTApplication(),
				SMUX_Geometry.SCENE_FRAME_BORDER_RADIUS / 2,
				SMUX_Geometry.SCENE_FRAME_BORDER_RADIUS / 2, 0,
				SMUX_Geometry.SCREEN_WIDTH
						- SMUX_Geometry.SCENE_FRAME_BORDER_RADIUS,
				SMUX_Geometry.SCREEN_HEIGHT
						- SMUX_Geometry.SCENE_FRAME_BORDER_RADIUS, 20, 20);
		sceneFrame.setName(SMUX_Children.SCENE_FRAME);
		sceneFrame.setNoStroke(true);
		sceneFrame.setFillColor(SMUX_Colors.SCENEFRAME_BACKGROUND_COLOR);
		sceneFrame.setPickable(false);
		getCanvas().addChild(sceneFrame);
	}

	private void initText(String msg) {

		textArea = new MTTextArea(getMTApplication());
		textArea.setName(SMUX_Children.TEXT_AREA);
		textArea.setText(msg);

		textArea.setPickable(false);

		textArea.setFillColor(SMUX_Colors.TEXTAREA_FILLCOLOR);
		textArea.setNoFill(true);
		textArea.setNoStroke(true);

		sceneFrame.addChild(textArea);

		textArea.setPositionGlobal(SMUX_Geometry.SCREEN_CENTER);
	}

	private void initImage(int count) {

		images = new HashMap<String, MTImage>();

		for (int i = 0; i < count; i++) {
			PImage tmpPImage = getMTApplication().loadImage(
					SMUX_Files.DEMO_IMAGE);

			final MTImage image = new MTImage(getMTApplication(), tmpPImage);
			image.setName(SMUX_Children.IMAGE);

			image.removeAllGestureEventListeners(DragProcessor.class);
			image.addGestureListener(DragProcessor.class,
					new IGestureEventListener() {
						public boolean processGestureEvent(MTGestureEvent ge) {
							DragEvent de = (DragEvent) ge;

							image.translateGlobal(de.getTranslationVect());

							Vector3D[] v1 = sceneFrame.getVerticesGlobal();

							boolean inside = true;

							if (!ToolsGeometry.isPolygonContainsPoint(v1,
									image.getCenterPointGlobal())) {
								inside = false;
							}

							if (!inside) {
								image.translateGlobal(de.getTranslationVect()
										.getScaled(-1));
							}
							return false;
						}
					});

			image.scaleGlobal(0.1f, 0.1f, 0.1f, image.getCenterPointGlobal());

			image.setDrawSmooth(true);

			images.put(SMUX_Children.IMAGE + i, image);
		}

	}

	private void slideImageIn() {

		for (String key : images.keySet()) {
			final MTImage image = images.get(key);
			sceneFrame.addChild(image);
			MultiPurposeInterpolator interpolator = new MultiPurposeInterpolator(
					0, endPosition, 1000, 0.1f, 0.8f, 1);
			final Animation moveAnimation = new Animation("move animation",
					interpolator, this, 8000);
			moveAnimation.addAnimationListener(new IAnimationListener() {
				@Override
				public void processAnimationEvent(AnimationEvent ae) {
					image.setPositionGlobal(new Vector3D(ae.getValue(), 500));
				}
			});

			endPosition -= SMUX_Values.DEFAULT_MARGIN_BETWEEN_IMAGES;

			moveAnimation.start();
		}

	}

}
