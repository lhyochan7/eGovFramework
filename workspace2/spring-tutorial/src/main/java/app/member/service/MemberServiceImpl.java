package app.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.member.Member;
import app.member.repository.MemberRepository;
import app.member.repository.MemoryMemberRepository;

@Component
public class MemberServiceImpl implements MemberService {

//	private MemberRepository memberRepository = new MemoryMemberRepository();
	private String prefix;
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	@Autowired
	private MemberRepository memberRepository;
	
	public void setMemberRepository(MemberRepository memberRepository) {
		System.out.println("setMemberRepository() 호출");
		this.memberRepository = memberRepository;
	}

	public MemberRepository getMemberRepository() {
		return memberRepository;
	}
	
	public MemberServiceImpl() {
		System.out.println("기본 생성자 호출");
	}

	public MemberServiceImpl(MemberRepository memberRepository) {
		System.out.println("memberRepository 주입 생성자 호출");
		this.memberRepository = memberRepository;
	}

	@Override
	public void join(Member member) throws Exception {
		System.out.println("join 시작");
		// 1. id 확인
		Long memberId = member.getId();
		if (memberId == null) {
			throw new Exception();
		}
		
		// 2. 중복확인
		Member storedMember = memberRepository.findById(memberId);
		if(storedMember != null) {
			throw new RuntimeException();
		}
		
		memberRepository.join(member);
		System.out.println("join 종료");
	}

	@Override
	public Member findMember(Long memberId) throws Exception {
		return memberRepository.findById(memberId);
	}

}
