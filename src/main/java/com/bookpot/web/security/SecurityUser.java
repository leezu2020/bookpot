package com.bookpot.web.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser implements UserDetails {

	// 사용자 중요 데이터
	// 등록번호
	private Long no;
	// 닉네임
	private String name;
	// 프로필 이미지
	private String imgUrl;
	
	// 보안 필드
	private Collection<? extends GrantedAuthority> authorities;
	private String username;	// credential(이메일)
	private String password;	// credential
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	// 계정에 대한 디테일 설정
	// 비밀번호 변경 기한
	// 휴먼상태 등등
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

	// 중복 로그인 설정을 위함
	// 다시 확인해보기
	@Override
	public boolean equals(Object obj) {
	//	return super.equals(obj);
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		SecurityUser other = (SecurityUser) obj;
		if(username == null) {
			if(other.username != null)
				return false;
		} else if(!username.equals(other.username))
			return false;
		
		return true;
	}
	@Override
	public int hashCode() {
	//	return super.hashCode();
		// 코드 이유 찾기
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
}
