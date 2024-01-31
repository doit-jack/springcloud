# application.yaml

configure-service -> userservice -> core 순서로 env 환경값이 적용된다.(3개 모두 같은 값을 넣었을 때)


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
