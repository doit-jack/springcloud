# application.yaml

config-service -> userservice -> core 순서로 env 환경값이 적용된다.(3개 모두 값을 넣었을 때)

# 비대칭키 암호화

```bash
# 공개키 생성
keytool -genkeypair -alias apiEncryptionKey -keyalg RSA -dname "CN=jack, OU=API Development, O=kakao.co.kr, L=Seoul, C=KR" -keypass "test1234" -keystore apiEncryptionKey.jks -storepass "test1234"

keytool -list -keystore apiEncryptionKey.jks -v

# 인증서 생성
keytool -export -alias apiEncryptionKey -keystore apiEncryptionKey.jks -rfc -file trustServer.cer  

# 공개 키 생성
keytool -import -alias trustServer -file trustServer.cer -keystore publicKey.jks  
```

여기서는 apiEncryptionKey 만 필요.




<pre data-ke-type="codeblock"><h3 data-ke-size="size23"><span><b>[<span><span><span><span> </span>Spring Cloud Config 설정 파일 우선 순위</span></span></span> ]</b></span></h3>
<p data-ke-size="size16">설정 파일은 크게 다음의 위치에 존재할 수 있으며 다음의 순서대로 읽어진다. 나중에 읽어지는 것이 우선순위가 높다.</p>
<ul data-ke-list-type="disc"><li><p>프로젝트의 application.yaml</p></li><li data-node-id="20240204204214-2hhmv2k"><p>설정 저장소의 application.yaml</p></li><li><p>프로젝트의 application-{profile}.yaml</p></li><li data-node-id="20240204204214-009rfyc"><p>설정 저장소의 {application name}/{application name}-{profile}</p></li></ul>
<p data-ke-size="size16"></p></pre>

출처: [https://mangkyu.tistory.com/253](https://mangkyu.tistory.com/253) [MangKyu's Diary:티스토리]
