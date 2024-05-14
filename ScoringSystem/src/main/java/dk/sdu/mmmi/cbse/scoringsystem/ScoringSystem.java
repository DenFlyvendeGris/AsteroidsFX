package dk.sdu.mmmi.cbse.scoringsystem;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/points")
@SpringBootApplication
@RestController
public class ScoringSystem {

    private Long scorePoints = 0L;

    public static void main(String[] args) {
        SpringApplication.run(ScoringSystem.class, args);
    }

    @GetMapping("/mypoints")
    public Long getScore() {
        return scorePoints;
    }

    @PutMapping("/newPoints/{score}")
    public Long setScore(@PathVariable (value = "score") Long score) {
        scorePoints += score;
        return scorePoints;
    }


}
