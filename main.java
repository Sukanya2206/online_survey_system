import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Survey {
    private int surveyId;
    private String surveyName;
    private String surveyDescription;

    // Constructor
    public Survey(int surveyId, String surveyName, String surveyDescription) {
        this.surveyId = surveyId;
        this.surveyName = surveyName;
        this.surveyDescription = surveyDescription;
    }

    // Method to save survey data to database
    public void saveToDatabase() {
        // JDBC connection parameters
        String url = "jdbc:mysql://localhost:3306/survey_db";
        String username = "username";
        String password = "password";

        // SQL query to insert survey data
        String sql = "INSERT INTO surveys (survey_id, survey_name, survey_description) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameters for the SQL statement
            statement.setInt(1, surveyId);
            statement.setString(2, surveyName);
            statement.setString(3, surveyDescription);

            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Survey data inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while saving survey data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Sample survey data
        int surveyId = 1;
        String surveyName = "Customer Satisfaction Survey";
        String surveyDescription = "Gather feedback from customers about their experience.";

        // Create a new Survey object
        Survey survey = new Survey(surveyId, surveyName, surveyDescription);

        // Save survey data to the database
        survey.saveToDatabase();
    }
}



import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class UserAuthentication {
    private static Map<String, String> userCredentials = new HashMap<>();

    static {
        // Simulated user data (in a real system, this would come from a database)
        userCredentials.put("user1", "password1");
        userCredentials.put("user2", "password2");
        userCredentials.put("user3", "password3");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JLabel messageLabel = new JLabel("");
        messageLabel.setBounds(100, 80, 250, 25);
        panel.add(messageLabel);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (authenticateUser(username, password)) {
                messageLabel.setText("Login successful");
                // Proceed to main application
            } else {
                messageLabel.setText("Invalid username or password");
            }
        });
    }

    private static boolean authenticateUser(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}