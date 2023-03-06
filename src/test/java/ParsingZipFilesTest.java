import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class ParsingZipFilesTest {
    ClassLoader cl = ParsingZipFilesTest.class.getClassLoader();
    @Test
    @DisplayName("Парсинг данных из Zip фрхива")

    void zipParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("test.zip");
                ZipInputStream zis = new ZipInputStream(resource)
        ) {
            ZipEntry entry;
            while((entry = zis.getNextEntry()) != null) {

                if (entry.getName().endsWith(".csv")) {

                    CSVReader reader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = reader.readAll();
                    assertThat(content.get(0)[0]).contains("Tilda UID");

                } else if (entry.getName().endsWith(".pdf")) {

                    PDF content = new PDF(zis);
                    assertThat(content.text).contains("Пример сопроводительного файла");

                } else if (entry.getName().endsWith(".xlsx")) {
                    XLS content = new XLS(zis);
                    assertThat(content.excel.getSheetAt(1).getRow(0).getCell(2)
                    .getStringCellValue().contains("<AT> АВСТРИЯ"));

                }
            }
        }
    }
}