package jr.kings.webtoon.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import jr.kings.webtoon.domain.Alarm;
import jr.kings.webtoon.domain.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberTests
 */

@SpringBootTest
@Slf4j
public class MemberTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private PasswordEncoder pwEncoder;

    @Test
    public void testInsertMember() {

        log.info("testInsertMember......");


        IntStream.range(1, 101).forEach(i -> {

            List<String> genderList = Arrays.asList("남성", "여성");

            Date date = new Date();
            String birth = "1992-01-" + (i % 31 + 1);
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            int randomBoolean = ((boolean)(Math.random() < 0.5)) ? 0 : 1;
            log.info(""+randomBoolean);
            Member member = Member.builder()
                                    .id("user"+i)
                                    .pw("pw"+i)
                                    .phone("phoneNumber"+i)
                                    .email("email"+i)
                                    .gender(genderList.get(randomBoolean))
                                    .age(date)
                                    .build();

            log.info(""+memberRepository.save(member));
        });
    }

    @Test
    public void insertAuth(){
        
    }


    @Test
    public void testInsertAlarm(){

        log.info("testInsertAlarm......");

        IntStream.range(0, 20).forEach(i->{

            Alarm alarm = new Alarm();

            alarm.setMsg("msg" + i);
            alarm.setMember(memberRepository.getMember("user1"));

            log.info(""+alarmRepository.save(alarm));

        });
    }

    @Test
    public void testAlarmMember(){

        log.info("testAlarmMember......");

        Pageable page = PageRequest.of(1, 10);
        
        List<Object[]> result = memberRepository.getMemberAlarm("user1", page);

        result.forEach(i->{

            log.info("i[0]: "+i[0]);
            log.info("i[1]: "+i[1]);

        });

        log.info("result.size(): "+result.size());

    }


}