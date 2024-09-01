package Main;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Function {

    // Store train details
    private int train_no;
    private String train_name;
    private String base_station;
    private String destination_station;
    private int platform_no;

    // Database connection details
    private final String url = "jdbc:mysql://localhost:3306/train_timetable";
    private final String user = "use your sql name";
    private final String pass = "use your sql password";

    // Establish database connection
    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    // Getter and Setter methods...

    // Take user input for train and platform details
    void takeDetails() {
        try (Scanner input = new Scanner(System.in); Connection conn = connect()) {
            System.out.println("Enter Train No: ");
            train_no = input.nextInt();
            System.out.println("Enter Platform No: ");
            platform_no = input.nextInt();

            // Fetch details of train
            String query = "SELECT Train_name, Base_Station, Destination_Station FROM new_train_table WHERE Train_no = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, train_no);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        train_name = rs.getString("Train_name");
                        base_station = rs.getString("Base_Station");
                        destination_station = rs.getString("Destination_Station");
                    } else {
                        System.out.println("Train Name Not Found");
                        train_name = "Unknown...";
                        base_station = "Unknown...";
                        destination_station = "Unknown...";
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database Connection Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    // Display train and platform details
    void displayDetails() {
        String announcement = "Attention Please! The train number " + train_no + "\n" +
                "from " + base_station + " to " + destination_station + "\n" +
                train_name + " is now arriving at Platform no " + platform_no + ".\n" +
                "Passengers are requested to stand behind the safety line and be careful while boarding the train.";
        System.out.println(announcement);

        // TTS announcement
        try {
            // Ensure FreeTTS loads the voice
            System.setProperty("freetts.voices",
                    "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");

            VoiceManager voiceManager = VoiceManager.getInstance();
            Voice voice = voiceManager.getVoice("kevin16");

            if (voice != null) {
                voice.allocate();
                voice.speak(announcement);
                voice.deallocate();
            } else {
                System.out.println("Voice 'kevin16' not available.");
            }
        } catch (Exception e) {
            System.out.println("Error with TTS: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        try {
            Function f = new Function();
            f.takeDetails();
            f.displayDetails();
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }
    }
}
