package sample;

import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javafx.scene.control.*;

public class DataEntryGUITest {

    @Before
    public void setUp(){
        JFXPanel panel = new JFXPanel();
    }


    @Test
    public void testSetNamePrompt(){
        //test1
        TextField test1 = new TextField();
        DataEntryGUI.setNamePrompt(test1);
        assertEquals("Name", test1.getPromptText());

        //test2
        TextField test2 = new TextField();
        DataEntryGUI.setNamePrompt(test2);
        assertEquals("Name", test2.getPromptText());
    }

    @Test
    public void testSetTelephonePrompt(){
        //test1
        TextField test1 = new TextField();
        DataEntryGUI.setTelephonePrompt(test1);
        assertEquals("(###) ###-####", test1.getPromptText());

        //test2
        TextField test2 = new TextField();
        DataEntryGUI.setTelephonePrompt(test2);
        assertEquals("(###) ###-####", test2.getPromptText());
    }

    @Test
    public void testNameInvalidityHelper(){
        //invalid name test1
        TextField invalidTest1 = new TextField();
        invalidTest1.setText("Ronny Michael Krakowski");
        DataEntryGUI.nameInvalidityHelper(invalidTest1);
        assertEquals("-fx-text-inner-color: red;", invalidTest1.getStyle());

        //invalid name test2
        TextField invalidTest2 = new TextField();
        invalidTest2.setText("michael dBeroko");
        DataEntryGUI.nameInvalidityHelper(invalidTest2);
        assertEquals("-fx-text-inner-color: red;", invalidTest2.getStyle());

        //valid name test1
        TextField validTest1 = new TextField();
        validTest1.setText("Michael Dberoko");
        DataEntryGUI.nameInvalidityHelper(validTest1);
        assertEquals("-fx-text-inner-color: black;", validTest1.getStyle());

        //valid name test2
        TextField validTest2 = new TextField();
        validTest2.setText("John Smith");
        DataEntryGUI.nameInvalidityHelper(validTest2);
        assertEquals("-fx-text-inner-color: black;", validTest2.getStyle());
    }

    @Test
    public void testTelephoneInvalidityHelper(){
        //invalid telephone test1
        TextField invalidTest1 = new TextField();
        invalidTest1.setText("2124947837");
        DataEntryGUI.telephoneInvalidityHelper(invalidTest1);
        assertEquals("-fx-text-inner-color: red;", invalidTest1.getStyle());

        //invalid telephone test2
        TextField invalidTest2 = new TextField();
        invalidTest2.setText("9019");
        DataEntryGUI.telephoneInvalidityHelper(invalidTest2);
        assertEquals("-fx-text-inner-color: red;", invalidTest2.getStyle());

        //valid telephone test1
        TextField validTest1 = new TextField();
        validTest1.setText("(212) 494-7837");
        DataEntryGUI.telephoneInvalidityHelper(validTest1);
        assertEquals("-fx-text-inner-color: black;", validTest1.getStyle());

        //valid telephone test2
        TextField validTest2 = new TextField();
        validTest2.setText("(347) 929-0000");
        DataEntryGUI.telephoneInvalidityHelper(validTest2);
        assertEquals("-fx-text-inner-color: black;", validTest2.getStyle());
    }

    @Test
    public void testIsNameColorBlack(){
        //valid name displayed in black text
        TextField blackTest1 = new TextField();
        blackTest1.setStyle("-fx-text-inner-color: black;");
        assertTrue(DataEntryGUI.isNameColorBlack(blackTest1));

        //invalid name displayed in red text
        TextField redTest1 = new TextField();
        redTest1.setStyle("-fx-text-inner-color: red;");
        assertFalse(DataEntryGUI.isNameColorBlack(redTest1));
    }

    @Test
    public void testIsTelephoneColorBlack(){
        //valid telephone displayed in black text
        TextField blackTest1 = new TextField();
        blackTest1.setStyle("-fx-text-inner-color: black;");
        assertTrue(DataEntryGUI.isTelephoneColorBlack(blackTest1));

        //invalid telephone displayed in red text
        TextField redTest1 = new TextField();
        redTest1.setStyle("-fx-text-inner-color: red;");
        assertFalse(DataEntryGUI.isTelephoneColorBlack(redTest1));
    }

    @Test
    public void testCanCreateProfiles(){
        // test 1 to see if button is disabled when no text areas have text in them
        TextField n1 = new TextField();
        TextField n2 = new TextField();
        TextField n3 = new TextField();
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        assertTrue(DataEntryGUI.canCreateProfiles(n1, n2, n3, t1, t2, t3));

        // test 2 to see if button is disabled when some text areas have text in them

        n1.setText("data");
        n3.setText("data");
        t2.setText("data");

        assertTrue(DataEntryGUI.canCreateProfiles(n1, n2, n3, t1, t2, t3));

        // test 3 to see if button is enabled when all text areas have text in them

        n1.setText("data");
        n2.setText("data");
        n3.setText("data");
        t1.setText("data");
        t2.setText("data");
        t3.setText("data");

        assertFalse(DataEntryGUI.canCreateProfiles(n1, n2, n3, t1, t2, t3));
    }

    @Test
    public void testInvalidInformationExists(){
        //if there is invalid information, the error box does indeed pop-up
        TextField n1 = new TextField();
        TextField n2 = new TextField();
        TextField n3 = new TextField();
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        n1.setStyle("-fx-text-inner-color: red;");
        n2.setStyle("-fx-text-inner-color: red;");
        n3.setStyle("-fx-text-inner-color: red;");
        t1.setStyle("-fx-text-inner-color: red;");
        t2.setStyle("-fx-text-inner-color: red;");
        t3.setStyle("-fx-text-inner-color: red;");

        assertTrue(DataEntryGUI.invalidInformationExists(n1, n2, n3, t1, t2, t3));
    }

    @Test
    public void testValidInformationExists(){
        //if there is no invalid information, the text boxes all become uneditable, and the final box saying "Data Saved, does, indeed, pop-up
        TextField n1 = new TextField();
        TextField n2 = new TextField();
        TextField n3 = new TextField();
        TextField t1 = new TextField();
        TextField t2 = new TextField();
        TextField t3 = new TextField();

        n1.setStyle("-fx-text-inner-color: black;");
        n2.setStyle("-fx-text-inner-color: black;");
        n3.setStyle("-fx-text-inner-color: black;");
        t1.setStyle("-fx-text-inner-color: black;");
        t2.setStyle("-fx-text-inner-color: black;");
        t3.setStyle("-fx-text-inner-color: black;");

        assertTrue(DataEntryGUI.validInformationExists(n1, n2, n3, t1, t2, t3));
    }
}
