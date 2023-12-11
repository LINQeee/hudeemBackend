package vit.projects.hudeem.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HashServiceTest {

    @Autowired
    private HashService hashService;

    @Test
    void getHashFrom_Negative_Result() {
        String result = hashService.getHashFrom("   ");
        Assertions.assertThat(result).isNull();

        result = hashService.getHashFrom(null);
        Assertions.assertThat(result).isNull();

        result = hashService.getHashFrom("");
        Assertions.assertThat(result).isNull();
    }

    @Test
    void getHashFrom_Positive_Result() {
        String value = "test";
        String expected = "36f028580bb02cc8272a9a020f4200e346e276ae664e45ee80745574e2f5ab80";

        String result = hashService.getHashFrom(value);
        Assertions.assertThat(result).isEqualTo(expected);
    }
}