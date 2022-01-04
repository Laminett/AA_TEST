package kr.co.yeoshin.auth;

import kr.co.yeoshin.entity.Member;
import kr.co.yeoshin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

 // 시큐리티 설정에서 loginProcessingUrl 에 설정된 url로 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    // session(Authentication(UserDetails)) 의 형태
    @Override
    // 해당부분 에서 받는 변수명은 클라이언트에서 넘어오는 변수명과 일치해야 동작한다
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=> username: " + username);
        Member member = memberRepository.findByUsername(username);
        if(member != null) {
            return new PrincipalDetails(member);
        }
        return null;
    }
}
