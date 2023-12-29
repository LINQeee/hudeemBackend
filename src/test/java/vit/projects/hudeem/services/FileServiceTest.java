package vit.projects.hudeem.services;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FileServiceTest {
//
//    @Autowired
//    private FileService fileService;
//
//    @Test
//    void readFromInputStream_Positive_Result() throws FileNotFoundException {
//        String currentDirectory = System.getProperty("user.dir") + "/src/test/java/vit/projects/hudeem/services/testFiles";
//
//        String filePath = currentDirectory + "/testFile.txt";
//        InputStream inputStream = new FileInputStream(filePath);
//
//        String result = fileService.readFromInputStream(inputStream);
//        String expected = "some text for a test\nmore test for a test\n";
//
//        Assertions.assertThat(result).isEqualTo(expected);
//    }
//
//    @Test
//    void readFromInputStream_Empty_File() throws FileNotFoundException {
//        String currentDirectory = System.getProperty("user.dir") + "/src/test/java/vit/projects/hudeem/services/testFiles";
//
//        String filePath = currentDirectory + "/emptyFile.txt";
//        InputStream inputStream = new FileInputStream(filePath);
//
//        String result = fileService.readFromInputStream(inputStream);
//        String expected = "";
//
//        Assertions.assertThat(result).isEqualTo(expected);
//    }
//
//    @Test
//    void readFromInputStream_Null_InputStream_Exception() {
//
//
//        Assertions.assertThatThrownBy(() -> fileService.readFromInputStream(null))
//                .isInstanceOf(RuntimeException.class)
//                .hasMessage("Empty InputStream");
//    }
}