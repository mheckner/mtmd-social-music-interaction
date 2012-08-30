package uniregensburg.medieninformatik.examples.hello_mt4j;

import org.mt4j.MTApplication;

import uniregensburg.medieninformatik.examples.hello_mt4j.config.SMUX_Scenes;
import uniregensburg.medieninformatik.examples.hello_mt4j.scenes.HelloMT4JScene;

public class StartHelloMT4JScene extends MTApplication{
	private static final long serialVersionUID = 1L;
	
	private HelloMT4JScene helloMT4JScene = null;

	public static void main(String[] args) {
		initialize();
	}

	@Override
	public void startUp() {
		helloMT4JScene = new HelloMT4JScene(this, SMUX_Scenes.HELLOMT4JSCENE);
		changeScene(helloMT4JScene);
	}

}
