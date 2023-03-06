import com.fasterxml.jackson.databind.ObjectMapper;
import model.DataStudents;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonJacksonTest {
    ClassLoader cl = JsonJacksonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void jsonParseTest() throws Exception  {
         try (
                InputStream resource = cl.getResourceAsStream("student.json");
                InputStreamReader reader = new InputStreamReader(resource);
            ){
             DataStudents jsonObject = objectMapper.readValue(reader, DataStudents.class);
             assertThat(jsonObject.id).isEqualTo("1001");
             assertThat(jsonObject.score.mathematics).isEqualTo("90");
             assertThat(jsonObject.score.science).isEqualTo("85");
             assertThat(jsonObject.score.computer).isEqualTo("89");
             assertThat(jsonObject.formCompleted).isTrue();
             assertThat(jsonObject.name).isEqualTo("Bella");
             assertThat(jsonObject.surname).isEqualTo("Petrova");

         }
    }
}

