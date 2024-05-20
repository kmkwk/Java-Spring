package com.indiv.study.virtualthread;

import com.indiv.study.entity.Member;
import com.indiv.study.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class VirtualThreadTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 스레드_테스트() {
        long startTime = System.nanoTime();

        List<Thread> threads = IntStream.range(0, 1_000_000)
                .mapToObj(i -> new Thread(() -> {
                }))
                .toList();

        threads.forEach(Thread::start);

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("End : " + durationSeconds);
    }

    @Test
    public void 가상_스레드_테스트() {
        long startTime = System.nanoTime();

        List<Thread> threads = IntStream.range(0, 1_000_000)
                .mapToObj(i -> Thread.ofVirtual().unstarted(() -> {}))
                .toList();

        threads.forEach(Thread::start);

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("End : " + durationSeconds);
    }



    @Test
    public void 멤버_생성_스레드_테스트() throws InterruptedException {
        long startTime = System.nanoTime();

        List<Thread> threads = IntStream.range(0, 1000)
                .mapToObj(i -> new Thread(() -> createMember()))
                .toList();

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("End : " + durationSeconds);
    }

    @Test
    public void 멤버_생성_가상_스레드_테스트() throws InterruptedException {
        long startTime = System.nanoTime();

        List<Thread> threads = IntStream.range(0, 1000)
                .mapToObj(i -> Thread.ofVirtual().unstarted(() -> createMember()))
                .toList();

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.nanoTime();
        double durationSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println("End : " + durationSeconds);
    }

    private void createMember() {
        Member member = Member.builder()
                .name("이름")
                .email("이메일")
                .phoneNumber("핸드폰 번호")
                .build();


        memberRepository.save(member);
    }
}