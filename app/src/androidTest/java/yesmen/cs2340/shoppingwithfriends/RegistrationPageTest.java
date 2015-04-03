package yesmen.cs2340.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import java.util.Random;


/**
 * ApplicationTest checks the delete Friend feature.
 */
public class RegistrationPageTest extends ActivityInstrumentationTestCase2<RegistrationPage> {

    private RegistrationPage activity;
    private EditText enteredUsername, enteredPassword, enteredConfirmed;
    private Button registerButton, cancelbutton;
    private Random number = new Random(System.currentTimeMillis());
    int randUser;


    @SuppressWarnings("deprecation")
    public RegistrationPageTest() {
        super("yesmen.cs2340.shoppingwithfriends", RegistrationPage.class);
    }

    /**
     * Get Activity method
     * @throws Exception
     */
    protected void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
        enteredUsername = (EditText)activity.findViewById(R.id.register_username_input);
        enteredPassword = (EditText)activity.findViewById(R.id.register_password_input);
        enteredConfirmed = (EditText)activity.findViewById(R.id.register_confirm_password_input);
        registerButton = (Button)activity.findViewById(R.id.register_execute_button);
        cancelbutton = (Button)activity.findViewById(R.id.register_cancel_button);
        randUser = number.nextInt();
    }

    /**
     * Test case of deleting a friend
     * @throws Throwable
     */
    public void testPasswordMismatch() throws Throwable {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                enteredUsername.setText("TestUser01");
                enteredPassword.setText("123");
                enteredConfirmed.setText("1234");
                registerButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        assertEquals("Password and confirmation do not match!", activity.response);
    }

    public void testRegisterSuccess() throws Throwable {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                enteredUsername.setText(Integer.toString(randUser));
                enteredPassword.setText("1234");
                enteredConfirmed.setText("1234");
                registerButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Thread.sleep(2000);
        assertEquals("Username Successfully Added!", activity.response);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                enteredUsername.setText(Integer.toString(randUser));
                enteredPassword.setText("1234");
                enteredConfirmed.setText("1234");
                registerButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync();
        Thread.sleep(2000);
        assertEquals("I'm sorry, this username is already in use" , activity.response);
    }




}