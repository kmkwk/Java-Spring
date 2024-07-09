package com.example.api.proxy;

import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    public void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(realSubject);

        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
    }

    @Test
    public void cacheProxyTest() {
        // RealSubject 생성 - 인터페이스
        Subject subject = new RealSubject();
        // 컈시 프록시에 RealSubject 객체 주입
        CacheProxy cacheProxy = new CacheProxy(subject);
        // 클라이언트에 캐시 프록시 주입
        ProxyPatternClient client = new ProxyPatternClient(cacheProxy);

        // 캐시 프록시 실행
        client.execute();
        client.execute();
        client.execute();
    }
}
