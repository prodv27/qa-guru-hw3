import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize="1920x1080";
        Configuration.baseUrl="https://demoqa.com";
        Configuration.holdBrowserOpen = true;

    }

    @Test
    void fillFormTest() {

        File file = new File("src/test/resources/bfoto_ru_4761.jpg");

        open("/automation-practice-form");

        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        $("#firstName").setValue("Dmitry");
        $("#lastName").setValue("Prokopev");
        $("#userEmail").setValue("dprokopev@t1-consulting.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9508292376");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("September");
        $(".react-datepicker__year-select").selectOptionContainingText("1997");
        $(".react-datepicker__day--009").click();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#uploadPicture").uploadFile(file);
        $("#currentAddress").setValue("Это не очень интересно");
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#react-select-4-input").setValue("Delhi").pressEnter();
        $("#submit").click();

        $(".modal-content").shouldHave(text("Dmitry Prokopev"), text("dprokopev@t1-consulting.ru"), text("Male"), text("9508292376"), text("9 September,1997"), text("Computer Science"), text("Sports"), text("bfoto_ru_4761.jpg"), text("Это не очень интересно"), text("NCR Delhi"));

    }

}