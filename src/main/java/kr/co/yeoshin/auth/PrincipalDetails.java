package kr.co.yeoshin.auth;

 // 시큐리티에 설정한 loginProcessingUrl 부분의 주소요청이 오면 시큐리티가 낚아채서 로그인을 진행시킴
 // 로그인 진행이 완료되면 시큐리티 session공간을 만들어 줌(Security ContextHolder)
 // 세션에 들어갈 수 있는 정보(오브젝트)는 => Authentication 타입 객체로 정해져 있음.
 // Authentication 안에 User 정보(오브젝트)가 있어야 됨 => User 오브젝트 타입 => UserDetails 타입 객체

 // Security Session => Authentication => UserDetails

import kr.co.yeoshin.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }

    // 해당 member의 권한을 리턴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
