package hellofx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class TimexController implements Initializable {

	@FXML
	private Label label;

	@FXML
	private BorderPane borderPane;

	@FXML
	private HBox buttonBox;

	@FXML
	private HBox timerBox;

	@FXML
	private Text timerText;

	@FXML
	private StackPane imageContainer;

	@FXML
	private ImageView dialImageView;

	@FXML
	private ImageView handImageView;

	@FXML
	public Button start;

	@FXML
	public Button reset;

	@FXML
	public Button stop;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupTimer();
	}

	@FXML
	public void start() {
		timeline.play();
	}

	@FXML
	public void stop() {
		timeline.stop();
	}

	@FXML
	public void reset() {
		stop();
		msElapsed = 0;
		handImageView.setRotate(0);
		ms = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		strMs = String.format("%03d", ms);
		strSeconds = String.format("%02d", seconds);
		strMinutes = String.format("%02d", minutes);
		strHours = String.format("%02d", hours);
		timerText.setText(strHours + ":" + strMinutes + ":" + strSeconds + ":" + strMs);

	}

	// Initialize the important variables.
	private double tickTimeInMs = 1; // Can be altered to change how often the hand moves.
	private final double angleDegreePerMs = 0.006; // Will be used to move the hand every second. (6/1000degrees per ms)

	private double msElapsed = 0; // Will be used to keep track of how many seconds pass.
	private int ms = 0;
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;

	private String strMs = String.format("%03d", ms); // Will be used for printing out milliseconds
	private String strSeconds = String.format("%02d", seconds); // Will be used for printing sec
	private String strMinutes = String.format("%02d", minutes); // Will be used for printing mins
	private String strHours = String.format("%02d", hours); // for hours
	private Timeline timeline; // Timelines can be used to schedule an action.
	private KeyFrame keyFrame; // KeyFrames are used to mark the beginning or end of transition in a timeline.


	public void setupTimer() {

		keyFrame = new KeyFrame(Duration.millis(tickTimeInMs * 1), (ActionEvent event) -> {
			update();
		});

		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Animation.INDEFINITE);

	}

	// Updates the timers every second
	private void update() {
		msElapsed += tickTimeInMs;
		double rotation = msElapsed*angleDegreePerMs;
		handImageView.setRotate(rotation);

		ms += tickTimeInMs;

		/* sec if mseconds reaches 60 */
		if (ms == 1000) {
			seconds += 1;
			ms = 0;
		}

		/* min if sec reaches 60 */
		if (seconds == 60.0) {
			minutes += 1;
			seconds = 0;
		}

		if (minutes == 60.0) {
			hours += 1;
			minutes = 0;
		}

		strMs = String.format("%03d", ms); // updated digital clock
		strSeconds = String.format("%02d", seconds);
		strMinutes = String.format("%02d", minutes);
		strHours = String.format("%02d", hours);
		timerText.setText(strHours + ":" + strMinutes + ":" + strSeconds + ":" + strMs);
	}

	// Allows main to set up a different tickTimeInms
	public void setTickTimeInMs(Double tickTimeInMs) {
		this.tickTimeInMs = tickTimeInMs;
	}

}
